package code.comet


import net.liftweb._
import http._
import server.model.{HasBarrier, Trade}

class TradeBlotter extends CometActor with CometListener {
  //  implicit def tradeToString(t: Trade): String = {
  //    t.toString
  //  }

  def sortById(t1: Trade, t2: Trade) = (t1.id < t2.id)

  private var blotterTrades = Vector[Trade]()

  protected def registerWith = TradeActivityServer

  override def lowPriority = {
    case newTrades: Vector[Trade] => {
      blotterTrades = newTrades.sortWith(sortById)
      reRender(false)
      //      partialUpdate(SetHtml("time", Text("boom!")))
    }
  }

  private def classesFor(trade: Trade): String = {
    trade match {
      case barrierTrade: HasBarrier if (barrierTrade.breached) => "breached"
      case _ => ""
    }
  }

//  def render = firstClassXmlStyle
  def render = renderCssSelectorStyle

  private def firstClassXmlStyle =
    <div>
        {blotterTrades.map(trade => <div class={classesFor(trade)}>{trade.toString}</div>)}
    </div>

  def renderCssSelectorStyle = {
    "div *" #> blotterTrades.map(trade =>
      ".trade *" #> trade.toString &
        ".trade [class+]" #> classesFor(trade)
    )
  }

  //  def render = {
  //    "type=submit" #> SHtml.submit("Register", process,
  //      "onclick" -> JsIf(JsEq(ValById("first_name"), ""), Alert("alert") & JsReturn(false)).toJsCmd)
  //  }

  //  def render = bind("li" ->
  //    SHtml.ajaxButton("Tock!", {
  //      () => ClockMaster ! Tick
  //      Noop
  //    }
  //    )

  //  def render = "li *" #> JsCmds.SetHtml("<span>"+(Vector[String]() ++ trades.map(t => t.toString)) & ClearClearable
  //  def render = "li *" #> blotterTrades & "li [class]" #> "foo"
  //  def render = {
  //    bind("tradeBlotter", "messages" -> renderMessages)
  //  }

}
