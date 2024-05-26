package jp.goyand.bankingmodulith.account.infrastructure.db.jpa

import jp.goyand.bankingmodulith.account.infrastructure.db.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AccountJpaRepository : JpaRepository<AccountEntity, String> {}
