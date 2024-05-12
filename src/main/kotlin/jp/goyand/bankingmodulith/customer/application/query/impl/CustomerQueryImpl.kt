package jp.goyand.bankingmodulith.customer.application.query.impl

import jp.goyand.bankingmodulith.customer.application.dto.CustomerDto
import jp.goyand.bankingmodulith.customer.application.port.db.CustomerQueryRepository
import jp.goyand.bankingmodulith.customer.application.query.CustomerQuery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CustomerQueryImpl(@Autowired private val customerQueryRepository: CustomerQueryRepository) :
    CustomerQuery {
    override fun getCustomer(customerId: String): CustomerDto? {
        return customerQueryRepository.getCustomer(customerId)
    }
}
