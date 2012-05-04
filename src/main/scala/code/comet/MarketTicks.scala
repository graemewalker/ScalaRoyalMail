package code.comet

import net.liftweb.http.{CometListener, CometActor}
import server.{Tick, MarketDataFeed}

class MarketTicks extends CometActor with CometListener {
  private var ticks = List[Tick]()

  protected def registerWith = MarketTicksActivityServer

  def render = {
    <div>{ticks.take(6).map(tick => printTick(tick))}</div>
  }

  def printTick(tick: Tick): String = {
    " ** " + tick.underlying + ":" + tick.price
  }

  override def lowPriority = {
    case newTicks: List[Tick] => {
      ticks = newTicks
      reRender(false)
    }
  }
}
