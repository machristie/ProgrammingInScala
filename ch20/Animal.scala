
// 20.6: Question: instead of an abstract type member, why not just use a type parameter?

class Food
abstract class Animal[SuitableFood <: Food] {
  def eat(food:SuitableFood)
}

// Cows
class Grass extends Food
class Cow extends Animal[Grass] {
  override def eat(food: Grass) {
  }
}

// One difference using type parameter instead of abstract
// type member is that you must specify the type parameter
// whenever refering to the Animal type

// For example
//scala> class Fish extends Food
//defined class Fish
//
//scala> val bessy:Animal = new Cow
//<console>:11: error: class Animal takes type parameters
//       val bessy:Animal = new Cow
//                 ^
//

