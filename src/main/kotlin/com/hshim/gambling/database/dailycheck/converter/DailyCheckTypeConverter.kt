package com.hshim.gambling.database.dailycheck.converter

import com.hshim.gambling.enums.dailycheck.DailyCheckType
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = false)
class DailyCheckTypeConverter : AttributeConverter<List<DailyCheckType>, String> {
    override fun convertToDatabaseColumn(attribute: List<DailyCheckType>): String {
        return attribute.joinToString(separator = ",") { it.name }
    }

    override fun convertToEntityAttribute(dbData: String): List<DailyCheckType> {
        return dbData.split(",").map { DailyCheckType.valueOf(it) }
    }
}