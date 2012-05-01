package server

import actors.{Futures, Future}
import java.lang.Short
import model._
import model.Underlying._


class TradeSource {
  //TODO: LLoyd: Add second Java based Trade Source
  TradeInputActor ! "any old junk";

  TradeInputActor ! new TradeMessage("fromJay", "jay1", new Single("10001", CLP, 70))

  TradeInputActor ! new TradeMessage("fromBob", "bob1", new Single("10002", HSBC, 75) with HasKnockOut)

  TradeInputActor ! new TradeMessage("fromBob", "bob2", new Single("10003", CHEUNG, 80) with HasKnockOut)

  TradeInputActor ! new TradeMessage("fromBob", "bob2", new Single("10004", HSBC, 82) with HasKnockOut)

  TradeInputActor ! new TradeMessage("fromBob", "bob2", new Single("10005", CHEUNG, 81) with HasKnockOut)

  TradeInputActor ! new TradeMessage("fromBob", "bob2", new Single("10006", HSBC, 60) with HasKnockOut)

  TradeInputActor ! new TradeMessage("fromBob", "bob2", new Basket("10007", List(CHEUNG, HSBC), 85) with HasKnockOut)

  TradeInputActor ! new TradeMessage("fromBob", "bob2", new Basket("10008", List(HSBC, CLP, CHEUNG), 85) with HasKnockOut)

  TradeInputActor ! new TradeMessage("fromBob", "bob2", new Basket("10009", List(HSBC, CLP), 85) with HasKnockOut)



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