package jp.goyand.bankingmodulith.account.application.listener

import jp.goyand.bankingmodulith.customer.domain.event.CustomerCreated

interface CreateCustomerListener {
    fun onCreateCustomer(event: CustomerCreated)
}
