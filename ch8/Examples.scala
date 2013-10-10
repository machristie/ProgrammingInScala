
val someNumbers = List(-11, -10, -5, 0, 5, 10)

someNumbers.foreach((x: Int) => println(x))

// Short form of function literals
someNumbers.filter((x) => x > 0)

// Can leave off parentheses for parameters whose type is inferred
someNumbers.filter(x => x > 0)

// Placeholder syntax
// underscore stands in for one or more parameters, but each parameter in order and exactly once
someNumbers.filter(_ > 0)

// Generally underscore syntax only works for situations where type can be inferred
// For example, doesn't really make sense to do this
// (x:Int) => (_:Int) * 2
// But that is also the only way to get that to work
// Also not sure if you can actually specify parameters when using underscore syntax

// partially applied functions

// Create a function value option
def sum(a:Int, b:Int, c:Int) = a + b + c
val funcValue = sum _

// But why use function values?
// See StackOverflow answer: http://stackoverflow.com/a/5011434/1419499

// def defines methods which aren't the same as functions.
// function values allow you to turn methods into functions.

// See also http://stackoverflow.com/a/2530007/1419499

// Accoring to programming in Scala, methods and nested
// functions can't be assigned to a variable or passed as an argument to another
// function. So what you need to do is make a function value.


// Closures
// open term - function literal with free variables that are closed over
// closed term - function literal with no free variables

// Repeated parameters

def echo(args: String*) =
  for (arg <- args) println(arg)

val s = Array("1", "2", "3")

// echo(s) won't work
echo(s:_*) // will work


// Default parameters

def printTime(out: java.io.PrintStream = Console.out) =
  out.println("time = "+ System.currentTimeMillis())

// Note: you need to specify the parameter type even if it could be
// inferred from the default value. I don't see a way around this.


// Tail recursion
// If you need to debug tail calls, you can add '-g:notailcalls' to scala
// Caveats:
// - no mutual tail recursion
// - doesn't work with function value objects
