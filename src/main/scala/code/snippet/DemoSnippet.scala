package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.js.JsCmds
import net.liftweb.http.SHtml
import server.model.store.TradeStore
import server.tradesource.ScalaTradeSource
import server.model.Underlying._
import server.{MarketDataPublisher, Tick}

class DemoSnippet {

  def initTradeSource = "#initTradeSourceButton [onclick]" #> SHtml.ajaxInvoke(() => {
    println("Create / reset demo Trades....")
    new ScalaTradeSource()
    JsCmds.Noop
  })

  def generateSomeMarketData = "#generateMarketData [onclick]" #> SHtml.ajaxInvoke(() => {
    println("Generate some market data....")
    new Thread() {
      override def run() {
        runTickSequence(createMarketTicks(), 1200);
      }
    }.start()
  })

  //Ranges example
  def createMarketTicks(): List[Tick] = {
    val hsbcPrices = 70.0 until 85.0 by 0.5
    // Or Range.Double(70, 85, 0.5).toList

    hsbcPrices.map( price => new Tick(HSBC, price)).toList
  }

  def runTickSequence(ticks: List[Tick], interval: Long) {
    ticks.foreach {
      tick =>
        MarketDataPublisher.tick(tick)
        Thread.sleep(interval)
    }
  }

  def printTradeStoreContents = "#printTradeStoreStatusButton [onclick]" #> SHtml.ajaxInvoke(() => {
    println("All Trades in trade store...")
    val trades = TradeStore.trades
    println(trades)

    //Example of Javascript functions
    JsCmds.Alert("Trades : " + trades.toString)
  })

  def tradeContents = "#trade *" #> TradeStore.trades.toString
}
