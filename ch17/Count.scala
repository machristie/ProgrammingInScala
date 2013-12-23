
// Using immutable Set to count unique words in a string of text
def countWords(text:String):Int = {

  val lowerWordsSet = for( word <- text.split("[ !,.]+") ) yield word.toLowerCase
  (Set.empty ++ lowerWords).size
}
