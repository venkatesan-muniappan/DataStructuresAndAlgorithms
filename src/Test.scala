// Scala program of try-catch-finally clause 

// Creating object
object GFG {
  // Main method
  def main(args: Array[String]) {
    try {
      // create array
      var array = Array(4, 2, 7)
      var b = array(5)
    }
    catch {
      case th: Throwable => println("unknown exception" + th); return
    }
    finally {

      println("this block always executes")
    }

    // rest program will execute
    println(" rest of code executing")
  }
} 