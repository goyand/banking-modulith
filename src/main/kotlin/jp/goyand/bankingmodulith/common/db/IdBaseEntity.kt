package jp.goyand.bankingmodulith.common.db

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@MappedSuperclass
abstract class IdBaseEntity(
    @Id
    @GenericGenerator(name = "id", strategy = "jp.goyand.bankingmodulith.common.db.IdGenerator")
    @GeneratedValue(generator = "id")
    @Column(name = "id", nullable = false, updatable = false)
    var id: String? = null
) : BaseEntity()
