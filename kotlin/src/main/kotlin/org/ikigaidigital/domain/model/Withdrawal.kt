package org.ikigaidigital.domain.model

import java.math.BigDecimal
import java.time.LocalDate

data class Withdrawal(
    val id: Int, // Int is not enough for ID, better to use UUID or something composite
    val timeDepositId: Int,
    val amount: BigDecimal, // BigDecimal better suits financial calculations
    val date: LocalDate
)