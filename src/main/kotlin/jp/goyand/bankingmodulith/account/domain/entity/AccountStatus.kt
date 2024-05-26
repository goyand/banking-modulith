package jp.goyand.bankingmodulith.account.domain.entity

import java.time.Instant
import jp.goyand.bankingmodulith.account.domain.value.AccountId

class AccountStatus(
    val accountId: AccountId?,
    val status: AccountStatusType,
    val datetime: Instant,
)
