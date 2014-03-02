
class Point(val x: Int, val y: Int) {

  def distance(point:Point):Double =
    if (this == point)
      0
    else
      Math.sqrt(Math.pow(this.x - point.x, 2) + Math.pow(this.y - point.y, 2))

  override def hashCode = 41 * (41 + x) + y
  override def equals(other: Any) = other match {
    case that: Point =>
      (that canEqual this) && (this.x == that.x) && (this.y == that.y)
    case _ => false
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Point]
}

object Color extends Enumeration {
  val Red, Orange, Yellow, Green, Blue, Indigo, Violet = Value
}

class ColoredPoint(x: Int, y: Int, val color: Color.Value) extends Point(x, y) {

  override def hashCode = 41 * super.hashCode + color.hashCode
  override def equals(other: Any) = other match {
    case that: ColoredPoint =>
      (that canEqual this) && super.equals(that) && this.color == that.color
    case _ => false
  }

  override def canEqual(other: Any): Boolean = other.isInstanceOf[ColoredPoint]
}

class ColoredPoint2(x: Int, y: Int, val color: Color.Value) {

  val point = new Point(x, y)

  override def hashCode = 41 * point.hashCode + color.hashCode
  override def equals(other: Any) = other match {
    case that: ColoredPoint2 =>
      point.equals(that.point) && this.color == that.color
    case _ => false
  }
}
