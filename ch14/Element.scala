
/*
 * Example code from chapter 10 of Programming in Scala, 2nd ed.
 *
 * To run, first you need to compile this code
 *
 *   scalac -d bin Element.scala
 *
 * or,
 *
 *   fsc -d bin Element.scala
 *
 * Then to run, do
 *
 *   scala -cp bin LayoutElement
 */

object Element {

  private class ArrayElement(
    val contents: Array[String]
  ) extends Element

  private class LineElement(s: String) extends Element {
    val contents = Array(s)
    override def width = s.length
    override def height = 1
  }

  private class UniformElement(
    ch: Char,
    override val width: Int,
    override val height: Int
  ) extends Element {
    private val line = ch.toString * width
    def contents = Array.fill(height)(line)
  }

  def elem(contents: Array[String]):Element =
    new ArrayElement(contents)

  def elem(line: String): Element =
    new LineElement(line)

  def elem(ch: Char, width: Int, height: Int): Element =
    new UniformElement(ch, width, height)
}

import Element.elem

abstract class Element {

  def contents: Array[String]
  def width: Int = contents(0).length
  def height: Int = contents.length

  def above(that: Element): Element = {

    val this1: Element = this widen that.width
    val that1: Element = that widen this.width
    elem(this1.contents ++ that1.contents)
  }

  def beside(that: Element): Element = {

    val this1: Element = this heighten that.height
    val that1: Element = that heighten this.height

    elem(
      for ((line1, line2) <- this1.contents zip that1.contents)
      yield line1 + line2)
  }

  def widen(w: Int): Element =
    if (w <= width) {
      this
    } else {

      val left = elem(' ', (w - width)/2, height)
      val right = elem(' ', (w - width - left.width), height)
      left beside this beside right
    }

  def heighten(h: Int): Element =
    if (h <= height) {
      this
    } else {

      val top = elem(' ', width, (h - height)/2)
      val bottom = elem(' ', width, (h - height - top.height))
      top above this above bottom
    }

  override def toString: String = contents mkString "\n"
}

object LayoutElement {
  def main(args: Array[String]) {
    println("example [\n" + example + "\n]")
  }

  def example = {
    val column1 = elem("hello") above elem("***")
    val column2 = elem("***") above elem("world")
    column1 beside column2
  }
}
