package org.ikigaidigital.domain.model

enum class PlanType(val typeName: String) {
    STUDENT("student"),
    PREMIUM("premium"),
    BASIC("basic");

    companion object {
        fun valueOf(type: String): PlanType? {
            when (type.lowercase()) {
                BASIC.typeName -> return BASIC
                STUDENT.typeName -> return STUDENT
                PREMIUM.typeName -> return PREMIUM
            }
            return null
        }
    }
}