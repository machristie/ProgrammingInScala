
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

trait FoodCategoriesComponent {
  val foodCategories: FoodCategories
  trait FoodCategories {
    case class FoodCategory(name: String, foods: List[Food])
    def allCategories: List[FoodCategory]
  }
}

trait FoodsComponent {
  val foods: Foods
  trait Foods {
    def allFoods: List[Food]
    def foodNamed(name: String) =
      allFoods.find(f => f.name == name)
  }
}

trait RecipesComponent {
  val recipes: Recipes
  trait Recipes {
    def allRecipes: List[Recipe]
  }
}

trait DatabaseComponent extends FoodCategoriesComponent
    with FoodsComponent with RecipesComponent {
  val database: Database

  trait Database extends FoodCategories with Foods with Recipes {
  }
}

// Implement Foods and FoodCategories at once
trait SimpleFoodsComponent extends FoodsComponent with FoodCategoriesComponent {

  val foods = new Object with SimpleFoods
  val foodCategories = foods
  trait SimpleFoods extends Foods with FoodCategories {
    object Pear extends Food("Pear")
    def allFoods = List(Apple, Pear)
    def allCategories = Nil
  }
}


trait SimpleRecipesComponent extends RecipesComponent {
  this: SimpleFoodsComponent =>

  val recipes = new Object with SimpleRecipes
  trait SimpleRecipes extends Recipes {
    object FruitSalad extends Recipe(
      "fruit salad",
      List(Apple, foods.Pear),
      "Mix it all together."
      )
    def allRecipes = List(FruitSalad)
  }
}

trait SimpleDatabaseComponent extends DatabaseComponent
    with SimpleFoodsComponent with SimpleRecipesComponent {

  val database = new Database with SimpleFoods with SimpleRecipes
}

trait BrowserComponent {
  this: DatabaseComponent =>

  val browser: Browser

  trait Browser {
    def recipesUsing(food: Food)
    def displayCategory(category: database.FoodCategory)
  }
}

trait SimpleBrowserComponent extends BrowserComponent {
  this: SimpleDatabaseComponent =>

  val browser = new Object with SimpleBrowser
  trait SimpleBrowser extends Browser {

    def recipesUsing(food: Food) =
      database.allRecipes.filter(recipe =>
          recipe.ingredients.contains(food))

    def displayCategory(category: database.FoodCategory) {
      println(category)
    }
  }
}
