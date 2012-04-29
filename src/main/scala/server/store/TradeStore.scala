package server.model.store

import collection.immutable.HashMap
import server.model.{HasBarrier, Trade}
import server.MarketDataFeed
import code.comet.TradeActivityServer

object TradeStore {

  var trades = Set[Trade]();

  def add(trade: Trade) {
    trades += trade;

    trade match{
      case trade : HasBarrier => MarketDataFeed.registerForUpdates(trade.underlyings, trade.changed)
      case _ =>
    }

    TradeActivityServer ! trade
    //Notify the gui that a trade has been added via the listener pattern (using Trait loveliness)
  }

  def breachedBarrierTrades : Set[HasBarrier] = {
    trades.collect {
      case trade:HasBarrier if trade.breached => trade
    }
  }

  def nonBreachedBarrierTrades : Set[HasBarrier] = {
    trades.collect {
      case trade:HasBarrier if !trade.breached => trade
    }
  }

  def allBarrierTrades : Set[HasBarrier] = {
    trades.collect {
      case trade: HasBarrier => trade
    }
  }

  def nonBarrierTrades : Set[Trade] = {
    trades.filter {
      case _ : HasBarrier => false
      case _ => true
    }
  }
}
