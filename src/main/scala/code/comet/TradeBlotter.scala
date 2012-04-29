package code.comet

import net.liftweb.http.{CometListener, CometActor}
import net.liftweb.util.ClearClearable
import server.model.Trade

class TradeBlotter extends CometActor with CometListener {

//  implicit def tradeToString(t: Trade): String = {
//    t.toString
//  }

  private var trades = Vector[String]()
  protected def registerWith = TradeActivityServer

  override def lowPriority = {
<<<<<<< HEAD
    case v : Vector[String] => trades = v; reRender();
=======
    case v : Vector[Trade] => trades = v.map(t => t.toString); reRender();
>>>>>>> dce6f13d9b9e9ca432033910e618c0004c8742e6
  }

  def render = "li *" #> trades & ClearClearable
}
