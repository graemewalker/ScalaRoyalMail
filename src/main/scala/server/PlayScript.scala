import server.model._
import server.TradeSource
;

//val singleProduct = Single("single1", "0005.HK");
//println(singleProduct)
//
//val basketProduct = new Basket("multiple1", ("0005.HK" :: "2398.HK" :: Nil));
//println(basketProduct)
//
//val basketProductWithKi = new Basket("multiple1", ("0005.HK" :: "2398.HK" :: Nil)) with HasKi;
//println(basketProduct)
//
//def basketMatcher(t: Trade*) {
//  t foreach {
//    case t: HasKi => println("Has KI")
//    case _ => println("No Ki")
//  }
//}
//
//basketMatcher(basketProduct, basketProductWithKi, singleProduct)

val tradeSource = new TradeSource();


