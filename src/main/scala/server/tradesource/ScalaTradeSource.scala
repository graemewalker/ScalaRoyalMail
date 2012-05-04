package server.tradesource

import server.{TradeMessage, TradeRoutingActor}
import server.model.Underlying.{CHEUNG, HSBC, CLP}
import server.model.{BasketTrade, HasKnockOut, SingleTrade}


class ScalaTradeSource {
  TradeRoutingActor ! "any old junk";

  TradeRoutingActor ! new TradeMessage("fromJay", "jay1", new SingleTrade("10001", CLP, 70))

  TradeRoutingActor ! new TradeMessage("fromBob", "bob1", new SingleTrade("10002", HSBC, 75) with HasKnockOut)

  TradeRoutingActor ! new TradeMessage("fromBob", "bob2", new SingleTrade("10003", CHEUNG, 80) with HasKnockOut)

  TradeRoutingActor ! new TradeMessage("fromBob", "bob2", new SingleTrade("10004", HSBC, 82) with HasKnockOut)

  TradeRoutingActor ! new TradeMessage("fromBob", "bob2", new SingleTrade("10005", CHEUNG, 81) with HasKnockOut)

  TradeRoutingActor ! new TradeMessage("fromBob", "bob2", new SingleTrade("10006", HSBC, 80) with HasKnockOut)

  TradeRoutingActor ! new TradeMessage("fromBob", "bob2", new BasketTrade("10007", List(CHEUNG, HSBC), 85) with HasKnockOut)

  TradeRoutingActor ! new TradeMessage("fromBob", "bob2", new BasketTrade("10008", List(HSBC, CLP, CHEUNG), 85) with HasKnockOut)

  TradeRoutingActor ! new TradeMessage("fromBob", "bob2", new BasketTrade("10009", List(HSBC, CLP), 85) with HasKnockOut)



  //  def runThisForever( f: => Unit){
  //    while(true){
  //      Thread.sleep(2000);
  //      f
  //    }
  //  }

  //  def asyncify[A, B](f: A => B): A => Future[B] = (a => Futures.future(f(a)))
  //
  //  def sleepFor(millis : Long) {
  //    Thread.sleep(millis);
  //  }
  //
  //  val asyncSleepFor = asyncify(sleepFor)


}