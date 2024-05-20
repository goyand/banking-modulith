package jp.goyand.bankingmodulith.common.db

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import org.hibernate.HibernateException
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.IdentifierGenerator

class IdGenerator : IdentifierGenerator {
    @Throws(HibernateException::class)
    override fun generate(session: SharedSessionContractImplementor?, `object`: Any?): Any {
        return NanoIdUtils.randomNanoId()
    }
}
