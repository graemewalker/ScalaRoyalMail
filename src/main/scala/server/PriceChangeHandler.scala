package server

import actors.Actor

// case classes for the message type give type safety to parameters during refactoring.  i.e. change price to Int below
case class Change(price: Double)

trait PriceChangeHandler extends Actor {

  def act() {
    loop {
      react {
        case Change(price) => handlePriceChange(price)
      }
    }
  }

  def handlePriceChange(price: Double)
}
