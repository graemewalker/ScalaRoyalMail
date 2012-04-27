package server.model.store

import collection.immutable.HashMap
import server.model.{HasBarrier, Trade}
import server.MarketDataFeed

object TradeStore {

  var trades = Set[Trade]();

  def add(trade: Trade) {
    trades += trade;

    trade match{
      case trade : HasBarrier => MarketDataFeed.registerForUpdates(trade.underlyings, trade.receiveMarketPriceAndCheckForBreach());
    }
  }
}
