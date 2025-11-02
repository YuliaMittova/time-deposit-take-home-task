package org.ikigaidigital.domain.service

import org.assertj.core.api.Assertions.assertThat
import org.ikigaidigital.domain.model.TimeDeposit
import org.junit.jupiter.api.Test

class TimeDepositCalculatorTest {

    @Test
    fun `when deposit time is less than 30 days then no interest should be applied`() {
        val calc = TimeDepositCalculator()
        val initialBalance = 1000.00

        val plans = listOf(TimeDeposit(1, "basic", initialBalance, 10))
        calc.updateBalance(plans)

        // balance should be the same
        assertThat(initialBalance).isEqualTo(plans[0].balance)
    }

    @Test
    fun `when basic deposit was hold for more than 30 days then 1 percent interest should be applied`() {
        val calc = TimeDepositCalculator()

        val plans = listOf(TimeDeposit(1, "basic", 2000.00, 60))
        calc.updateBalance(plans)

        // balance should be the same
        assertThat(2001.67).isEqualTo(plans[0].balance)
    }

    @Test
    fun `when student deposit was hold for more than 30 days but less than a year then 3 percent interest should be applied`() {
        val calc = TimeDepositCalculator()

        val plans = listOf(TimeDeposit(1, "student", 5000.00, 120))
        calc.updateBalance(plans)

        // balance should be the same
        assertThat(5012.5).isEqualTo(plans[0].balance)
    }

    @Test
    fun `when student deposit was hold for more than a year then no interest should be applied`() {
        val calc = TimeDepositCalculator()

        val plans = listOf(TimeDeposit(1, "student", 8000.00, 400))
        calc.updateBalance(plans)

        // balance should be the same
        assertThat(8000.0).isEqualTo(plans[0].balance)
    }

    @Test
    fun `when premium deposit was hold for less than 45 days then no interest should be applied`() {
        val calc = TimeDepositCalculator()

        val plans = listOf(TimeDeposit(1, "premium", 10000.00, 40))
        calc.updateBalance(plans)

        // balance should be the same
        assertThat(10000.0).isEqualTo(plans[0].balance)
    }

    @Test
    fun `when premium deposit was hold for more than 45 days then 5 percent interest should be applied`() {
        val calc = TimeDepositCalculator()

        val plans = listOf(TimeDeposit(1, "premium", 15000.00, 90))
        calc.updateBalance(plans)

        // balance should be the same
        assertThat(15062.5).isEqualTo(plans[0].balance)
    }
}
