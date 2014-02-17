import java.util.{Calendar => JCal, Date => JDate}

object Date {

  def unapply(d: JDate):Option[(Int,Int,Int,Int,Int,Int,Int)] = {
    val cal = JCal.getInstance
    cal.setTime(d)

    Some(cal get JCal.YEAR,
         cal get JCal.MONTH,
         cal get JCal.DAY_OF_MONTH,
         cal get JCal.HOUR_OF_DAY,
         cal get JCal.MINUTE,
         cal get JCal.SECOND,
         cal get JCal.MILLISECOND)
  }
}

def isFebruary(d: JDate): Boolean = d match {
  case Date(_, 1, _, _, _, _, _) => true
  case _ => false
}

val Date(year, month, day, hour, minutes, seconds, milliseconds) = new JDate
