
def reverseLeft[T](list:List[T]):List[T] =
  (list foldLeft List[T]()) ((accum, item) => item :: accum)
