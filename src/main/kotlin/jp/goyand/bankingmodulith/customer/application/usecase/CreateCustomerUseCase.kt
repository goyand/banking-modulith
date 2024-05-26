package jp.goyand.bankingmodulith.customer.application.usecase

import jp.goyand.bankingmodulith.customer.application.dto.CreateCustomerDto
import jp.goyand.bankingmodulith.customer.application.dto.CustomerDto

interface CreateCustomerUseCase {
    fun execute(data: CreateCustomerDto): CustomerDto
}
