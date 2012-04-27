package server

import model.HasBarrier
import collection.immutable.HashMap

object MarketDataFeed {

  val tradesRegisteredForUnderlyingUpdates : HashMap[]

  def registerForUpdates(underlying: List[String], callback: => Unit){
    tradesRegisteredForUnderlyingUpdates
  }

}
