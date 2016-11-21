package imports



object Test extends App {

  import A._
  import B.{a => _, _}
  import B.{a => ba}
  import p1.T1

  println(a)
  println(ba)
  println(b)

  val t: T1 = new T1(){

  }

  import t.AA.{a => AAa, _}
  import t.BB._

  println(AAa)
  println(bb)

}
