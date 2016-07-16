package sml

/** A OUTInstruction.
  * Creates a new output instruction that prints the value of the register to the console.
  * @author Charlene Ferguson on 01/03/2016.
  * @constructor creates a new out instruction with a label for the line number,
  * operation type and registers.
  * @param label is the label for line or statement.
  * @param op the description of instruction to perform "out".
  * @param register is the register that will be output to the console.
  */

case class OUTInstruction(label: String, op: String, register: Int) extends Instruction(label, op) {

/** A variable that stores the value of the register*/
  var value1: Int = 0

  /** A variable counter that indicates the end of a loop*/
  var fin: Boolean = _

  /**
    * Retrieves a value from memory and prints the contents to the console.
    * @param m machine
    *
    */
  override def execute(m: Machine) {
    value1 = m.regs(register)
    if (!fin) println("Print contents of register " + value1)
    fin = ((m.prog.length - 1) == m.labels.labels.indexOf(label))
  }

  override def toString(): String = {
    super.toString + " register " + register + "\n"
  }
}

object OUTInstruction {
  def apply(label: String, register: Int) =
    new OUTInstruction(label, "out", register)
}
