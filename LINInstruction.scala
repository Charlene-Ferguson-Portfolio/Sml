package sml

/** A LINInstruction.
  * Stores a value in a register.
  * @constructor creates a new lin instruction with a label for the line number, operation type and registers.
  * @param label is the label for the line.
  * @param op the description of instruction to perform "lint".
  * @param register is the register that will store the provided value.
  */
case class LINInstruction(label: String, op: String, register: Int, value: Int) extends Instruction(label, op) {

  /** Stores a value in a register.*/
  override def execute(m: Machine) =
    m.regs(register) = value

  override def toString(): String = {
    super.toString + " register " + register + " value is " + value + "\n"
  }
}

object LINInstruction {
  def apply(label: String, register: Int, value: Int) =
    new LINInstruction(label, "lin", register, value)
}
