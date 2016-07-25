package sml

/** A MUL Instruction.
  * Creates a new multiply instruction that multiplies one value by another value and stores the result.
  * @author Charlene Ferguson
  * @constructor creates a new mulinstruction with a label for the line number,operation type and registers.
  * @param label is the label for the line.
  * @param op the description of instruction to perform "mul".
  * @param resultRegister where the result of the execution method is stored.
  * @param register1  location register for a value
  * @param register2 location register for a value
  */

class MulInstruction(label: String, op: String, val resultRegister: Int, val register1: Int, val register2: Int)
  extends Instruction(label, op) {

/**
  * Takes the value of register 1 and register 2 and
  * multiplies these two numbers to provide a product
  * which is stored in the result register
  * @param m a machine object
  * */
  override def execute(m: Machine) {
    val value1 = m.regs(register1)
    val value2 = m.regs(register2)
    m.regs(resultRegister) = value1 * value2

  }

  override def toString(): String = {
    super.toString + " " + register1 + " * " + register2 + " to " + resultRegister + "\n"
  }
}

object MulInstruction {
  def apply(label: String, result: Int, op1: Int, op2: Int) =
    new MulInstruction(label, "mul", result, op1, op2)
}
