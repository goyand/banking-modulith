package jp.goyand.bankingmodulith.customer.infrastructure.api.message

import jakarta.validation.constraints.NotBlank
import jp.goyand.bankingmodulith.customer.application.dto.CreateCustomerDto
import jp.goyand.bankingmodulith.customer.application.dto.CustomerDto

data class CreateCustomerRequest(
    @NotBlank(message = "First name is required") val firstName: String,
    @NotBlank(message = "Last name is required") val lastName: String,
    val middleName: String?,
) {

    fun toDto() =
        CreateCustomerDto(firstName = firstName, lastName = lastName, middleName = middleName)
}

data class CreateCustomerResponse(
    val id: String,
    val customerNumber: String,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
) {

    companion object {
        fun from(dto: CustomerDto): CreateCustomerResponse {
            return CreateCustomerResponse(
                id = dto.id,
                customerNumber = dto.customerNumber,
                firstName = dto.firstName,
                lastName = dto.lastName,
                middleName = dto.middleName,
            )
        }
    }
}
