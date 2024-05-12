package jp.goyand.bankingmodulith.customer.infrastructure.api.message

import jp.goyand.bankingmodulith.customer.application.dto.CustomerDto

interface GetCustomerResponse {
    val id: String
    val customerNumber: String
    val firstName: String
    val lastName: String
    val middleName: String?

    companion object {
        fun from(customerDto: CustomerDto): GetCustomerResponse {
            return object : GetCustomerResponse {
                override val id = customerDto.id
                override val customerNumber = customerDto.customerNumber
                override val firstName = customerDto.firstName
                override val lastName = customerDto.lastName
                override val middleName = customerDto.middleName
            }
        }
    }
}
