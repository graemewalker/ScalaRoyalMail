import server.model._
import server.model.Underlying._
import server.{MarketDataFeed, TradeSource}
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

val tradeSource = new TradeSource();


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
