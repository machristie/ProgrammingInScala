
trait Prompter1 {

  val prompt = "> "
  val greeting = "Hello world"

  def printGreeting() {
    println(prompt + greeting)
  }
}

val prompter1 = new Object with Prompter1
prompter1.printGreeting

//<console>:37: error: not found: value greeting
//                  println(prompt + greeting)

trait Prompter2 {
  // the self type
  this: GreetingProvider =>

  val prompt = "> "

  def printGreeting() {
    println(prompt + greeting)
    // Next line would work too. 'this' can refer to things within this trait or
    // the declared self type trait
    //println(this.prompt + this.greeting)
  }
}

trait GreetingProvider {

  val greeting = "Hello world"
}

val prompter2 = new Prompter2 with GreetingProvider
prompter2.printGreeting

// Order doesn't matter, can do it this way too
val prompter2backwards = new GreetingProvider with Prompter2
prompter2backwards.printGreeting


// Multiple dependencies
trait Foo {

  def foo() {
    println("foo called")
  }
}

trait Prompter3 {
  // the self type
  // multiple dependencies
  this: GreetingProvider with Foo =>

  val prompt = "> "

  def printGreeting() {
    println(prompt + greeting)
    foo()
    // Next line would work too. 'this' can refer to things within this trait or
    // the declared self type trait
    //println(this.prompt + this.greeting)
  }
}

