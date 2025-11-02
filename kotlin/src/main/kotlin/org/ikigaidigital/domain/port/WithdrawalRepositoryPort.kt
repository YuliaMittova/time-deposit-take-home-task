package org.ikigaidigital.domain.port

import org.ikigaidigital.domain.model.Withdrawal

interface WithdrawalRepositoryPort {
    fun findByTimeDepositId(timeDepositId: Int): List<Withdrawal>
}