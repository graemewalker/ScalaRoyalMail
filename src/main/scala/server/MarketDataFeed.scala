package server

import collection.immutable.HashMap
import model.store.TradeStore
import model.Underlying._

object MarketDataFeed {
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

  def notifyListenersOfTick(underlying: Underlying, price: Double) {
    println()
    println("TICK for [" + underlying + "] @" + price);
    for (callback <- getCurrentListenersOrEmptyList(underlying)) {
      callback(price)
    }
  }

  // TODO: Futures for define ticker events

  val tickThread: Thread = new Thread() {
    override def run() {
      //      while (true) {
      //        notifyListenersOfTick(CLP, 34.34)
      //        Thread.sleep(2000)
      Thread.sleep(2000)
      notifyListenersOfTick(HSBC, 70.00)
      Thread.sleep(2000)
      notifyListenersOfTick(HSBC, 72.00)
      Thread.sleep(2000)
      notifyListenersOfTick(HSBC, 75.00)
      Thread.sleep(2000)
      notifyListenersOfTick(HSBC, 76.00)
      Thread.sleep(2000)
      notifyListenersOfTick(HSBC, 81.00)
      Thread.sleep(2000)
      //        notifyListenersOfTick(CHEUNG, 100.11)
      //        Thread.sleep(3000)

      //      }

      println("\nThe following have breached:")
      for ( breached <- TradeStore.breachedTrades){
        println(breached)
      }
    }
  }


  tickThread.start();
}
