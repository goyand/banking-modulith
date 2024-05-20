package jp.goyand.bankingmodulith.customer.domain.value

import jp.goyand.bankingmodulith.common.entity.NumberGenerator

class CustomerNumber(val value: String) {
    init {
        require(value.isNotBlank()) { "Customer number must not be blank" }
    }

    companion object {
        fun new(): CustomerNumber {
            return CustomerNumber(NumberGenerator.generateNumber("C"))
        }
    }
}
