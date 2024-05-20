package jp.goyand.bankingmodulith.common.db

import jakarta.annotation.PostConstruct
import jakarta.persistence.EntityManagerFactory
import java.time.Instant
import org.hibernate.event.service.spi.EventListenerRegistry
import org.hibernate.event.spi.*
import org.hibernate.internal.SessionFactoryImpl
import org.slf4j.Logger
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
class EntityListener(private val logger: Logger) : PreInsertEventListener, PreUpdateEventListener {
    override fun onPreInsert(event: PreInsertEvent?): Boolean {
        event?.entity?.let {
            if (it is BaseEntity) {

                if (it.createdAt == null) {
                    val now = Instant.now()
                    val operator = "system"
                    val propertyNames = event.persister.entityMetamodel.propertyNames
                    val state = event.state
                    // inserts
                    it.createdAt = now
                    it.createdBy = operator
                    it.updatedAt = now
                    it.updatedBy = operator
                    setValue(state, propertyNames, "createdAt", now, it)
                    setValue(state, propertyNames, "createdBy", operator, it)
                    setValue(state, propertyNames, "updatedAt", now, it)
                    setValue(state, propertyNames, "updatedBy", operator, it)
                }
            }
        }
        return false
    }

    override fun onPreUpdate(event: PreUpdateEvent?): Boolean {
        event?.entity?.let {
            if (it is BaseEntity) {
                val now = Instant.now()
                val operator = "system"
                val propertyNames = event.persister.entityMetamodel.propertyNames
                val state = event.state
                // inserts
                setValue(state, propertyNames, "createdAt", it.createdAt, it)
                setValue(state, propertyNames, "createdBy", it.createdBy, it)
                // updates
                it.updatedAt = now
                it.updatedBy = operator
                setValue(state, propertyNames, "updatedAt", now, it)
                setValue(state, propertyNames, "updatedBy", operator, it)
            }
        }
        return false
    }

    private fun setValue(
        state: Array<Any?>,
        propertyNames: Array<String>,
        propertyName: String,
        value: Any?,
        entity: Any,
    ) {
        val index = propertyNames.indexOf(propertyName)
        if (index >= 0) {
            state[index] = value
        } else {
            logger.error("Field '$propertyName' not found on entity '${entity.javaClass}'.")
        }
    }
}

@Configuration
class EntityListenerConfig(
    private val entityManagerFactory: EntityManagerFactory,
    private val eventListener: EntityListener,
) {

    @PostConstruct
    fun init() {
        entityManagerFactory.unwrap(SessionFactoryImpl::class.java).let { sessionFactory ->
            sessionFactory.serviceRegistry.getService(EventListenerRegistry::class.java)?.also {
                it.appendListeners(EventType.PRE_INSERT, eventListener)
                it.appendListeners(EventType.PRE_UPDATE, eventListener)
            }
        }
    }
}
