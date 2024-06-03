import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.dependency.management)
	alias(libs.plugins.detekt)
	alias(libs.plugins.spotless)
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

flyway {
	url = "jdbc:postgresql://localhost:5432/banking"
	user = "postgres"
	password = "password"
//	locations = arrayOf("classpath:db/migration")
	cleanDisabled = false
}
