package me.lgcode.neurowatch.api

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.lang.reflect.Type

class DateTypeAdapter: JsonDeserializer<LocalDateTime> {

    private val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault())

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): LocalDateTime {
        return LocalDateTime.parse(json.asString, dateFormat)
    }
}