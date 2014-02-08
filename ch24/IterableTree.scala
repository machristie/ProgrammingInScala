
sealed abstract class Tree extends Iterable[Int]

case class Branch(left: Tree, right: Tree) extends Tree {
  def iterator: Iterator[Int] = new WrappedIterator(left.iterator ++ right.iterator)
  //def iterator: Iterator[Int] = new WrappedIterator(add(left.iterator,right.iterator))
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

def add[A](left:Iterator[A], right:Iterator[A]):Iterator[A] =
  return new Iterator[A] {

    def hasNext: Boolean = left.hasNext || right.hasNext

    def next: A =
      if (left.hasNext)
        left.next
      else
        right.next
  }

val t = Branch(Branch(Node(1),Node(2)),Node(3))

