
abstract class Node
case class Branch(left:Node, right:Node, value:Any) extends Node
case class Leaf(value:Any) extends Node

def getValue(node:Node):Any = node match {
  case Branch(l, r, v) => v
  case Leaf(v) => v
}

def getLeft(node:Node):Option[Node] = node match {
  case Branch(l, r, v) => Some(l)
  case Leaf(v) => None
}

def getRight(node:Node):Option[Node] = node match {
  case Branch(l, r, v) => Some(r)
  case Leaf(v) => None
}

val t:Branch = Branch(Leaf(1), Leaf(2), 3)
getLeft(t) map getValue getOrElse "No left node"
getRight(t) map getValue getOrElse "No right node"
