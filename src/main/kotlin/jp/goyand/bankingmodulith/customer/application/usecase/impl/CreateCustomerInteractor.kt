package jp.goyand.bankingmodulith.customer.application.usecase.impl

import jp.goyand.bankingmodulith.customer.application.dto.CreateCustomerDto
import jp.goyand.bankingmodulith.customer.application.dto.CustomerDto
import jp.goyand.bankingmodulith.customer.application.port.db.CustomerRepository
import jp.goyand.bankingmodulith.customer.application.usecase.CreateCustomer
import jp.goyand.bankingmodulith.customer.domain.aggregate.Customer
import org.springframework.stereotype.Component

@Component
class CreateCustomerInteractor(private val customerRepository: CustomerRepository) :
    CreateCustomer {
    override fun execute(data: CreateCustomerDto): CustomerDto {
        return Customer.create(
                firstName = data.firstName,
                lastName = data.lastName,
                middleName = data.middleName,
            )
            .let { customerRepository.save(it) }
            .let {
                CustomerDto(
                    id = it.id!!.value,
                    customerNumber = it.customerNumber.value,
                    firstName = it.name.firstName,
                    lastName = it.name.lastName,
                    middleName = it.name.middleName,
                )
            }
    }
}
