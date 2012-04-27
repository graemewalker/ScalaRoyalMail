package server.model

import scala._

sealed trait Trade {
  def id: String;
  def underlyings: List[String]
}

case class Single(id: String, underlying: String) extends Trade {
  def underlyings = List(underlying);
}

case class Basket(id: String, underlyings: List[String]) extends Trade {

}

// example of the use of self types to define the rules of which classes a trait can be mixed in with
//would have been simpler to extend Trade.
sealed trait HasBarrier extends Trade { // todo must be extended by stuff that has an underlying?
  this: Trade =>
  def receiveMarketPriceAndCheckForBreach();
  def description(): String;
}

trait HasKi extends HasBarrier with Trade{

  override def receiveMarketPriceAndCheckForBreach() {
      println("HasKi.receiveMarketPriceAndCheckForBreach() called")
  }

  override def description(): String = {
    "Kick In"
  }

 // override def toString = "blah!"
}

trait HasKo extends HasBarrier with Trade {
  override def receiveMarketPriceAndCheckForBreach() {
    println("HasKo.receiveMarketPriceAndCheckForBreach() called")
  }

  override def description(): String = {
    "Kick Out"
  }
}






