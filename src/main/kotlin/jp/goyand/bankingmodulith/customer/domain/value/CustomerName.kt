package jp.goyand.bankingmodulith.customer.domain.value

class CustomerName(val firstName: String, val lastName: String, val middleName: String?) {

    init {
        require(firstName.isNotBlank()) { "First name must not be blank" }
        require(lastName.isNotBlank()) { "Last name must not be blank" }
    }

    fun fullName(): String {
        return if (middleName != null) {
            "$firstName $middleName $lastName"
        } else {
            "$firstName $lastName"
        }
    }
}
