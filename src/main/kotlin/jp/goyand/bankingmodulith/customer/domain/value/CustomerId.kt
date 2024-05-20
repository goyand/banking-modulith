package jp.goyand.bankingmodulith.customer.domain.value

import jp.goyand.bankingmodulith.common.entity.IdValue

class CustomerId(val value: String) : IdValue(value) {
    init {
        require(value.isNotBlank()) { "Customer number must not be blank" }
    }
}
