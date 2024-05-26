package jp.goyand.bankingmodulith.account.infrastructure.db.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jp.goyand.bankingmodulith.common.db.TypeEntity

@Entity(name = "AccountType") @Table(name = "account_types") class AccountTypeEntity : TypeEntity()
