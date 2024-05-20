package jp.goyand.bankingmodulith.customer.application.port.db

import jp.goyand.bankingmodulith.customer.domain.aggregate.Customer

interface CustomerRepository {
    fun save(customer: Customer): Customer

    fun findById(id: String): Customer?

    fun findByCustomerNumber(customerNumber: String): Customer?

    fun findAll(): List<Customer>
}
