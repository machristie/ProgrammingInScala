
class EmailImpl(val username:String, val domain:String) {

  override def toString: String = username + "@" + domain
}

object Email {

  def unapply(s: String): Option[(String, String)] = {
    val parts = s split "@"
    if (parts.length == 2) Some(parts(0), parts(1)) else None
  }
}
