package server

import actors.{Futures, Future}
import model.{HasKi, Single, Trade}
import java.lang.Short


class TradeSource {
  //TODO: LLoyd: Add second Java based Trade Source

  def asyncify[A, B](f: A => B): A => Future[B] = (a => Futures.future(f(a)))

  def sleepFor(millis : Long) {
    Thread.sleep(millis);
  }

  val asyncSleepFor = asyncify(sleepFor)

  def runThisForever( f: => Unit){
    while(true){
      Thread.sleep(2000);
      f
    }
  }

  def generateRandomTradeAndSentToTradeInputActor() {
    // TODO: Graeme: Random...

    val tradeMessage1 = new TradeMessage("fromJay", "jay1", new Single("id1", "lloy.l"));
    TradeInputActor ! tradeMessage1

    val tradeMessageWithKi = new TradeMessage("fromBob", "bob1", new Single("id2", "lloy.2"));
    TradeInputActor ! tradeMessageWithKi

    TradeInputActor ! "any old junk";

    println()
    println()
  }


  runThisForever(generateRandomTradeAndSentToTradeInputActor())
}