package code.controls


object Controls {

  /*
   Calling code:
   fork {
      doSomethingComplex()
   }
  */
  def fork(f: Any) {
    new Thread() {
      override def run() {
        f
      }
    }.start()
  }

  /*
   Calling code:
  using(myBufferedReader) {
    reader => println(reader.readLine())
  }
  */
  def using[A <: {def close() : Unit}, B](param: A)(f: A => B): B = {
    try {
      f(param)
    } finally {
      param.close()
    }
  }
}