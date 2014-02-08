
sealed abstract class Tree extends Traversable[Int]

case class Branch(left: Tree, right: Tree) extends Tree {
  def foreach[U](f: Int => U) = {
    //println("left foreach")
    left foreach f
    //println("right foreach")
    right foreach f
  }
}

case class Node(elem: Int) extends Tree {
  def foreach[U](f: Int => U) = f(elem)
}
