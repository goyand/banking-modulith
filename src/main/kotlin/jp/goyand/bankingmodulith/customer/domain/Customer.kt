package jp.goyand.bankingmodulith.customer.domain

class Customer
private constructor(val id: String, val customerNumber: String, val name: CustomerName) {

    companion object {
        fun create(id: String, customerNumber: String, name: CustomerName): Customer {
            return Customer(id, customerNumber, name)
        }
    }

    fun fullName(): String {
        return name.fullName()
    }
}
