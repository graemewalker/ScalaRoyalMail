package code.snippet

import xml.NodeSeq
import net.liftweb.http.SHtml._
import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml
import server.model.{Underlying, SingleTrade}
import server.{TradeMessage, TradeInputActor}

class TradeInput {

  def add(xhtml : NodeSeq) : NodeSeq = {
    var id = ""
    var strike = ""
    var underlying = ""

    def processEntryAdd() {
      println("Form was submitted")

      TradeInputActor ! new TradeMessage("gui", "????", SingleTrade(id, Underlying.withName(underlying), 666))
    }

    bind("entry", xhtml,
      "id" -> SHtml.text(id, id = _),
      "strike" -> SHtml.text(strike, strike = _),
      "underlying" -> SHtml.text(underlying, underlying = _),
      "submit" -> submit("Submit", processEntryAdd)
    )
  }

}
