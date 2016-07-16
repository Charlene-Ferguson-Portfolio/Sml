package sml

/** A DIVInstruction.
  * Creates a new div instruction that divides one value by another value and stores the result.
  * @author Charlene Ferguson
  * @constructor creates a new div instruction that divides one value by another value and stores the result.
  * @param label is the label for the line.
  * @param op the identifier of the instruction to perform "div".
  * @param result identifier that stores the results of the division.
  * @param op1 identifier for a register or storage value
  * @param op2 identifier for a register or storage value
  */

class DIVInstruction(label: String, op: String, val result: Int, val op1: Int, val op2: Int)
  extends Instruction(label, op) {

  /**
    * Divides the contents of one register by the contents of another register and stores
    * the result in another register.
    * @param m machine
    */

  override def execute(m: Machine) {
    val value1 = m.regs(op1)
    val value2 = m.regs(op2)
    m.regs(result) = value1 / value2
  }

  override def toString(): String = {
    super.toString + " " + op1 + " / " + op2 + " to " + result + "\n"
  }
}

object DIVInstruction {
  def apply(label: String, result: Int, op1: Int, op2: Int) =
    new DIVInstruction(label, "div", result, op1, op2)
}
