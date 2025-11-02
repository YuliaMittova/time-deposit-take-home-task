package org.ikigaidigital.api.dto

import java.math.BigDecimal
import java.time.LocalDate

data class WithdrawalResponse(
    val id: Int,
    val timeDepositId: Int,
    val amount: BigDecimal,
    val date: LocalDate
)