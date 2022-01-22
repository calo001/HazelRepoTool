import com.lectra.koson.arr
import com.lectra.koson.obj
import provider.FileProvider
import repository.*

fun main() {
    val usefulPhrases = getUsefulPhrases()
    val colors = getColors()
    val irregularVerbs = getIrregularVerbs()
    val regularVerbs = getRegularVerbs()
    val countries = getSheetCountry()
    val animals = getSheetAnimal()
    val seasons = getSheetSeasons()
    val weather = getSheetWeather()

    val jsonhazel = obj {
        "useful_phrases" to arr[usefulPhrases]
        "colors" to arr[colors]
        "irregular_verbs" to arr[irregularVerbs]
        "regular_verbs" to arr[regularVerbs]
        "countries" to arr[countries]
        "animals" to arr[animals]
        "seasons" to arr[seasons]
        "weather" to arr[weather]
    }.pretty()

    println(jsonhazel)
    FileProvider.hazelJson.writeText(jsonhazel)
}