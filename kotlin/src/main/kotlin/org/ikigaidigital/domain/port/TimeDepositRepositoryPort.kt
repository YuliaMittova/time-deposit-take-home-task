package org.ikigaidigital.domain.port

import org.ikigaidigital.domain.model.TimeDeposit

interface TimeDepositRepositoryPort {
    fun findAll(): List<TimeDeposit>
    fun saveAll(deposits: List<TimeDeposit>)
}