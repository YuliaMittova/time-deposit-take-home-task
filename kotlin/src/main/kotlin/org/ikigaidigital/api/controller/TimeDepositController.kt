package org.ikigaidigital.api.controller

import org.ikigaidigital.api.dto.TimeDepositResponse
import org.ikigaidigital.api.dto.WithdrawalResponse
import org.ikigaidigital.domain.service.TimeDepositService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/time-deposits")
class TimeDepositController(
    private val service: TimeDepositService
) {

    @GetMapping
    fun getAll(): List<TimeDepositResponse> {
        return service.getAllWithWithdrawals().map { (deposit, withdrawals) ->
            TimeDepositResponse(
                id = deposit.id,
                planType = deposit.planType,
                balance = deposit.balance,
                days = deposit.days,
                withdrawals = withdrawals.map {
                    WithdrawalResponse(
                        id = it.id,
                        timeDepositId = it.timeDepositId,
                        amount = it.amount,
                        date = it.date
                    )
                }
            )
        }
    }

    @PostMapping("/apply-interest")
    fun applyMonthlyInterest() {
        service.applyMonthlyInterestToAll()
    }
}