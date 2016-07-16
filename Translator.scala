package sml

import java.lang.reflect.Constructor


import scala.collection.mutable.ArrayBuffer

/**
  * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
  */
class Translator(fileName: String) {
  private final val ADD = "add"
  private final val LIN = "lin"
  private final val OUT = "out"
  private final val SUBTRACT = "sub"
  private final val DIVIDE = "div"
  private final val MULTIPLY = "mul"
  private final val BNZ = "bnz"


  // word + line is the part of the current line that's not yet processed
  // word has no whitespace
  // If word and line are not empty, line begins with whitespace

  /**
    * translate the small program in the file into lab (the labels) and prog (the program)
    */
  def readAndTranslate(m: Machine): Machine = {
    val labels = m.labels
    var program = m.prog
    import scala.io.Source
    val lines = Source.fromFile(fileName).getLines
    for (line <- lines) {
      val fields = line.split(" ")
      if (fields.length > 0) {

        labels.add(fields(0))

        /** Package Name */
        val PACKAGE = "sml."

        /** Operation type add, subtract, multiply etc */
        /** Converts an operation type to a prefix for the class name. */
        val PREFIX = fields(1).toUpperCase

        /** Suffix of the class name */
        val SUFFIX = "Instruction"

        /** Create the name of the class */
        val name = PACKAGE + PREFIX + SUFFIX
        /** Get the class for the supplied string */
        val className: Class[_] = Class.forName(name)

        /** Get the constructors of a class and return the first one. */
        val constructor: Constructor[_] = className.getConstructors().head

        /** Get the parameter types of a class from the constructor and return in an array. */
        val parameterArray = constructor.getParameterTypes

        /** Create a mutable array that will contain objects*/
        var parameterObject = ArrayBuffer[Object]()

        /** Create an array of both the parameter type and parameter values */
        val zipped = parameterArray.zip(fields)

        /**
          * Iterate through the array.
          * Provide a name for both elements.
          * Check the parameter type and if it is an int convert to int then convert to an object
          * if the type is a string convert to an object.
          * add to a parameterArray of objects.
          * These parameters are used to create a new instance via the java reflection api.
          **/

        zipped.foreach(i => {
          val (parameterType: Class[_], parameter: String) = i
          parameterType match {
            case x if parameterType.getName == "int" =>
              var z = parameter.toInt.asInstanceOf[Object]
              parameterObject += z
            case x if parameterType.getName == "java.lang.String" =>
              var y = parameter.asInstanceOf[Object]
              parameterObject += y
          }
        })

        /** Create the correct number of arguments to pass to the new instance method */

       try {
          val newObject: Instruction = constructor.newInstance(parameterObject:_*).asInstanceOf[Instruction]
          newObject.toString()
          program = program :+ newObject

        } catch {
          case e: ClassNotFoundException => "Class not found" + e.printStackTrace()
          case e: IllegalArgumentException => "Wrong number of arguments" + e.printStackTrace()
          case e: InstantiationException => "Can not instantiate class" + e.printStackTrace()
          case e: IllegalAccessException => "No access" + e.printStackTrace()
          case e: Exception => "An exception occurred." + e.printStackTrace()
       }

      } // End If Checks that fields is not empty
    } // End For Loop that read each line
    new Machine(labels, program)
  }
}
object Translator {
  def apply(file: String) =
    new Translator(file)
}