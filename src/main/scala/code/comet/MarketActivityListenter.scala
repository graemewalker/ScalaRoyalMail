package code.comet

import net.liftweb.http.ListenerManager
import net.liftweb.actor.LiftActor
import server.model.Trade
import collection.immutable.HashMap
import server.Tick

object MarketActivityListenter extends LiftActor with ListenerManager {

  private var ticks = List[Tick]()

  def createUpdate = ticks

  override def lowPriority = {
    case tick: Tick => ticks ::= tick; updateListeners(); println("MarketActivityListenter$ got update: ticks = " + ticks.size)
  }

}
