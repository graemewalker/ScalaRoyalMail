package server

import actors.Actor
import model._
import model.store.TradeStore


case class TradeMessage(origin: String, extRef: String, tradeDetails: Trade);

object TradeRoutingActor extends Actor {
  def act() {
    loop {
      receive {

        //examples of pattern matching can also use conditional checking
        case message: TradeMessage => {
          message.tradeDetails match {
            case ko: HasKickIn => sendToExceptionQueue("Unsupported Barrier Type ")
            case trade => sendToTradeStore(trade)
          }
        }
        case _ => sendToExceptionQueue("Not a TradeMessage");
      }
    }
  }

  def sendToTradeStore(trade: Trade) {
    println("Sending to trade store:  " + trade)
    TradeStore.add(trade);
  }

  def sendToExceptionQueue(msg: String) {
    println("Adding to Exception queue: " + msg)
    // Do something...
  }

  // This must be called or the Actor will do nothing and message will appear as if they are vanishing into thin air
  this.start()
}