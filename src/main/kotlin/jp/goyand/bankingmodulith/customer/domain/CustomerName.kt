package jp.goyand.bankingmodulith.customer.domain

class CustomerName(val firstName: String, val lastName: String, val middleName: String?) {

    fun fullName(): String {
        return if (middleName != null) {
            "$firstName $middleName $lastName"
        } else {
            "$firstName $lastName"
        }
    }
}
