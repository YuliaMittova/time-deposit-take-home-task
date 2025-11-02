package org.ikigaidigital.domain.model

data class TimeDeposit(
    val id: Int,
    val planType: String,
    var balance: Double, // it is not OK to have var in this case, but we are not allowed to change this class
    val days: Int
)