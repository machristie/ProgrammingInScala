
sealed abstract class Tree extends Iterable[Int]

case class Branch(left: Tree, right: Tree) extends Tree {
  def iterator: Iterator[Int] = new WrappedIterator(left.iterator ++ right.iterator)
}

case class Node(elem: Int) extends Tree {
  def iterator: Iterator[Int] = new WrappedIterator(Iterator.single(elem))
}

class WrappedIterator[T](iter:Iterator[T]) extends Iterator[T] {

  def hasNext: Boolean = {
    println("hasNext called")
    iter.hasNext
  }
  def next: T = {
    println("next called")
    iter.next
  }
}
