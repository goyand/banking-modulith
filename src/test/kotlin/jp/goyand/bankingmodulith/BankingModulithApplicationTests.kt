package jp.goyand.bankingmodulith

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.modulith.core.ApplicationModules

@SpringBootTest
class BankingModulithApplicationTests {

    @Test
    fun contextLoads() {
        ApplicationModules.of(BankingModulithApplication::class.java).verify()
    }
}
