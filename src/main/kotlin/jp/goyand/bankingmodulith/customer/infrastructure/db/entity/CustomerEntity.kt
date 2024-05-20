package jp.goyand.bankingmodulith.customer.infrastructure.db.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jp.goyand.bankingmodulith.common.db.IdBaseEntity

@Entity(name = "Customer")
@Table(name = "customers")
class CustomerEntity(
    @Column(name = "customer_number", nullable = false) val customerNumber: String,
    @Column(name = "first_name", nullable = false) val firstName: String,
    @Column(name = "last_name", nullable = false) val lastName: String,
    @Column(name = "middle_name") val middleName: String?,
) : IdBaseEntity()
