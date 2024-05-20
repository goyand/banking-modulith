package jp.goyand.bankingmodulith.customer.infrastructure.db.mapper

import jp.goyand.bankingmodulith.customer.domain.aggregate.Customer
import jp.goyand.bankingmodulith.customer.infrastructure.db.entity.CustomerEntity
import org.springframework.stereotype.Component

@Component
class CustomerDbMapper {
    fun toEntity(model: Customer): CustomerEntity {
        return CustomerEntity(
            customerNumber = model.customerNumber.value,
            firstName = model.name.firstName,
            lastName = model.name.lastName,
            middleName = model.name.middleName,
        )
    }

    fun toModel(entity: CustomerEntity): Customer {
        return Customer.create(
            id = entity.id,
            customerNumber = entity.customerNumber,
            firstName = entity.firstName,
            lastName = entity.lastName,
            middleName = entity.middleName,
        )
    }
}
