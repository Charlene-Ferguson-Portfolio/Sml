package sml

/**
  * Created by charlene on 01/03/2016.
  */

class PrintInstruction(label: String, op: String, val result: Int, val op1: Int, val op2: Int)
  extends Instruction(label, op) {

  override def execute(m: Machine) {
    val value1 = m.regs(op1)
    val value2 = m.regs(op2)
    m.regs(result) = value1 + value2
  }

  override def toString(): String = {
    super.toString + " " + op1 + " + " + op2 + " to " + result
  }
}

object PrintInstruction {
  def apply(label: String, result: Int, op1: Int, op2: Int) =
    new PrintInstruction(label, "print", result, op1, op2)
}
