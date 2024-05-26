package jp.goyand.bankingmodulith.account.domain.factory

import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import jp.goyand.bankingmodulith.account.domain.aggregate.Account
import jp.goyand.bankingmodulith.account.domain.entity.*
import jp.goyand.bankingmodulith.account.domain.value.*
import jp.goyand.bankingmodulith.customer.domain.value.CustomerId

class AccountFactory {
    companion object {
        fun createAccount(
            customerId: String,
            accountName: String,
            fromDate: LocalDate = LocalDate.now(),
            accountType: AccountType = AccountType.SAVING,
        ): Account {
            val now = Instant.now()
            return Account(
                null,
                AccountNumber.new(),
                AccountName(accountName),
                accountType,
                role =
                    AccountRole(
                        accountId = null,
                        role = AccountRoleType.OWNER,
                        customerId = CustomerId(customerId),
                        fromDate = fromDate,
                    ),
                status = AccountStatus(accountId = null, status = AccountStatusType.ACTIVE, datetime = now),
                balance = Balance(Currency(BigDecimal.ZERO, CurrencyType.JPY), now),
            )
        }
    }
}
