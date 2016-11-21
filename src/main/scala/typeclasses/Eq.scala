package typeclasses

trait Eq[T]{

  def == (t1: T, t2: T) : Boolean
}

trait Ord[T] extends Eq[T] {
  def > (t1: T, t2: T): Boolean
}


sealed trait Tree[+A]
final case class Empty() extends  Tree[Nothing]
final case class Node[A](value: A) extends Tree[A]
final case class Branch[A](value: A, left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree{

  implicit def eqTree[T]: Eq[Tree[T]] = new Eq[Tree[T]] {
    override def ==(t1: Tree[T], t2: Tree[T]): Boolean = true
  }

  implicit def ordTree[T](implicit eqTree: Eq[Tree[T]]): Ord[Tree[T]] = new Ord[Tree[T]]{

    override def >(t1: Tree[T], t2: Tree[T]): Boolean = true

    override def ==(t1: Tree[T], t2: Tree[T]): Boolean = eqTree.==(t1, t2)
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
  def greaterThan[T: Ord](t1: T, t2: T): Boolean = implicitly[Ord[T]].>(t1, t2)

  println(equality[Tree[Int]](Empty(), Node(2)))

  println(greaterThan[Tree[Int]](Node(1), Node(2)))

  def f(x: List[Int]) = x match {
    case x @ (h :: t) => h
    case _ => 2
  }

  println(f(List(1, 2)))
}
