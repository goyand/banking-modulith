package jp.goyand.bankingmodulith.account.domain.value

import java.math.BigDecimal
import java.time.Instant
import jp.goyand.bankingmodulith.account.domain.entity.CurrencyType

class Balance(val currency: Currency, val datetime: Instant) {
    constructor(
        amount: BigDecimal,
        currency: CurrencyType,
        datetime: Instant,
    ) : this(Currency(amount, currency), datetime)
}
