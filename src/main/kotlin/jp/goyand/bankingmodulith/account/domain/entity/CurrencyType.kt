package jp.goyand.bankingmodulith.account.domain.entity

import java.util.Currency

enum class CurrencyType(
    val currency: Currency,
    val fractionDigits: Int = currency.defaultFractionDigits,
) {
    USD(Currency.getInstance("USD")),
    JPY(Currency.getInstance("JPY")),
}
