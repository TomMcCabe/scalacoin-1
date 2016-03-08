package org.scalacoin.crypto

import org.bitcoinj.core.{ECKey, DumpedPrivateKey}
import org.scalacoin.config.TestNet3
import org.scalacoin.util.{BitcoinSUtil, BitcoinJTestUtil, CryptoTestUtil, TestUtil}
import org.scalatest.{MustMatchers, FlatSpec}

/**
 * Created by chris on 3/7/16.
 */
class ECFactoryTest extends FlatSpec with MustMatchers {

    "ECFactory" must "create a private key from the dumped base58 in bitcoin-cli" in {
      val privateKeyBase58 = CryptoTestUtil.privateKeyBase58
      val bitcoinjDumpedPrivateKey = new DumpedPrivateKey(BitcoinJTestUtil.params,privateKeyBase58)
      val bitcoinjPrivateKey = bitcoinjDumpedPrivateKey.getKey
      val privateKey = ECFactory.fromBase58ToPrivateKey(privateKeyBase58,TestNet3)

      privateKey.hex must be (bitcoinjPrivateKey.getPrivateKeyAsHex)

    }

    it must "create a private key from a sequence of bytes that has the same byte representation of bitcoinj ECKeys" in {
      val bytes = CryptoTestUtil.bitcoinjPrivateKey.getPrivKeyBytes.toList
      val bitcoinJKey = ECKey.fromPrivate(bytes.toArray)
      val privateKey : ECPrivateKey = ECFactory.privateKey(bytes)
      privateKey.hex must be (bitcoinJKey.getPrivateKeyAsHex)
    }


}