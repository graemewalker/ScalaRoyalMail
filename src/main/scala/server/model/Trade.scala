package server.model

import scala._
import code.comet.TradeActivityActor
import server.PriceChangeHandler
import server.model.Underlying._

sealed trait Trade {
  def id: String;
  def underlyings: List[Underlying]
  def strikePrice: Double;

  override def toString: String = {
    "ID: " + id + ", Underlying(s): (" + underlyings.mkString(",") + "), Strike: " + strikePrice
  }
}

case class SingleTrade(id: String, underlying: Underlying, strikePrice: Double) extends Trade {
  def underlyings = List(underlying);
}

case class BasketTrade(id: String, underlyings: List[Underlying], strikePrice: Double) extends Trade

// example of the use of self types to define the rules of which classes a trait can be mixed in with
// would have been simpler to extend Trade?
sealed trait HasBarrier extends Trade with PriceChangeHandler {

  var breached: Boolean = false

  def description(): String;
}

trait HasKnockOut extends HasBarrier with Trade {

  override def handlePriceChange(price: Double) {
    if (breached)
      return
    if (price > strikePrice) {
      breached = true
      TradeActivityActor ! this
      println(this + ": " + description() + " breached barrier [" + strikePrice + "]")
    }
  }

  override def description(): String = {
    "Knock Out"
  }

  override def toString: String = {
    super.toString + ", breached: " + breached
  }
}

trait HasKickIn extends HasBarrier with Trade {

  override def handlePriceChange(price: Double) {
    println("HasKickIn.handlePriceChange " + price)
  }

  override def description(): String = {
    "Kick In"
  }
}



