package jp.goyand.bankingmodulith.account.application.port.db

import jp.goyand.bankingmodulith.account.domain.aggregate.Account

interface AccountRepository {
    fun save(account: Account): Account
}
