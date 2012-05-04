package server

import collection.immutable.HashMap
import model.Underlying._
import code.comet.MarketActivityActor

case class Tick(underlying: Underlying, price: Double)

object MarketDataPublisher {
  type Callback = Double => Unit;

  // Look, only defining the type once!
  var tradesRegisteredForUnderlyingUpdates: HashMap[Underlying, List[Callback]] = new HashMap()

  // difference between direct and get access to collections
  private def getCurrentListenersOrEmptyList(underlying: Underlying): List[Callback] = {
    val currentListeners: Option[List[Callback]] = tradesRegisteredForUnderlyingUpdates.get(underlying)

    currentListeners.getOrElse(List()) //implicit return value
  }

  // defaulting to synchronized on this
  def registerForUpdates(underlyings: List[Underlying], callback: Callback) {
    synchronized {

      underlyings foreach {
        underlying =>
          var callbacks: List[Callback] = getCurrentListenersOrEmptyList(underlying)
          callbacks ::= callback // don't forget to update callbacks because the list is always immutable

          tradesRegisteredForUnderlyingUpdates += underlying -> callbacks
      }

    }
  }

  def tick(tick: Tick) {
    println("\n>>> MARKET MOVING >>>")
    println(tick.underlying + " @" + tick.price)
    getCurrentListenersOrEmptyList(tick.underlying).foreach(callback => callback(tick.price))
    MarketActivityActor ! tick
  }
}