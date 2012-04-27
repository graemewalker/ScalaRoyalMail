package server

import actors.Actor
import model._
import store.TradeStore


case class TradeMessage(origin: String, extRef: String, tradeDetails: Trade);

object TradeInputActor extends Actor {
  def act() {
    loop {
      receive {
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
  }


  this.start()
}


//case kicker @(_: HasKnockOut | _: HasKickIn) => println(kicker)
