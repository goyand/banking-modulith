package jp.goyand.bankingmodulith.account.infrastructure.db.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jp.goyand.bankingmodulith.common.db.TypeEntity

@Entity(name = "AccountStatusType")
@Table(name = "account_status_types")
class AccountStatusTypeEntity : TypeEntity()
