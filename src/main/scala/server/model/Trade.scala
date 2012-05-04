package server.model

import scala._
import code.comet.TradeActivityListener
import server.BarrierListener
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
//would have been simpler to extend Trade.
sealed trait HasBarrier extends Trade with BarrierListener {
  // todo must be extended by stuff that has an underlying?
  this: Trade =>

  var breached: Boolean = false

  def description(): String;
}

trait HasKnockOut extends HasBarrier with Trade {

  // TODO: make this confusing as hell and use a case?
  override def changed(price: Double) {
    if (breached)
      return
    if (price > strikePrice) {
      breached = true
      TradeActivityListener ! this
      println(this + ": " + description() + " breached barrier [" + strikePrice + "]")
    }
  }

  override def description(): String = {
    "Knock Out"
  }

  override def toString: String = {
    super.toString + ", breached: " + breached
  }

  // override def toString = "blah!"
}

trait HasKickIn extends HasBarrier with Trade {

  override def changed(price: Double) {
    println("HasKickIn.changed " + price)
  }

  override def description(): String = {
    "Kick In"
  }
}






