package org.scalacoin.marshallers.transaction

import org.scalacoin.currency.{Satoshis, CurrencyUnits, Bitcoins}
import org.scalacoin.protocol.P2SH
import org.scalacoin.protocol.transaction.TransactionOutput
import org.scalacoin.script.bitwise.OP_EQUAL
import org.scalacoin.script.constant.{BytesToPushOntoStackImpl, ScriptConstantImpl}
import org.scalacoin.script.crypto.OP_HASH160
import org.scalatest.{MustMatchers, FlatSpec}

/**
 * Created by chris on 1/11/16.
 * https://bitcoin.org/en/developer-reference#txout
 */
class RawTransactionOutputParserTest extends FlatSpec with MustMatchers with RawTransactionOutputParser {

  //txid cad1082e674a7bd3bc9ab1bc7804ba8a57523607c876b8eb2cbe645f2b1803d6
  val rawTxOutput = "02204e00000000000017a914eda8ae08b5c9f973f49543e90a7c292367b3337c87" +
    "197d2d000000000017a914be2319b9060429692ebeffaa3be38497dc5380c887"
  "RawTransactionOutputTest" must "read a serialized tx output" in {

    val txOutput : Seq[TransactionOutput] = read(rawTxOutput)
    val firstOutput = txOutput.head
    val secondOutput = txOutput(1)
    firstOutput.value must be (CurrencyUnits.toSatoshis(Bitcoins(0.0002)))
    secondOutput.value must be (CurrencyUnits.toSatoshis(Bitcoins(0.02981145)))
    firstOutput.scriptPubKey.asm must be (Seq(OP_HASH160, BytesToPushOntoStackImpl(20),ScriptConstantImpl("eda8ae08b5c9f973f49543e90a7c292367b3337c"), OP_EQUAL))
    secondOutput.scriptPubKey.asm must be (Seq(OP_HASH160,BytesToPushOntoStackImpl(20), ScriptConstantImpl("be2319b9060429692ebeffaa3be38497dc5380c8"), OP_EQUAL))
    firstOutput.scriptPubKey.addressType must be (P2SH)
    secondOutput.scriptPubKey.addressType must be (P2SH)
  }

  it must "seralialize a transaction output" in {
    val txOutput = read(rawTxOutput)
    write(txOutput) must be (rawTxOutput)
  }

  it must "serialize a single transaction output not in a sequence" in {
    val txOutputs = read(rawTxOutput)
    write(txOutputs.head) must be ("204e00000000000017a914eda8ae08b5c9f973f49543e90a7c292367b3337c87")
  }


  it must "serialize an older raw transaction output" in {
    //from this question
    //https://bitcoin.stackexchange.com/questions/2859/how-are-transaction-hashes-calculated
    val txOutput = "0100f2052a01000000434104678afdb0fe5548271967f1a67130b7105cd6a828e03909a67962e0ea1f61deb649f6bc3f4cef38c4f35504e51ec112de5c384df7ba0b8d578a4c702b6bf11d5fac"
    val output = RawTransactionOutputParser.read(txOutput)
    output.head.n must be (0)
    output.head.value must be (Satoshis(5000000000L))

  }
}