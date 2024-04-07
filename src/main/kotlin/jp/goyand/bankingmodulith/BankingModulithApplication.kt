package jp.goyand.bankingmodulith

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication class BankingModulithApplication

fun main(args: Array<String>) {
  @Suppress("SpreadOperator") runApplication<BankingModulithApplication>(*args)
}
