package org.ikigaidigital.infrastructure.persistence.adapter

import org.ikigaidigital.domain.model.TimeDeposit
import org.ikigaidigital.domain.port.TimeDepositRepositoryPort
import org.ikigaidigital.infrastructure.persistence.entity.TimeDepositEntity
import org.ikigaidigital.infrastructure.persistence.repository.TimeDepositRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal

/**
 * Implementation of TimeDepositRepositoryPort
 */
@Repository
open class TimeDepositRepositoryAdapter(
    private val repository: TimeDepositRepository
) : TimeDepositRepositoryPort {

    override fun findAll(): List<TimeDeposit> =
        repository.findAll().map {
            TimeDeposit(
                id = it.id,
                planType = it.planType,
                balance = it.balance.toDouble(),
                days = it.days,
            )
        }

    override fun saveAll(deposits: List<TimeDeposit>) {
        val entities = deposits.map {
            TimeDepositEntity(
                id = it.id,
                planType = it.planType,
                days = it.days,
                balance = BigDecimal.valueOf(it.balance)
            )
        }
        repository.saveAll(entities)
    }
}
