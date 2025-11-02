package org.ikigaidigital.domain.service

import org.ikigaidigital.domain.model.TimeDeposit
import org.ikigaidigital.domain.model.Withdrawal
import org.ikigaidigital.domain.port.TimeDepositRepositoryPort
import org.ikigaidigital.domain.port.WithdrawalRepositoryPort
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class TimeDepositService(
    private val timeDepositRepository: TimeDepositRepositoryPort,
    private val withdrawalRepository: WithdrawalRepositoryPort,
    private val interestCalculator: InterestCalculator,
) {

    /**
     * Retrieves all deposits, calculates changes if applicable and saves results
     */
    fun applyMonthlyInterestToAll() {
        val deposits = timeDepositRepository.findAll().toMutableList()

        for (deposit in deposits) {
            val interest: BigDecimal = interestCalculator.calculateMonthlyInterest(deposit)
            val newBalance = BigDecimal.valueOf(deposit.balance).add(interest)
            deposit.balance = newBalance.toDouble()
        }

        timeDepositRepository.saveAll(deposits)
    }

    /**
     * Returns all time deposits with their withdrawals
     */
    fun getAllWithWithdrawals(): List<Pair<TimeDeposit, List<Withdrawal>>> {
        val deposits = timeDepositRepository.findAll()
        return deposits.map { deposit ->
            val withdrawals = withdrawalRepository.findByTimeDepositId(deposit.id)
            deposit to withdrawals
        }
    }
}
