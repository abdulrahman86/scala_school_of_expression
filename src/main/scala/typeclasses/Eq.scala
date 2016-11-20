package typeclasses

trait Eq[T]{

  def == (t1: T, t2: T) : Boolean
}


sealed trait Tree[+A]
final case class Empty() extends  Tree[Nothing]
final case class Node[A](value: A) extends Tree[A]
final case class Branch[A](value: A, left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree{

  implicit def eqTree[T]: Eq[Tree[T]] = new Eq[Tree[T]] {
    override def ==(t1: Tree[T], t2: Tree[T]): Boolean = true
  }
}

case class MyClass()

object MyClass {

  implicit object EqMyClass extends Eq[MyClass] {
    override def ==(t1: MyClass, t2: MyClass): Boolean = true
  }
}

object App1 extends App{

  import Tree._

  def equality[T: Eq](t1: T, t2: T): Boolean = implicitly[Eq[T]].==(t1, t2)

  println(equality[Tree[Int]](Empty(), Node(2)))

  //eqTree[Int].==(Empty(), Node(2))
}
