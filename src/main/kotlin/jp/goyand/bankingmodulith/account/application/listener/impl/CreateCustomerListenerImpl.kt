package jp.goyand.bankingmodulith.account.application.listener.impl

import jp.goyand.bankingmodulith.account.application.listener.CreateCustomerListener
import jp.goyand.bankingmodulith.account.application.usecase.CreateAccountUseCase
import jp.goyand.bankingmodulith.customer.domain.event.CustomerCreated
import org.springframework.modulith.events.ApplicationModuleListener
import org.springframework.stereotype.Component

@Component
class CreateCustomerListenerImpl(private val createAccountUseCase: CreateAccountUseCase) :
    CreateCustomerListener {
    @ApplicationModuleListener
    override fun onCreateCustomer(event: CustomerCreated) {
        createAccountUseCase.execute(event.customerId)
    }
}
