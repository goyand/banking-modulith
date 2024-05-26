package jp.goyand.bankingmodulith.account.application.usecase.impl

import jp.goyand.bankingmodulith.account.application.dto.AccountDto
import jp.goyand.bankingmodulith.account.application.port.db.AccountRepository
import jp.goyand.bankingmodulith.account.application.usecase.CreateAccountUseCase
import jp.goyand.bankingmodulith.account.domain.event.AccountCreated
import jp.goyand.bankingmodulith.account.domain.factory.AccountFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class CreateAccountInteractor(
    private val accountRepository: AccountRepository,
    private val events: ApplicationEventPublisher,
) : CreateAccountUseCase {
    override fun execute(customerId: String): AccountDto {
        return AccountFactory.createAccount(customerId, "Default Account")
            .let { accountRepository.save(it) }
            .let {
                events.publishEvent(AccountCreated(it.id!!.value))
                AccountDto(
                    id = it.id.value,
                    accountNumber = it.accountNumber.value,
                    customerId = it.role!!.customerId.value,
                    balance = it.balance!!.currency.amount(),
                    currency = it.balance.currency.currency().name,
                )
            }
    }
}
