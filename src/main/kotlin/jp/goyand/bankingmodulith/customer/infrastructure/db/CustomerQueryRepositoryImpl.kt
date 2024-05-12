package jp.goyand.bankingmodulith.customer.infrastructure.db

import jp.goyand.bankingmodulith.customer.application.dto.CustomerDto
import jp.goyand.bankingmodulith.customer.application.port.db.CustomerQueryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository

@Repository
class CustomerQueryRepositoryImpl(
    @Autowired private val jdbcOperations: NamedParameterJdbcOperations
) : CustomerQueryRepository {
    override fun getCustomer(customerId: String): CustomerDto? {
        val stmt = """SELECT * FROM customers WHERE id = :id""".trimMargin()
        return jdbcOperations
            .query(stmt, mapOf("id" to customerId)) { rs, _ ->
                CustomerDto(
                    id = rs.getString("id"),
                    customerNumber = rs.getString("customer_number"),
                    firstName = rs.getString("first_name"),
                    lastName = rs.getString("last_name"),
                    middleName = rs.getString("middle_name"),
                )
            }
            .firstOrNull()
    }
}
