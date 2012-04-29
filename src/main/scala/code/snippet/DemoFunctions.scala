package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.js.JsCmds
import net.liftweb.http.SHtml
import server.model.store.TradeStore
import server.{Tick, DemoHelper, TradeSource}
import server.model.Underlying._

class DemoFunctions {

  def initTradeSource = "#initTradeSourceButton [onclick]" #> SHtml.ajaxInvoke(() => {
    println("\nInitialise Some Data....")
    new Thread() {
      override def run() {
        new TradeSource()
        val marketTicks = List(Tick(HSBC, 70.0), Tick(HSBC, 72.0), Tick(HSBC, 75.0), Tick(HSBC, 76.0), Tick(HSBC, 81.0))
        val demoHelper = new DemoHelper()
        demoHelper.runTickSequence(marketTicks, 2000);
      }
    }.start()
    JsCmds.Noop
  })

  def printTradeStoreContents = "#printTradeStoreStatusButton [onclick]" #> SHtml.ajaxInvoke(() => {
    println("\nAll Trades in trade store...")
    val trades = TradeStore.trades
    println(trades)
    JsCmds.Alert("Trades : " + trades.toString())
  })

  def tradeContents = "#trade *" #> TradeStore.trades.toString()

}
