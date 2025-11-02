package org.ikigaidigital.infrastructure.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "timeDeposits")
class TimeDepositEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Int,
    @Column(name = "planType")
    val planType: String,
    @Column(name = "days")
    val days: Int,
    @Column(name = "balance")
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