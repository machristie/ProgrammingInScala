abstract class Food(val name: String) {
  override def toString = name
}
object Apple extends Food("Apple")
object Orange extends Food("Orange")
object Cream extends Food("Cream")
object Sugar extends Food("Sugar")

class Recipe(
  val name: String,
  val ingredients: List[Food],
  val instructions: String
) {
  override def toString = name
}

trait FoodCategories {
  case class FoodCategory(name: String, foods: List[Food])
  def allCategories: List[FoodCategory]
}

trait Foods {
  def allFoods: List[Food]
  def foodNamed(name: String) =
    allFoods.find(f => f.name == name)
}

trait Recipes {
  def allRecipes: List[Recipe]
}

abstract class Database extends FoodCategories with Foods with Recipes {
}

trait SimpleFoods extends Foods {
  object Pear extends Food("Pear")
  def allFoods = List(Apple, Pear)
  def allCategories = Nil
}


trait SimpleRecipes extends Recipes { // Does not compile
  object FruitSalad extends Recipe(
    "fruit salad",
    List(Apple, Pear),  // Uh oh
    "Mix it all together."
    )
  def allRecipes = List(FruitSalad)
}

