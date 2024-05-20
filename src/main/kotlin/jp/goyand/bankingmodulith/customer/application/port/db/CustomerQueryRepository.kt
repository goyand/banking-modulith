package jp.goyand.bankingmodulith.customer.application.port.db

import jp.goyand.bankingmodulith.customer.application.dto.CustomerQueryDto

interface CustomerQueryRepository {
    fun getCustomer(customerId: String): CustomerQueryDto?
}
