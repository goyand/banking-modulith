package jp.goyand.bankingmodulith.customer.application.query

import jp.goyand.bankingmodulith.customer.application.dto.CustomerDto

interface CustomerQuery {
    fun getCustomer(customerId: String): CustomerDto?
}
