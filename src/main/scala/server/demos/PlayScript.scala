import server._
import model.{SingleTrade, HasKnockOut}
import server.model.Underlying._
import tradesource.{ScalaTradeSource, JavaTradeSource}

//Example of a scala script file, there is also the REPL

//val tradeSource = new ScalaTradeSource();
//val javaTradeSource = new JavaTradeSource()

//val marketTicks = List(Tick(HSBC, 70.0), Tick(HSBC, 72.0), Tick(HSBC, 75.0), Tick(HSBC, 76.0), Tick(HSBC, 81.0))

// example of restricting HasKnockOut trait to a type which subclasses Trade
case class FakeTrade(id:String, underlyings: List[Underlying], strikePrice: Double)
new FakeTrade("a", List(HSBC), 10.1) with HasKnockOut

sys.exit()