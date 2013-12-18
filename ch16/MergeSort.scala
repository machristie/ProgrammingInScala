
// putting the list first allows the type of the second argument to be inferred
def mergeSort[T](list:List[T])(less: (T,T) => Boolean):List[T] = {

  def merge(l1:List[T], l2:List[T]):List[T] = (l1, l2) match {
    // Forgot about the nicer syntax to match on here that goes
    // case (a :: arest, b :: brest) if less(a,b) => a :: merge(arest, l2)
    case (List(a, _*), List(b, _*)) if less(a,b) => a :: merge(l1.tail, l2)
    case (List(a, _*), List(b, _*)) if less(b,a) => b :: merge(l1, l2.tail)
    case (_, List()) => l1
    case (List(), _) => l2
  }

  if (list.length <= 1)
    return list
  else {
    val (first, second) = list splitAt list.length/2
    return merge(mergeSort(first)(less), mergeSort(second)(less))
  }
}
