package jp.goyand.bankingmodulith.account.domain.value

import jp.goyand.bankingmodulith.common.entity.IdValue

class AccountId(val value: String) : IdValue(value) {
    init {
        require(value.isNotBlank()) { "Customer number must not be blank" }
    }
}
