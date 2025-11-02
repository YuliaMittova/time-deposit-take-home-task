package org.ikigaidigital.domain.model

import java.math.BigDecimal
import java.util.Date

data class Withdrawal(
    val id: Int, // Int is not enough for ID, better to use UUID or something composite
    val timeDepositId: Int,
    val amount: BigDecimal, // BigDecimal better suits financial calculations
    val date: Date,
)