package jp.goyand.bankingmodulith.customer.application.dto

data class CustomerDto(
    val id: String,
    val customerNumber: String,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
)
