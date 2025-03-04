package com.easy.wallet.shared.data.multiwallet

import com.easy.wallet.database.dao.WalletDao
import com.easy.wallet.model.Wallet
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow

class MultiWalletRepository internal constructor(
  private val walletDao: WalletDao
) {
  @NativeCoroutines
  suspend fun insertOne(
    mnemonic: String,
    passphrase: String,
    onCompleted: () -> Unit = {}
  ) {
    walletDao.insertWithCallback(
      mnemonic,
      passphrase,
      onCompleted
    )
  }

  @NativeCoroutines
  fun forActiveOneStream(): Flow<Wallet?> = walletDao.findActiveOneStream()

  @NativeCoroutines
  fun findWalletStream(): Flow<Wallet?> = walletDao.findActiveOneStream()

  @NativeCoroutines
  suspend fun findWallet(): Wallet? = walletDao.findActiveOne()

  @NativeCoroutines
  suspend fun forActiveOne(): Wallet? = walletDao.findActiveOne()
}
