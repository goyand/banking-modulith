package jp.goyand.bankingmodulith.account.domain.value

class AccountName(val name: String) {
    init {
        require(name.isNotBlank()) { "Account name must not be blank" }
    }
}
