package jp.goyand.bankingmodulith.account.infrastructure.db.mapper

import jp.goyand.bankingmodulith.account.domain.aggregate.Account
import jp.goyand.bankingmodulith.account.domain.entity.*
import jp.goyand.bankingmodulith.account.domain.value.*
import jp.goyand.bankingmodulith.account.infrastructure.db.entity.AccountBalanceEntity
import jp.goyand.bankingmodulith.account.infrastructure.db.entity.AccountEntity
import jp.goyand.bankingmodulith.account.infrastructure.db.entity.AccountRoleEntity
import jp.goyand.bankingmodulith.account.infrastructure.db.entity.AccountStatusEntity
import jp.goyand.bankingmodulith.customer.domain.value.CustomerId
import org.springframework.stereotype.Component

@Component
class AccountDbMapper {
    fun toEntity(model: Account): AccountEntity {
        return AccountEntity(
                accountNumber = model.accountNumber.value,
                accountName = model.accountName.name,
                description = model.description,
                currency = model.currency,
                accountType = model.accountType.name,
            )
            .also {
                it.accountBalance =
                    model.balance?.let { balance ->
                        AccountBalanceEntity(
                            accountEntity = it,
                            amount = balance.currency.amount(),
                            datetime = balance.datetime,
                        )
                    }
                it.accountRoles =
                    model.role?.let { role ->
                        mutableListOf(
                            AccountRoleEntity(
                                accountEntity = it,
                                role = role.role.name,
                                customerId = role.customerId.value,
                                fromDate = role.fromDate,
                            )
                        )
                    }
                it.accountStatuses =
                    model.status?.let { status ->
                        mutableListOf(
                            AccountStatusEntity(
                                accountEntity = it,
                                status = status.status.name,
                                datetime = status.datetime,
                            )
                        )
                    }
            }
    }

    fun toModel(entity: AccountEntity): Account {
        return Account(
            id = entity.id?.let { AccountId(it) },
            accountNumber = AccountNumber(entity.accountNumber),
            accountName = AccountName(entity.accountName),
            description = entity.description,
            accountType = entity.accountType.let { AccountType.valueOf(it) },
            role =
                entity.accountRoles?.firstOrNull()?.let { role ->
                    AccountRole(
                        accountId = entity.id?.let { AccountId(it) },
                        role = AccountRoleType.valueOf(role.role),
                        customerId = CustomerId(role.customerId),
                        fromDate = role.fromDate,
                    )
                },
            status =
                entity.accountStatuses?.firstOrNull()?.let { status ->
                    AccountStatus(
                        accountId = entity.id?.let { AccountId(it) },
                        status = AccountStatusType.valueOf(status.status),
                        datetime = status.datetime,
                    )
                },
            balance =
                entity.accountBalance?.let { balance ->
                    Balance(
                        currency = Currency(amount = balance.amount, currency = balance.accountEntity.currency),
                        datetime = balance.datetime,
                    )
                },
        )
    }
}
