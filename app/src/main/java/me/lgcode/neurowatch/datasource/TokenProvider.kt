package me.lgcode.neurowatch.datasource

import me.lgcode.neurowatch.db.TokenDao
import me.lgcode.neurowatch.model.Token
import me.lgcode.neurowatch.model.toModel
import javax.inject.Inject

class TokenProvider @Inject constructor(
    private val tokenDao: TokenDao
) {
    suspend fun getToken(): Token? {
        return tokenDao.get().toModel()
    }
}