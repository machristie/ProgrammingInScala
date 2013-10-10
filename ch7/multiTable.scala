
def formatProduct(p:Int):String = {

  val spacesCount = 4 - p.toString.length()
  return " " * spacesCount + p
}

def multiRow(i:Int):String = {

  val rowItems = for (j <- 1 to 10) yield formatProduct(i*j)
  return rowItems.mkString
}

def multiTable():String = {

  val rows = for (i <- 1 to 10) yield multiRow(i)
  return rows.mkString("\n")
}

