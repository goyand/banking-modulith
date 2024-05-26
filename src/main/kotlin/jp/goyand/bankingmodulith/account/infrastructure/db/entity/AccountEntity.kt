package jp.goyand.bankingmodulith.account.infrastructure.db.entity

import jakarta.persistence.*
import jp.goyand.bankingmodulith.account.domain.entity.CurrencyType
import jp.goyand.bankingmodulith.common.db.IdBaseEntity

@Entity(name = "Account")
@Table(
    name = "accounts",
    uniqueConstraints =
        [UniqueConstraint(columnNames = ["account_number"], name = "uk_account_account_number")],
)
@NamedEntityGraph(
    name = "AccountEntity.full",
    attributeNodes =
        [
            NamedAttributeNode("accountBalance"),
            NamedAttributeNode("accountRoles"),
            NamedAttributeNode("accountStatuses"),
        ],
)
class AccountEntity(
    @Column(name = "account_number", nullable = false) val accountNumber: String,
    @Column(name = "account_name", nullable = false) val accountName: String,
    @Column(name = "description") val description: String?,
    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    val currency: CurrencyType,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "account_type",
        referencedColumnName = "code",
        insertable = false,
        updatable = false,
        foreignKey = ForeignKey(name = "fk_account_account_type"),
    )
    val accountTypeEntity: AccountTypeEntity? = null,
    @Basic(optional = false) @Column(name = "account_type", nullable = false) val accountType: String,
) : IdBaseEntity() {
    @OneToOne(
        mappedBy = "accountEntity",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH],
    )
    var accountBalance: AccountBalanceEntity? = null

    @OneToMany(
        mappedBy = "accountEntity",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH],
    )
    var accountRoles: MutableList<AccountRoleEntity>? = mutableListOf()

    @OneToMany(
        mappedBy = "accountEntity",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH],
    )
    var accountStatuses: MutableList<AccountStatusEntity>? = mutableListOf()
}
