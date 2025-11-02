package org.ikigaidigital

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class TimeDepositApplication

fun main(args: Array<String>) {
    runApplication<TimeDepositApplication>(*args)
}