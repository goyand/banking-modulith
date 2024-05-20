package jp.goyand.bankingmodulith.common.entity

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

object NumberGenerator {
    private val NUMBER_STORE: ConcurrentHashMap<String, AtomicLong> = ConcurrentHashMap()

    fun generateNumber(prefix: String): String {
        return NUMBER_STORE.computeIfAbsent(prefix) { AtomicLong(0) }
            .incrementAndGet()
            .let { "$prefix-${String.format("%07d", it)}" }
    }
}
