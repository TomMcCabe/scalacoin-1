package org.scalacoin.util

import org.scalacoin.script.constant.ScriptToken

/**
 * Created by chris on 3/2/16.
 */
trait BitcoinScriptUtil {

  /**
   * Takes in a sequence of script tokens and converts them to their hexadecimal value
   * @param asm
   * @return
   */
  def asmToHex(asm : Seq[ScriptToken]) : String = {
    val hex = asm.map(_.hex).mkString
    hex
  }


  /**
   * Converts a sequence of script tokens to them to their byte values
   * @param asm
   * @return
   */
  def asmToBytes(asm : Seq[ScriptToken]) : Seq[Byte] = BitcoinSUtil.decodeHex(asmToHex(asm))
}


object BitcoinScriptUtil extends BitcoinScriptUtil