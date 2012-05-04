package code.snippet

import xml.NodeSeq
import net.liftweb.http.SHtml._
import net.liftweb.util.Helpers._
import net.liftweb.http._

import js.JsCmds
import js.JsCmds._
import net.liftweb.common._
import net.liftweb.http.SHtml
import server.model.{Underlying, SingleTrade}
import server.{TradeMessage, TradeRoutingActor}
import net.liftweb.common.Empty

object TradeInputSnippet {

  object id extends RequestVar("")
  object strike extends RequestVar("")
  object underlying extends RequestVar(Underlying.CLP)

  def add(xhtml : NodeSeq) : NodeSeq = {

    def processEntryAdd() {
      println("Form was submitted")

      try {
        val strikeDouble = strike.is.toDouble
        TradeRoutingActor ! TradeMessage("gui", "????", SingleTrade(id.is, underlying, strikeDouble))
      }
      catch {
        case e: NumberFormatException => S.error("strike", "Invalid number '"+strike+"'")
      }
    }

    val underlyings: List[(String, String)] = Underlying.values.toList.map(ul => (ul.toString, ul.toString))
    bind("entry", xhtml,
      "id" -> SHtml.text(id.is, id.set(_)),
      "strike" -> SHtml.text(strike, strike.set(_)),
      "underlying" -> SHtml.select(underlyings, Empty, setUnderlying(_)),
      "submit" -> submit("Submit", processEntryAdd)
    )
  }

  def setUnderlying(s: String): Any = {
    underlying.set( Underlying.withName(s) )
  }
}

