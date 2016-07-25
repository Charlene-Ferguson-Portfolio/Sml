package sml

/** An Sub Instruction.
  * Subtracts a value from another value and stores the result in a register (storage of memory).
  * @constructor creates a new sub instruction with a label for the line number, operation type and registers.
  * @param label is the label for the line.
  * @param op the description of instruction to perform "sub".
  * @param result where the result of the execution method is stored.
  * @param register1  location register for a value
  * @param register2 location register for a value
  */

class SubInstruction(label: String, op: String, val result: Int, val register1: Int, val register2: Int)
    extends Instruction(label, op) {

  /** Subtracts a value from another value and stores the result in a register. */
    override def execute(m: Machine) {
      val value1 = m.regs(register1)
      val value2 = m.regs(register2)
      m.regs(result) = value1 - value2
    }

    override def toString(): String = {
      super.toString + " " + register1 + " - " + register2 + " to " + result + "\n"
    }
  }

  object SubInstruction {
    def apply(label: String, result: Int, op1: Int, op2: Int) =
      new SubInstruction(label, "sub", result, op1, op2)
  }

