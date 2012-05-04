package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.js.JsCmds
import net.liftweb.http.SHtml
import server.model.store.TradeStore
import server.tradesource.ScalaTradeSource
import server.model.Underlying._
import server.{DemoHelper, Tick}

class DemoFunctions {

  def initTradeSource = "#initTradeSourceButton [onclick]" #> SHtml.ajaxInvoke(() => {
    println("Create / reset demo Trades....")
    new ScalaTradeSource()
    JsCmds.Noop
  })

  def generateSomeMarketData = "#generateMarketData [onclick]" #> SHtml.ajaxInvoke(() => {
    println("Generate some market data....")
    new Thread() {
      override def run() {
        val hsbcPrices = Range.Double(70, 85, 0.5).toList
        val hsbcTicks = hsbcPrices.map( price => new Tick(HSBC, price))
        new DemoHelper().runTickSequence(hsbcTicks, 2000);
      }
    }.start()
  })

  def printTradeStoreContents = "#printTradeStoreStatusButton [onclick]" #> SHtml.ajaxInvoke(() => {
    println("All Trades in trade store...")
    val trades = TradeStore.trades
    println(trades)
    JsCmds.Alert("Trades : " + trades.toString)
  })

  def tradeContents = "#trade *" #> TradeStore.trades.toString
}
