package org.ikigaidigital.infrastructure.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import java.math.BigDecimal
import java.util.Date

@Entity
@Table(name = "withdrawals")
class WithdrawalEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Int? = null,
    @Column(name = "timeDepositId")
    val timeDepositId: Int,
    @Column(name = "amount")
    val amount: BigDecimal,
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    val date: Date,
) {

    // JPA / Hibernate requires a no-arg constructor so it can instantiate
    // the entity via reflection. In Kotlin this is usually handled by the kotlin-jpa
    // no-arg compiler plugin, but to save time I'm providing an explicit protected no-arg constructor here.
    private constructor() : this(
        id = null,
        timeDepositId = 0,
        amount = BigDecimal.ZERO,
        date = Date(),
    )
}