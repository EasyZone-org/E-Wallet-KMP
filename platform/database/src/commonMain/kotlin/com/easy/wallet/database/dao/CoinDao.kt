package com.easy.wallet.database.dao

import com.easy.wallet.database.CoinEntity
import com.easy.wallet.model.asset.AssetCoin
import com.easy.wallet.model.asset.BasicCoin
import kotlinx.coroutines.flow.Flow

interface CoinDao {
    suspend fun insert(vararg coins: CoinEntity)

    suspend fun findCoinById(id: String): BasicCoin?

    suspend fun findAllCoin(): List<BasicCoin>

    fun findAllStream(): Flow<List<BasicCoin>>

    fun findAllByPlatformStream(platformId: String): Flow<List<BasicCoin>>
}
