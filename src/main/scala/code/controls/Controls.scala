package code.controls

import server.model.Underlying._
import server.{DemoHelper, Tick}
import net.liftweb.http.js.JsCmds

object Controls {

  def fork(f: Any) {
      new Thread() {
        override def run() {
          f
        }
      }.start()
  }
}