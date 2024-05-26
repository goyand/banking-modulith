import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun
import org.ysb33r.gradle.terraform.tasks.TerraformApply
import org.ysb33r.gradle.terraform.tasks.TerraformDestroy

plugins {
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.dependency.management)
	alias(libs.plugins.detekt)
	alias(libs.plugins.spotless)
	alias(libs.plugins.terraform)
	alias(libs.plugins.flyway)
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	kotlin("plugin.jpa") version "1.9.24"
}

group = "jp.goyand"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
	maven("https://repo.spring.io/milestone")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.modulith:spring-modulith-bom:1.1.5")

		// Uncomment the line below to use the latest version of the Spring Modulith BOM
		// mavenBom("org.springframework.modulith:spring-modulith-bom:1.2.0")
	}
}

dependencies {
	implementation(libs.spring.boot.starter)
	implementation(libs.spring.boot.starter.web)
	implementation(libs.spring.boot.starter.data.jpa)
	implementation(libs.spring.boot.starter.validation)
	implementation(libs.spring.modulith.starter.jpa)
	runtimeOnly(libs.spring.modulith.starter.insight)

	implementation(libs.springdoc.openapi.starter.webmvc.ui)
	implementation(libs.springdoc.openapi.starter.webmvc.api)
	implementation(libs.kotlin.reflect)
	implementation(libs.commons.lang3)
	implementation(libs.jnanoid)
	implementation(libs.flyway)
	runtimeOnly(libs.postgresql)
	runtimeOnly(libs.flyway.database.postgresql)
	testImplementation(libs.spring.boot.starter.test) {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(group = "org.mockito", module = "mockito-core")
	}
	testImplementation(libs.junit.jupiter.engine)
	testImplementation(libs.springmockk)
	testImplementation(libs.rider.core)
	testImplementation(libs.rider.spring)
	testImplementation(libs.spring.modulith.starter.test)
	testImplementation(libs.assertj.core)
}

buildscript {
	dependencies {
		classpath(libs.postgresql)
		classpath(libs.flyway.database.postgresql)
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

detekt {
	buildUponDefaultConfig = true
	allRules = false
	config.setFrom("$projectDir/config/detekt/detekt.yml")
}

spotless {
	kotlin {
		ktfmt("0.47").googleStyle()
		indentWithTabs(2)
		indentWithSpaces(4)
	}
}

terraformSourceSets {
	create("dev") {
		setSrcDir("$projectDir/terraform/dev")
	}
}

flyway {
	url = "jdbc:postgresql://localhost:5432/banking"
	user = "postgres"
	password = "password"
//	locations = arrayOf("classpath:db/migration")
	cleanDisabled = false
}

// all tasks named starts with "flyway" should depend on tfDevApply
tasks.filter { it.name.startsWith("flyway") }
	.forEach {
		it.dependsOn(tasks.withType(TerraformApply::class).named("tfDevApply"))
//		it.finalizedBy(tasks.withType(TerraformDestroy::class).named("tfDevDestroy"))
	}

// set autoApprove true to terraform apply
tasks.withType(TerraformDestroy::class).named("tfDevDestroy").configure {
	setAutoApprove(true)
}

setOf(BootRun::class, Test::class).forEach {
	tasks.withType(it) {
		dependsOn(tasks.withType(TerraformApply::class).named("tfDevApply"))
		finalizedBy(tasks.withType(TerraformDestroy::class).named("tfDevDestroy"))
		doFirst {
			val dbPortExternal = terraformSourceSets.getByName("dev").rawOutputVariable("db_port_external").get()
			val dbPassword = terraformSourceSets.getByName("dev").rawOutputVariable("db_password").get()
			val dbUser = terraformSourceSets.getByName("dev").rawOutputVariable("db_user").get()
			val jdbcUrl = "jdbc:postgresql://localhost:$dbPortExternal/banking?schema=public"

			// datasource
			systemProperty("spring.datasource.url", jdbcUrl)
			systemProperty("spring.datasource.username", dbUser)
			systemProperty("spring.datasource.password", dbPassword)
			systemProperty("spring.datasource.driver-class-name", "org.postgresql.Driver")

			// flyway
			systemProperty("spring.flyway.enabled", "true")
			systemProperty("spring.flyway.url", jdbcUrl)
			systemProperty("spring.flyway.user", dbUser)
			systemProperty("spring.flyway.password", dbPassword)
		}
	}
}
