package jp.goyand.bankingmodulith.customer.infrastructure.db.jpa

import jp.goyand.bankingmodulith.customer.infrastructure.db.entity.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerJpaRepository : JpaRepository<CustomerEntity, String> {}
