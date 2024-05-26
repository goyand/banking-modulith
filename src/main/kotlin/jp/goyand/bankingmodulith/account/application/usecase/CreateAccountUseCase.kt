package jp.goyand.bankingmodulith.account.application.usecase

import jp.goyand.bankingmodulith.account.application.dto.AccountDto

interface CreateAccountUseCase {
    fun execute(customerId: String): AccountDto
}
