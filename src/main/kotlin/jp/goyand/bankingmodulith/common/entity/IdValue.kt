package jp.goyand.bankingmodulith.common.entity

import com.aventrix.jnanoid.jnanoid.NanoIdUtils

abstract class IdValue(private val value: String) {
    init {
        require(value.isNotBlank()) { "Id must not be blank" }
    }

    companion object {
        fun generate(): String {
            return NanoIdUtils.randomNanoId()
        }
    }
}
