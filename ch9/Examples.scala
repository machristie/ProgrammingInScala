
// Fun with by name parameters

def twice (block: => Unit) {
  block
  block
}

twice {
  println("Hello!")
}
