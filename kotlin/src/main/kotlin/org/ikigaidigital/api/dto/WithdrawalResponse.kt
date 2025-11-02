package org.ikigaidigital.api.dto

import java.math.BigDecimal
import java.util.Date

data class WithdrawalResponse(
    val id: Int,
    val timeDepositId: Int,
    val amount: BigDecimal,
    val date: Date,
)