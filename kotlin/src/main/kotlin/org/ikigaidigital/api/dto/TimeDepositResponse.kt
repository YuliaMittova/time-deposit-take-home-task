package org.ikigaidigital.api.dto

data class TimeDepositResponse(
    val id: Int,
    val planType: String,
    val balance: Double,
    val days: Int,
    val withdrawals: List<WithdrawalResponse>
)