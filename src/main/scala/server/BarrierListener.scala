package server

import actors.Actor

// case classes for the message type give type safety to parameters during refactoring.  i.e. change price to Int below
case class Changed(price: Double)

trait BarrierListener extends Actor {

  def act() {
    loop {
      react {
        case Changed(price) => changed(price)
      }
    }
  }

  def changed(price: Double)

}
