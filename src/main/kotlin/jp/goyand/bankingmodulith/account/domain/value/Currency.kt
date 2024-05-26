package jp.goyand.bankingmodulith.account.domain.value

import java.math.BigDecimal
import jp.goyand.bankingmodulith.account.domain.entity.CurrencyType

class Currency(private val amount: BigDecimal, private val currency: CurrencyType) {
    init {
        require(amount >= BigDecimal.ZERO) { "Amount must be greater than or equal to zero" }
        require(amount.scale() <= currency.fractionDigits) {
            "Amount scale must be less than or equal to ${currency.fractionDigits}"
        }
    }

    fun amount(): BigDecimal {
        return amount
    }

    fun currency(): CurrencyType {
        return currency
    }

    fun add(amount: BigDecimal): Currency {
        require(amount.scale() <= currency.fractionDigits) {
            "Amount scale must be less than or equal to ${currency.fractionDigits}"
        }
        return Currency(this.amount + amount, currency)
    }

    fun subtract(amount: BigDecimal): Currency {
        require(amount.scale() <= currency.fractionDigits) {
            "Amount scale must be less than or equal to ${currency.fractionDigits}"
        }
        return Currency(this.amount - amount, currency)
    }

    fun multiply(amount: BigDecimal): Currency {
        return Currency(this.amount * amount, currency)
    }

    fun divide(amount: BigDecimal): Currency {
        return Currency(this.amount / amount, currency)
    }

    fun isGreaterThan(other: Currency): Boolean {
        return this.amount > other.amount
    }

    fun isGreaterThanOrEqual(other: Currency): Boolean {
        return this.amount >= other.amount
    }

    fun isLessThan(other: Currency): Boolean {
        return this.amount < other.amount
    }

    fun isLessThanOrEqual(other: Currency): Boolean {
        return this.amount <= other.amount
    }

    fun isZero(): Boolean {
        return this.amount == BigDecimal.ZERO
    }

    fun isPositive(): Boolean {
        return this.amount > BigDecimal.ZERO
    }

    fun isNegative(): Boolean {
        return this.amount < BigDecimal.ZERO
    }

    fun isSameCurrency(other: Currency): Boolean {
        return this.currency == other.currency
    }

    fun isSameCurrency(other: CurrencyType): Boolean {
        return this.currency == other
    }
}
