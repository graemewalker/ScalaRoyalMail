import server._
import model.store.TradeStore
import server.model.Underlying._
;

//val singleProduct = Single("single1", Underlying.CHEUNG);
//println(singleProduct)
//
//val basketProduct = new Basket("multiple1", (CHEUNG :: CLP :: Nil));
//println(basketProduct)
//
//val basketProductWithKi = new Basket("multiple1", List(CLP, HSBC)) with HasKnockOut;
//println(basketProduct)
//
//def basketMatcher(t: Trade*) {
//  t foreach {
//    case t: HasKnockOut => println("Has KI")
//    case _ => println("No Ki")
//  }
//}
//
//basketMatcher(basketProduct, basketProductWithKi, singleProduct)

val demoHelper = new DemoHelper()
val tradeSource = new TradeSource();
val javaTradeSource = new JavaTradeSource()

val demoMarketTicks = Tick(HSBC, 70.0) :: Tick(HSBC, 72.0) :: Tick(HSBC, 75.0) :: Tick(HSBC, 76.0) :: Tick(HSBC, 81.0) :: Nil
demoHelper.runTickSequence(demoMarketTicks, 2000);
demoHelper.printTradeStoreSummary()
sys.exit()

//def myCallback = (price: Double) => {
//  println("Got called back")
//}
//
//MarketDataFeed.registerForUpdates( List("Lloy.L"), myCallback)
//
//MarketDataFeed.registerForUpdates( List("Lloy.L"), myCallback)
//
//MarketDataFeed.registerForUpdates( List("xxxxx"), myCallback)
//
//MarketDataFeed.notifyListenersOfTick("Lloy.L", 101.1)
//