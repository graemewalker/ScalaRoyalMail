package code.comet

import net.liftweb.http.js.JsCmds
import xml.{Text, NodeSeq}
import net.liftweb.http.js.JsCmds.SetHtml
import net.liftweb.util.{Helpers, ClearClearable}
import Helpers._

import net.liftweb.http.{SHtml, CometListener, CometActor, ListenerManager}

import net.liftweb._
import http._
import net.liftweb.common.{Box, Full}
import net.liftweb.http.js.JsCmds.{SetHtml}
import server.model.{HasBarrier, Trade}

class TradeBlotter extends CometActor with CometListener {

  //  implicit def tradeToString(t: Trade): String = {
  //    t.toString
  //  }

  private var blotterTrades = Vector[Trade]()
  protected def registerWith = TradeActivityServer

  override def lowPriority = {
    case newTrades : Vector[Trade] => {
//            botterTrades = Vector[String]() ++ newTrades.map(trade => <span id={trade.id}>{trade.toString}</span>)
//      blotterTrades = newTrades.map(t => t.toString)
      blotterTrades = newTrades
      println("*** lowPriority update call " + blotterTrades.size);
      reRender()
//      partialUpdate(SetHtml("time", Text("boom!")))
    }
  }

  private def breachClass(trade: Trade) : String =  {
    trade match {
      case g2: HasBarrier => if (g2.breached) "trade breached" else "trade"
      case _ => "trade"
    }
  }

  private def renderMessages =
    <div>
      {blotterTrades.map(trade => <li class={breachClass(trade)}>{trade.toString}</li>)}
    </div>

//  def render = bind("messages" -> renderMessages)

  //  def render = "li *" #> JsCmds.SetHtml("<span>"+(Vector[String]() ++ trades.map(t => t.toString)) & ClearClearable
//  def render = "li *" #> blotterTrades & "li [class]" #> "foo"
  def render = renderMessages

//  def render = bind("li" ->
//    SHtml.ajaxButton("Tock!", {
//      () => ClockMaster ! Tick
//      Noop
//    }
//    )

  //  def spanIt(trades: Vector[Trade]): String = {
  //    trades.foreach(trade => "<span id="+trade.id+">"+trade.toString+"</span>)")
  //  }
}
