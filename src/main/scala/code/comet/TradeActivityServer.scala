package code.comet

import net.liftweb.http.ListenerManager
import net.liftweb.actor.LiftActor
import server.model.Trade
import collection.immutable.HashMap

object TradeActivityServer extends LiftActor with ListenerManager{
<<<<<<< HEAD
  private var trades = Map[String, String]()

  def createUpdate = Vector[String]() ++ trades.values

  override def lowPriority = {
    case trade : Trade => trades += (trade.id -> trade.toString); updateListeners(); println("Activity server got  update: trades size = " + trades.size)
=======
  private var trades = Map[String, Trade]()

  def createUpdate = Vector[Trade]() ++ trades.values

  override def lowPriority = {
    case trade : Trade => trades += (trade.id -> trade); updateListeners(); println("Activity server got  update: trades size = " + trades.size)
>>>>>>> dce6f13d9b9e9ca432033910e618c0004c8742e6
  }


}
