package org.ikigaidigital.domain.service

import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat
import org.ikigaidigital.TimeDepositApplication
import org.ikigaidigital.infrastructure.persistence.entity.TimeDepositEntity
import org.ikigaidigital.infrastructure.persistence.repository.TimeDepositRepository
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import java.math.BigDecimal

@SpringBootTest(classes = [TimeDepositApplication::class])
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Disabled
class TimeDepositServiceIntegrationTest {

    companion object {

        @ServiceConnection
        @Container
        @JvmStatic
        val postgres = PostgreSQLContainer<Nothing>("postgres:15-alpine").apply {
            withDatabaseName("testdb")
            withUsername("test")
            withPassword("test")
        }

        @JvmStatic
        @DynamicPropertySource
        fun registerDataSource(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { postgres.jdbcUrl }
            registry.add("spring.datasource.username") { postgres.username }
            registry.add("spring.datasource.password") { postgres.password }
            registry.add("spring.datasource.driver-class-name") { postgres.driverClassName }
            registry.add("spring.jpa.hibernate.ddl-auto") { "create-drop" }
            registry.add("spring.h2.console.enabled") { "false" }
            registry.add("spring.jpa.show-sql") { "false" }
        }
    }

    @Autowired
    private lateinit var repo: TimeDepositRepository

    @Autowired
    private lateinit var service: TimeDepositService

    @BeforeEach
    fun seedData() {
        repo.deleteAll() // clean slate each test

        repo.saveAll(
            listOf(
                TimeDepositEntity(
                    id = 0,
                    planType = "basic",
                    days = 60,
                    balance = BigDecimal("2000.00")
                ),
                TimeDepositEntity(
                    id = 0,
                    planType = "student",
                    days = 10,
                    balance = BigDecimal("5000.00")
                )
            )
        )
    }

    @Test
    fun `applyMonthlyInterestToAll updates balances correctly`() {
        service.applyMonthlyInterestToAll()

        val deposits = repo.findAll()
        val basic = deposits.first { it.planType == "basic" }
        val student = deposits.first { it.planType == "student" }

        assertThat(basic.balance).isEqualByComparingTo("2001.67")
        assertThat(student.balance).isEqualByComparingTo("5000.00")
    }
}