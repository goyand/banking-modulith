package jp.goyand.bankingmodulith.account.infrastructure.db.entity

import jakarta.persistence.*
import java.time.Instant
import jp.goyand.bankingmodulith.common.db.IdBaseEntity

@Entity(name = "AccountStatus")
@Table(
    name = "account_statuses",
    uniqueConstraints =
        [
            UniqueConstraint(
                columnNames = ["account_id", "datetime"],
                name = "uk_account_status_account_datetime",
            )
        ],
)
class AccountStatusEntity(
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", foreignKey = ForeignKey(name = "fk_account_status_account"))
    var accountEntity: AccountEntity,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "status",
        referencedColumnName = "code",
        insertable = false,
        updatable = false,
        foreignKey = ForeignKey(name = "fk_account_status_account_status_type"),
    )
    val accountStatusTypeEntity: AccountStatusTypeEntity? = null,
    @Column(name = "status", nullable = false) val status: String,
    @Column(name = "datetime", nullable = false) val datetime: Instant,
) : IdBaseEntity()
