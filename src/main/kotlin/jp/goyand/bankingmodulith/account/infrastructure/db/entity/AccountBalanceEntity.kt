package jp.goyand.bankingmodulith.account.infrastructure.db.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.Instant
import jp.goyand.bankingmodulith.common.db.IdBaseEntity

@Entity(name = "AccountBalance")
@Table(
    name = "account_balances",
    uniqueConstraints =
        [UniqueConstraint(columnNames = ["account_id"], name = "uk_account_balance_account")],
)
class AccountBalanceEntity(
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", foreignKey = ForeignKey(name = "fk_account_balance_account"))
    var accountEntity: AccountEntity,
    @Column(name = "amount", nullable = false) val amount: BigDecimal,
    @Column(name = "datetime", nullable = false) val datetime: Instant,
) : IdBaseEntity()
