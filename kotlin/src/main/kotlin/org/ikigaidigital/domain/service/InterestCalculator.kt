package org.ikigaidigital.domain.service

import org.ikigaidigital.domain.model.PlanType
import org.ikigaidigital.domain.model.TimeDeposit
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.math.RoundingMode

@Component
class InterestCalculator {

    // TODO: Reuse the existing class for it
    fun calculateMonthlyInterest(deposit: TimeDeposit): BigDecimal {
        val days = deposit.days
        val balance = BigDecimal.valueOf(deposit.balance)
        val planType = PlanType.valueOf(deposit.planType)

        val interest = when {
            // first 30 days: no interest for anybody
            days <= 30 -> BigDecimal.ZERO

            // student plan
            planType == PlanType.STUDENT && days <= DAYS_PER_YEAR ->
                calculatePercent(balance, STUDENT_PERCENT)

            // premium
            planType == PlanType.PREMIUM && days > PREMIUM_PERCENT_START ->
                calculatePercent(balance, PREMIUM_PERCENT)

            // basic
            planType == PlanType.BASIC ->
                calculatePercent(balance, BASIC_PERCENT)

            else -> BigDecimal.ZERO
        }

        // round HALF_UP to 2 decimals, like the original code
        return interest.setScale(2, RoundingMode.HALF_UP)
    }

    private fun calculatePercent(balance: BigDecimal, percent: Double): BigDecimal {
        return balance
            .multiply(BigDecimal(percent))
            .divide(BigDecimal(MONTHS_PER_YEAR), RoundingMode.HALF_UP)
    }

    private companion object {
        const val MONTHS_PER_YEAR = 12
        const val DAYS_PER_YEAR = 365
        const val PREMIUM_PERCENT_START = 45
        const val STUDENT_PERCENT = 0.03
        const val PREMIUM_PERCENT = 0.05
        const val BASIC_PERCENT = 0.01
    }
}
