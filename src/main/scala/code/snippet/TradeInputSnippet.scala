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

  // Objects and request vars to allow state persistence across requests
  object id extends RequestVar("")
  object strike extends RequestVar("")

  def add(xhtml : NodeSeq) : NodeSeq = {

    // Example of non persisted variable
    // AND an example of a variable that is closed over by a method and kept in scope on the heap for use by Submit
    var underlying = Underlying.CLP;

    val underlyings: List[(String, String)] = Underlying.values.toList.map(ul => (ul.toString, ul.toString))

    //Method within Method
    def processEntryAdd() {
      println("Form was submitted")

      try {
        val strikeDouble = strike.is.toDouble
        TradeRoutingActor ! TradeMessage("gui", "someRef", SingleTrade(id.is, underlying, strikeDouble))
      }
      catch {
        case e: NumberFormatException => S.error("strike", "Invalid number '"+strike+"'")
      }
    }

    def setUnderlying(s: String): Any = {
      underlying = Underlying.withName(s)
    }

    bind("entry", xhtml,
      "id" -> text(id.is, id.set(_)),
      "strike" -> text(strike, strike.set(_)),
      "underlying" -> select(underlyings, Empty, setUnderlying(_)),
      "submit" -> submit("Submit", processEntryAdd)
    )
  }
}

