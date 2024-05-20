package jp.goyand.bankingmodulith.customer.infrastructure.db

import jp.goyand.bankingmodulith.customer.application.port.db.CustomerRepository
import jp.goyand.bankingmodulith.customer.domain.aggregate.Customer
import jp.goyand.bankingmodulith.customer.infrastructure.db.jpa.CustomerJpaRepository
import jp.goyand.bankingmodulith.customer.infrastructure.db.mapper.CustomerDbMapper
import org.springframework.stereotype.Repository

@Repository
class CustomerRepositoryImpl(
    private val customerJpaRepository: CustomerJpaRepository,
    private val customerDbMapper: CustomerDbMapper,
) : CustomerRepository {
    override fun save(customer: Customer): Customer {
        return customerDbMapper
            .toEntity(customer)
            .let { customerJpaRepository.save(it) }
            .let { customerDbMapper.toModel(it) }
    }

    override fun findById(id: String): Customer? {
        TODO("Not yet implemented")
    }

    override fun findByCustomerNumber(customerNumber: String): Customer? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Customer> {
        TODO("Not yet implemented")
    }
}
