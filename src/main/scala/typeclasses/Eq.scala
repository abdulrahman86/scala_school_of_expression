package typeclasses

trait Eq[T]{

  def == (t1: T, t2: T) : Boolean
}


sealed trait Tree[+A]
final case class Node[A](value: A) extends Tree[A]
final case class Branch[A](value: A, left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree{

  implicit def eqTree[T, A[T] <: Tree[T]]: Eq[A[T]] = new Eq[A[T]] {
    override def ==(t1: A[T], t2: A[T]): Boolean = true
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

  def equality[T](t1: T, t2: T)(implicit eq: Eq[T]): Boolean = eq.==(t1, t2)

  println(equality(Node(1), Node(2)))

  println(equality(MyClass(), MyClass()))
}
