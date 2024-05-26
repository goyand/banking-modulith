package jp.goyand.bankingmodulith.account.domain.entity

import java.time.LocalDate
import jp.goyand.bankingmodulith.account.domain.value.AccountId
import jp.goyand.bankingmodulith.customer.domain.value.CustomerId

class AccountRole(
    val accountId: AccountId?,
    val role: AccountRoleType,
    val customerId: CustomerId,
    val fromDate: LocalDate,
    var thruDate: LocalDate? = null,
)
