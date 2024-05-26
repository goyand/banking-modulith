package jp.goyand.bankingmodulith.account.domain.aggregate

import jp.goyand.bankingmodulith.account.domain.entity.*
import jp.goyand.bankingmodulith.account.domain.value.*

class Account(
    val id: AccountId?,
    val accountNumber: AccountNumber,
    val accountName: AccountName,
    val accountType: AccountType,
    val currency: CurrencyType = CurrencyType.JPY,
    val description: String? = null,
    val role: AccountRole? = null,
    val status: AccountStatus? = null,
    val balance: Balance? = null,
)
