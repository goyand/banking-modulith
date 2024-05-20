package jp.goyand.bankingmodulith.customer.application.query

import jp.goyand.bankingmodulith.customer.application.dto.CustomerQueryDto

interface CustomerQuery {
    fun getCustomer(customerId: String): CustomerQueryDto?
}
