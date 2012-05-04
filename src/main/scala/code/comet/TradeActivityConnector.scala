package code.comet


import net.liftweb._
import http._
import server.model.{HasBarrier, Trade}

class TradeActivityConnector extends CometActor with CometListener {

  // Implicit def to convert a Trade to a String
  implicit def tradeToString(t: Trade): String = { t.toString() }

  def sortById(t1: Trade, t2: Trade) = (t1.id < t2.id)

  private var blotterTrades = Vector[Trade]()

  protected def registerWith = TradeActivityActor

  override def lowPriority = {
    case newTrades: Vector[Trade] => {
      blotterTrades = newTrades.sortWith(sortById)
      reRender(false)
    }
  }

  // 2 Examples of generating the XHTML
//  def render = firstClassXmlStyle
  def render = renderCssSelectorStyle

  private def firstClassXmlStyle =
    <div>
      {blotterTrades.map(trade => <div class={classesFor(trade).getOrElse("")}>{trade}</div>)}
    </div>

  def renderCssSelectorStyle = {
    "div *" #> blotterTrades.map(trade =>
      ".trade *" #> trade &
        ".trade [class+]" #> classesFor(trade)
    )
  }

  // Example of use of Something and None Options
  private def classesFor(trade: Trade): Option[String] = {
    trade match {
      case barrierTrade: HasBarrier if (barrierTrade.breached) => Some("breached")
      case _ => None
    }
  }

}
