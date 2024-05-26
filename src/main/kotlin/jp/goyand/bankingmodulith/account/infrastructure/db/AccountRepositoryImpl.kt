package jp.goyand.bankingmodulith.account.infrastructure.db

import jp.goyand.bankingmodulith.account.application.port.db.AccountRepository
import jp.goyand.bankingmodulith.account.domain.aggregate.Account
import jp.goyand.bankingmodulith.account.infrastructure.db.jpa.AccountJpaRepository
import jp.goyand.bankingmodulith.account.infrastructure.db.mapper.AccountDbMapper
import org.springframework.stereotype.Repository

@Repository
class AccountRepositoryImpl(
    private val accountDbMapper: AccountDbMapper,
    private val accountJpaRepository: AccountJpaRepository,
) : AccountRepository {
    override fun save(account: Account): Account {
        return accountDbMapper
            .toEntity(account)
            .let { accountJpaRepository.save(it) }
            .let { accountDbMapper.toModel(it) }
    }
}
