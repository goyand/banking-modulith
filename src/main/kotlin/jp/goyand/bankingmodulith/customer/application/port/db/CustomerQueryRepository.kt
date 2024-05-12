package jp.goyand.bankingmodulith.customer.application.port.db

import jp.goyand.bankingmodulith.customer.application.dto.CustomerDto

interface CustomerQueryRepository {
    fun getCustomer(customerId: String): CustomerDto?
}
