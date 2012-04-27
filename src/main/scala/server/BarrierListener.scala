package server

import actors.Actor

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
