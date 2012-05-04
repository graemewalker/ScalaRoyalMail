package code.comet

import net.liftweb.http.{CometListener, CometActor}
import server.{Tick, MarketDataPublisher}

class MarketActivityConnector extends CometActor with CometListener {
  private var ticks = List[Tick]()

  protected def registerWith = MarketActivityActor

  def render = {
    <div>{printHead(ticks) ++ printRest(ticks)}</div>
  }

  def printHead(ticks: List[Tick]) = {
    <span class="latestTick">{" ** " + ticks.head.underlying + ":" + ticks.head.price}</span>
  }

  def printRest(ticks: List[Tick]) = {
    ticks.tail.take(5).map(tick => <span>{" ** " + tick.underlying + ":" + tick.price}</span>)

  }

  override def lowPriority = {
    case newTicks: List[Tick] => {
      ticks = newTicks
      reRender(false)
    }
  }
}
