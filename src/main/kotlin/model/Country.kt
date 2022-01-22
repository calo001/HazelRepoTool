package model

import com.lectra.koson.obj

class Country (
    val id: String,
    val name: String,
    val countryPhonetic: String,
    val nationality: String,
    val nationalityPhonetic: String,
    val language: String,
    val languagePhonetic: String,
    val emojiCode: String,
    val latitude: String,
    val longitude: String,
    val zoom: Int,
    val linkMaps: String,
) {
    val json get() = obj {
        "id" to id
        "name" to name
        "country_phonetic" to countryPhonetic
        "nationality" to nationality
        "nationality_phonetic" to nationalityPhonetic
        "language" to language
        "language_phonetic" to languagePhonetic
        "emoji_code" to emojiCode
        "latitude" to latitude
        "longitude" to longitude
        "zoom" to zoom
        "link_maps" to linkMaps
    }
}