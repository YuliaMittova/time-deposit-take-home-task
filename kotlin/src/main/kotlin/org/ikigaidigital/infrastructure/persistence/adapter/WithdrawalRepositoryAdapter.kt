package org.ikigaidigital.infrastructure.persistence.adapter

import org.ikigaidigital.domain.model.Withdrawal
import org.ikigaidigital.domain.port.WithdrawalRepositoryPort
import org.ikigaidigital.infrastructure.persistence.repository.WithdrawalRepository
import org.springframework.stereotype.Repository

/**
 * Implementation of WithdrawalRepositoryPort
 */
@Repository
open class WithdrawalRepositoryAdapter(
    private val repository: WithdrawalRepository
) : WithdrawalRepositoryPort {

    override fun findByTimeDepositId(timeDepositId: Int): List<Withdrawal> =
        repository.findByTimeDepositId(timeDepositId).map { entity ->
            Withdrawal(
                id = entity.id ?: 0,
                timeDepositId = entity.timeDepositId,
                amount = entity.amount,
                date = entity.date
            )
        }
}