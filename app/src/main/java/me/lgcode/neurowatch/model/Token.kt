package me.lgcode.neurowatch.model

import me.lgcode.neurowatch.db.TokenEntity

data class Token(val token: String)

fun Token.toEntity() = TokenEntity(token = token)

fun TokenEntity.toModel() = Token(token = token)