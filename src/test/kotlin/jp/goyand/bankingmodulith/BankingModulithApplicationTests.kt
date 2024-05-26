package jp.goyand.bankingmodulith

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.modulith.core.ApplicationModules

@SpringBootTest
class BankingModulithApplicationTests {

    @Test
    fun contextLoads() {
        // todo: add test
        ApplicationModules.of(BankingModulithApplication::class.java).forEach(::println)
        ApplicationModules.of(BankingModulithApplication::class.java).sharedModules.forEach(::println)

        ApplicationModules.of(BankingModulithApplication::class.java).verify()
    }
}
