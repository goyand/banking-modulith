package jp.goyand.bankingmodulith.customer.application.usecase.impl

import jakarta.transaction.Transactional
import jp.goyand.bankingmodulith.customer.application.dto.CreateCustomerDto
import jp.goyand.bankingmodulith.customer.application.dto.CustomerDto
import jp.goyand.bankingmodulith.customer.application.port.db.CustomerRepository
import jp.goyand.bankingmodulith.customer.application.usecase.CreateCustomerUseCase
import jp.goyand.bankingmodulith.customer.domain.aggregate.Customer
import jp.goyand.bankingmodulith.customer.domain.event.CustomerCreated
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class CreateCustomerInteractor(
    private val customerRepository: CustomerRepository,
    private val events: ApplicationEventPublisher,
) : CreateCustomerUseCase {

    @Transactional
    override fun execute(data: CreateCustomerDto): CustomerDto {
        return Customer.create(
                firstName = data.firstName,
                lastName = data.lastName,
                middleName = data.middleName,
            )
            .let { customerRepository.save(it) }
            .let {
                events.publishEvent(CustomerCreated(it.id!!.value))
                CustomerDto(
                    id = it.id.value,
                    customerNumber = it.customerNumber.value,
                    firstName = it.name.firstName,
                    lastName = it.name.lastName,
                    middleName = it.name.middleName,
                )
            }
    }
}
