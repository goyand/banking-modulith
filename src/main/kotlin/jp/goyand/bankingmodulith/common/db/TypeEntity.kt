package jp.goyand.bankingmodulith.common.db

import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.io.Serializable

@MappedSuperclass
abstract class TypeEntity(
    @Id @Column(name = "code", nullable = false) val code: String? = null,
    @Column(name = "description") val description: String? = null,
) : Serializable
