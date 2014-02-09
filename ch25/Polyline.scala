import collection.LinearSeq
import collection.LinearSeqLike
import collection.mutable.Builder
import collection.mutable.ArrayBuffer

case class LatLng(lat:Double, lng:Double)

final class Polyline private (val encoding: String, val initLat:Double, val initLng:Double) extends LinearSeq[LatLng] {

//  override def newBuilder: Builder[LatLng, Polyline] =
//    new ArrayBuffer[LatLng] mapResult Polyline.fromSeq

  private var index = 0
  private var firstLatLng:Option[LatLng] = None

  def apply(idx: Int): LatLng = {
    val it = iterator
    var i = 0
    while (it.hasNext && i <= idx) {

      val latlng = it.next
      if (idx == i)
        return latlng
      else
        i += 1
    }
    throw new NoSuchElementException("No LatLng at index " + idx)
  }

  def length: Int = {
    val it = iterator
    var i = 0
    while (it.hasNext) {

      it.next
      i += 1
    }
    i
  }

  override def isEmpty: Boolean = encoding.length == 0

  override def head(): LatLng = {

    if (isEmpty) {
      throw new NoSuchElementException("Collection is empty.")
    }

    computeFirstLatLng()
    firstLatLng.get
  }

  override def tail(): Polyline = {

    if (isEmpty) {
      throw new UnsupportedOperationException("Collection is empty.")
    }

    computeFirstLatLng()
    val latLng = firstLatLng.get
    new Polyline(encoding.substring(index), latLng.lat, latLng.lng)
  }

  private def computeFirstLatLng() {

    if (firstLatLng.isEmpty && encoding.length > 0) {

      val lat = initLat + getNextNumber / 1e5
      val lng = initLng + getNextNumber / 1e5

      firstLatLng = Some(LatLng(lat, lng))
    }
  }

  private def getNextNumber():Double = {
    var b = 0
    var shift = 0
    var result = 0

    do {
      b = encoding.charAt(index) - 63
      index += 1
      result |= (b & 0x1f) << shift
      shift += 5
    } while (b >= 0x20)
    return (if ((result & 1) != 0) ~(result >> 1) else (result >> 1))
  }

}

object Polyline {

  def empty: Polyline = new Polyline("", 0, 0)
  def fromEncoding(encoding:String) = new Polyline(encoding, 0, 0)
}
