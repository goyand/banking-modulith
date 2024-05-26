package jp.goyand.bankingmodulith.account.domain.value

import jp.goyand.bankingmodulith.common.entity.NumberGenerator

class AccountNumber(val value: String) {
    init {
        require(value.isNotBlank()) { "Customer number must not be blank" }
    }

    companion object {
        fun new(): AccountNumber {
            return AccountNumber(NumberGenerator.generateNumber("A"))
        }
    }
}
