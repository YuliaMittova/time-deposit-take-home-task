package org.ikigaidigital.infrastructure.persistence.entity

import java.math.BigDecimal
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "withdrawals")
data class WithdrawalEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "timeDepositId")
    val timeDepositId: Int,
    val amount: BigDecimal,
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