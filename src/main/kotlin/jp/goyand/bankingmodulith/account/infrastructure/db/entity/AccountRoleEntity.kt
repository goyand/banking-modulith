package jp.goyand.bankingmodulith.account.infrastructure.db.entity

import jakarta.persistence.*
import java.time.LocalDate
import jp.goyand.bankingmodulith.common.db.IdBaseEntity

@Entity(name = "AccountRole")
@Table(
    name = "account_roles",
    uniqueConstraints =
        [
            UniqueConstraint(
                columnNames = ["account_id", "customer_id", "from_date"],
                name = "uk_account_role_account_customer_from_date",
            )
        ],
)
class AccountRoleEntity(
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", foreignKey = ForeignKey(name = "fk_account_status_account"))
    var accountEntity: AccountEntity,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "role",
        referencedColumnName = "code",
        insertable = false,
        updatable = false,
        foreignKey = ForeignKey(name = "fk_account_role_account_role_type"),
    )
    val accountRoleTypeEntity: AccountRoleTypeEntity? = null,
    @Column(name = "customer_id", nullable = false) val customerId: String,
    @Column(name = "role", nullable = false) val role: String,
    @Column(name = "from_date", nullable = false) val fromDate: LocalDate,
    @Column(name = "thru_date", nullable = true) val thruDate: LocalDate? = null,
) : IdBaseEntity()
