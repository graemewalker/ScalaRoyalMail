package server.model.store

import collection.immutable.HashMap
import server.model.{HasBarrier, Trade}
import server.MarketDataFeed

object TradeStore {

  var trades = Set[Trade]();

  def add(trade: Trade) {
    trades += trade;

    trade match{
      case trade : HasBarrier => MarketDataFeed.registerForUpdates(trade.underlyings, trade.changed)
      case _ =>

    }
    //Notify the gui that a trade has been added via the listener pattern (using Trait loveliness)
  }

  def breachedTrades : Set[HasBarrier] = {
    trades.collect {
      case t:HasBarrier if t.breached => t
    }
  }
}
