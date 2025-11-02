package org.ikigaidigital.infrastructure.persistence.entity

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "timeDeposits")
data class TimeDepositEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val planType: String,
    val days: Int,
    val balance: BigDecimal
) {

    // JPA / Hibernate requires a no-arg constructor so it can instantiate
    // the entity via reflection. In Kotlin this is usually handled by the kotlin-jpa
    // no-arg compiler plugin, but to save time I'm providing an explicit protected no-arg constructor here.
    private constructor() : this(
        id = 0,
        planType = "",
        days = 0,
        balance = BigDecimal.ZERO,
    )
}