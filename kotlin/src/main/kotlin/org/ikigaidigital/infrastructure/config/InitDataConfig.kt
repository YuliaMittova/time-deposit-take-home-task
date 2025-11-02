package org.ikigaidigital.infrastructure.config

import org.ikigaidigital.infrastructure.persistence.entity.TimeDepositEntity
import org.ikigaidigital.infrastructure.persistence.entity.WithdrawalEntity
import org.ikigaidigital.infrastructure.persistence.repository.TimeDepositRepository
import org.ikigaidigital.infrastructure.persistence.repository.WithdrawalRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.math.BigDecimal
import java.time.LocalDate

/**
 * For testing purposes I prefill data on startup instead of exposing a seeding API.
 * In production this would be done via migrations (if needed).
 */
@Configuration
open class InitDataConfig {


    @Bean
    open fun initDatabase(
        depositsRepo: TimeDepositRepository,
        withdrawalsRepo: WithdrawalRepository,
    ) = CommandLineRunner {

        // only seed if empty, to avoid duplicates if app restarts
        if (depositsRepo.count() == 0L) {

            depositsRepo.saveAll(
                listOf(
                    TimeDepositEntity(1, "basic", 10, BigDecimal("1000.00")),
                    TimeDepositEntity(2, "basic", 60, BigDecimal("2000.00")),
                    TimeDepositEntity(3, "student", 120, BigDecimal("5000.00")),
                    TimeDepositEntity(4, "student", 400, BigDecimal("8000.00")),
                    TimeDepositEntity(5, "premium", 40, BigDecimal("10000.00")),
                    TimeDepositEntity(6, "premium", 90, BigDecimal("15000.00"))
                )
            )

            withdrawalsRepo.saveAll(
                listOf(
                    WithdrawalEntity(
                        id = null,
                        timeDepositId = 2,
                        amount = BigDecimal("100.00"),
                        date = LocalDate.parse("2025-10-15")
                    ),
                    WithdrawalEntity(
                        id = null,
                        timeDepositId = 2,
                        amount = BigDecimal("50.00"),
                        date = LocalDate.parse("2025-10-20")
                    ),
                    WithdrawalEntity(
                        id = null,
                        timeDepositId = 3,
                        amount = BigDecimal("500.00"),
                        date = LocalDate.parse("2025-10-30")
                    )
                )
            )
        }
    }
}