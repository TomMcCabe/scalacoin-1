package org.scalacoin.script.control

import org.scalatest.{MustMatchers, FlatSpec}

/**
 * Created by chris on 1/6/16.
 */
class ControlOperationsTest extends FlatSpec with MustMatchers {

  "ControlOperations" must "define an OP_IF" in {
    OP_IF.opCode must be (99)
  }

  it must "define an OP_NOTIF" in {
    OP_NOTIF.opCode must be (100)
  }

  it must "define an OP_ELSE" in {
    OP_ELSE.opCode must be (103)
  }

  it must "define an OP_ENDIF" in {
    OP_ENDIF.opCode must be (104)
  }

  it must "define an OP_VERIFY" in {
    OP_VERIFY.opCode must be (105)
  }

  it must "define an OP_RETURN" in {
    OP_RETURN.opCode must be (106)
  }

}
