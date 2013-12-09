/*
 * To run, start up 'scala' repl using
 *
 *   scala -cp bin:scalatest_2.10-2.0.jar
 *
 * In the repl, run
 *
 *   :load ElementSuite.scala
 *
 * then
 *
 *   (new ElementSuite).execute()
 */
import org.scalatest.FunSuite
import Element.elem

class ElementSuite extends FunSuite {

  test("elem result should have passed width") {
    val ele = elem('x', 2, 3)
    assert(ele.width == 2)
  }
}
