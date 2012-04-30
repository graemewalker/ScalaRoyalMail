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
    case v : Vector[Trade] => trades = v.map(t => t.toString); reRender();
  }

  def render = "li *" #> trades & ClearClearable
}
