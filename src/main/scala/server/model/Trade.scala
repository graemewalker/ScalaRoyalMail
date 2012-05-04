package server.model

import scala._
import code.comet.TradeActivityActor
import server.PriceChangeHandler
import server.model.Underlying._

// could also be a Trait
abstract class Trade {
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

// example of the use of self types to define the rules of which classes a trait can be mixed in with (see PlayScript.scala for FakeTrade)
trait HasBarrier extends Trade with PriceChangeHandler {
  self: Trade =>

  var breached: Boolean = false

  def description(): String;
}

// Removing Trade here will cause a compile error, due to HasBarrier bounding against Trade
trait HasKnockOut extends HasBarrier {

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

trait HasKickIn extends HasBarrier {

  override def handlePriceChange(price: Double) {
    println("HasKickIn.handlePriceChange " + price)
  }

  override def description(): String = {
    "Kick In"
  }
}



