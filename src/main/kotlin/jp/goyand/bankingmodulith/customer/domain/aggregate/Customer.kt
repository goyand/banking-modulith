package jp.goyand.bankingmodulith.customer.domain.aggregate

import jp.goyand.bankingmodulith.customer.domain.value.CustomerId
import jp.goyand.bankingmodulith.customer.domain.value.CustomerName
import jp.goyand.bankingmodulith.customer.domain.value.CustomerNumber

class Customer
private constructor(
    val id: CustomerId?,
    val customerNumber: CustomerNumber,
    val name: CustomerName,
) {

    companion object {
        fun create(
            id: String?,
            customerNumber: String,
            firstName: String,
            lastName: String,
            middleName: String?,
        ): Customer {
            return Customer(
                id?.let { CustomerId(it) },
                CustomerNumber(customerNumber),
                CustomerName(firstName, lastName, middleName),
            )
        }

        fun create(firstName: String, lastName: String, middleName: String?) =
            Customer(id = null, CustomerNumber.new(), CustomerName(firstName, lastName, middleName))
    }

    fun fullName(): String {
        return name.fullName()
    }
}
