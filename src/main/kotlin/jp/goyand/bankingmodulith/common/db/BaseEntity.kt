package jp.goyand.bankingmodulith.common.db

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import jakarta.persistence.Version
import java.io.Serializable
import java.time.Instant

@MappedSuperclass
abstract class BaseEntity(
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: Instant? = null,
    @Column(name = "created_by", nullable = false, updatable = false) var createdBy: String? = null,
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant? = null,
    @Column(name = "updated_by", nullable = false) var updatedBy: String? = null,
    @Version @Column(name = "version", nullable = false) var version: Instant = Instant.now(),
) : Serializable
