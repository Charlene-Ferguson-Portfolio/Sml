package sml

/** A BNZInstruction Changes the order of execution . The next statement to executes is contained with variable
  * label 2
* @author Charlene Ferguson
* @constructor creates a new bnz instruction with a label for the line number, operation type and registers.
* @param label is the label for the line.
* @param op the description of instruction to perform "bnz".
* @param op1 Check the contents of this storage register.
* @param label2 the label for the line number which is the next statement to execute
*/

case class BNZInstruction(label: String, op: String, val op1: Int, val label2: String)
  extends Instruction(label, op) {

  var counter = 0

  /**
    * If the contents of register op1 is not zero then make the statement labeled l2 the next statement
    * to execute.
    * @param m Machine object
    */
  override def execute(m: Machine) {
    val value1 = m.regs(op1)

    if(value1 != 0) {

      m.labels.labels.view.zipWithIndex foreach{case(value, index) =>
        if(value == label2 && counter < 1)
          {
            counter += 1
            m.execute(index)}
      }}

    }

  override def toString(): String = {
    super.toString + " " + label + " replaced with " + label2 + "\n"
  }

}

object BNZInstruction {
  def apply(label: String, op1: Int, label2: String) =
    new BNZInstruction(label, "bnz", op1, label2)
}
