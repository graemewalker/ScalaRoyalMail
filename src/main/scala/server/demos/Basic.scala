import server._
import server.model.Underlying._

val tradeSource = new TradeSource();
val javaTradeSource = new JavaTradeSource()

val marketTicks = List(Tick(HSBC, 70.0), Tick(HSBC, 72.0), Tick(HSBC, 75.0), Tick(HSBC, 76.0), Tick(HSBC, 81.0))

val demoHelper = new DemoHelper()
demoHelper.runTickSequence(marketTicks, 2000);
demoHelper.printTradeStoreSummary()
sys.exit()