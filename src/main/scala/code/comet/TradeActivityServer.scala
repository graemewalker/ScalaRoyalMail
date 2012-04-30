package code.comet

import net.liftweb.http.ListenerManager
import net.liftweb.actor.LiftActor
import server.model.Trade
import collection.immutable.HashMap

object TradeActivityServer extends LiftActor with ListenerManager{

  private var trades = Map[String, Trade]()

  def createUpdate = Vector[Trade]() ++ trades.values

  override def lowPriority = {
    case trade : Trade => trades += (trade.id -> trade); updateListeners(); println("Activity server got  update: trades size = " + trades.size)
  }

}
