package sml

/** An Add Instruction.
  *
  * @constructor creates a new add instruction that adds the value from one register to the value of another
  *              register and stores the result in a third register.
  * @param label is the label for the line.
  * @param op indicates the type of operation to perform "add".
  * @param resultRegister where the result of the execution method is stored.
  * @param register1  location register for a value
  * @param register2 location register for a value
  */

class AddInstruction(label: String, op: String, val resultRegister: Int, val register1: Int, val register2: Int)
  extends Instruction(label, op) {



/** Adds the values of two storage registers and stores the result in another register.
  * @param m machine object @tparam Machine
  */

  override def execute(m: Machine) {
    val value1 = m.regs(register1)
    val value2 = m.regs(register2)
    m.regs(resultRegister) = value1 + value2
  }

  override def toString(): String = {
    super.toString + " " + register1 + " + " + register2 + " to " + resultRegister + "\n"
  }
}

object AddInstruction {
  def apply(label: String, result: Int, op1: Int, op2: Int) =
    new AddInstruction(label, "add", result, op1, op2)
}