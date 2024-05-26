package jp.goyand.bankingmodulith.account.application.dto

import java.math.BigDecimal

data class AccountDto(
    val id: String,
    val accountNumber: String,
    val customerId: String,
    val balance: BigDecimal,
    val currency: String,
)
