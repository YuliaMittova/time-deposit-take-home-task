package org.ikigaidigital.infrastructure.persistence.repository

import org.ikigaidigital.infrastructure.persistence.entity.WithdrawalEntity
import org.springframework.data.jpa.repository.JpaRepository

interface WithdrawalRepository : JpaRepository<WithdrawalEntity, Int> {
    fun findByTimeDepositId(timeDepositId: Int): List<WithdrawalEntity>
}