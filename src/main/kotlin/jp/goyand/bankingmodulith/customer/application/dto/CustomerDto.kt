package jp.goyand.bankingmodulith.customer.application.dto

import jp.goyand.bankingmodulith.customer.domain.Customer

data class CustomerDto(
    val id: String,
    val customerNumber: String,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
) {

    companion object {
        fun from(customer: Customer): CustomerDto {
            return CustomerDto(
                id = customer.id,
                customerNumber = customer.customerNumber,
                firstName = customer.name.firstName,
                lastName = customer.name.lastName,
                middleName = customer.name.middleName,
            )
        }
    }
}
