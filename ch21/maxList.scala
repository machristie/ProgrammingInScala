
def maxListImpParam[T](elements: List[T])
  (implicit orderer: T => Ordered[T]): T = {

    elements match {
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxListImpParam(rest)(orderer)
        if (orderer(x) > maxRest) x
        else maxRest
    }
  }

/*
 * This won't work. Even though x should be able to be implicitly converted to Ordered[T],
 * this method won't compile. I'm assuming it's because the compiler has no way to know
 * that T has a method '>'.
<console>:46: error: No implicit view available from T => Ordered[T].
               val maxRest = maxListImpParam(rest)
                                            ^
<console>:47: error: value > is not a member of type parameter T
               if (x > maxRest) x
                     ^
 */

//def maxList2[T](elements: List[T]): T = {
//
//    elements match {
//      case List() =>
//        throw new IllegalArgumentException("empty list!")
//      case List(x) => x
//      case x :: rest =>
//        val maxRest = maxListImpParam(rest)
//        if (x > maxRest) x
//        else maxRest
//    }
//  }

// Implicit doesn't work here either
//
//<console>:22: error: inferred type arguments [Ordered[President]] do not conform to method maxList3's type parameter bounds [T <: Ordered[T]]
//              maxList3(l)
//              ^
//<console>:22: error: type mismatch;
// found   : List[Ordered[President]]
// required: List[T]
//              maxList3(l)
//                       ^
//
// Not sure why this won't work. I guess this only works if the type T is already a
// subclass of Ordered[T] even if the instances in the List are of type Ordered[T].
def maxList3[T <: Ordered[T]](elements: List[T]): T = {

    elements match {
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxList3(rest)
        if (x > maxRest) x
        else maxRest
    }
  }

// View bounds
def maxList[T <% Ordered[T]](elements: List[T]): T = {

    elements match {
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxList(rest)
        if (x > maxRest) x
        else maxRest
    }
  }
//class President(val name:String, val inaugurated:Int) extends Ordered[President] {
//
//  override def compare(that: President):Int =
//    this.inaugurated - that.inaugurated
//  override def toString = name
//}
class President(val name:String, val inaugurated:Int) {

  override def toString = name
}

object President {
  val GeorgeWashington = new President("George Washington", 1789)
  val JohnAdams = new President("John Adams", 1797)
}

implicit def orderedPresident(pres: President): Ordered[President] = {

  class OrderedPres extends President(pres.name, pres.inaugurated) with Ordered[President] {

    override def compare(that: President):Int =
      this.inaugurated - that.inaugurated
  }

  return new OrderedPres()
}
