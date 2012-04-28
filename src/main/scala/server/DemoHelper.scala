package server

import model.store.TradeStore

class DemoHelper {
  def runTickSequence(ticks: List[Tick], interval: Long) {
        ticks.foreach {
          tick =>
            MarketDataFeed.tick(tick)
            Thread.sleep(interval)
        }
  }

  def printTradeStoreSummary() {
    println("\n********************************************************************")
    println("Trade Store Summary");
    println("********************************************************************")
    println("\nTrades without a Barrier:")
    TradeStore.nonBarrierTrades.foreach(breachedTrade => println(breachedTrade))
    println("\nBarrier trades which have breached:")
    TradeStore.breachedBarrierTrades.foreach(breachedTrade => println(breachedTrade))
    println("\nBarrier trades which have NOT breached:")
    TradeStore.nonBreachedBarrierTrades.foreach(breachedTrade => println(breachedTrade))
  }
}
