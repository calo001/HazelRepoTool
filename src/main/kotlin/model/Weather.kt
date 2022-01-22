package model

import com.lectra.koson.arr
import com.lectra.koson.obj

class Weather(
    val id: String,
    val name: String,
    val phonetic: String,
    val emojiCodes: List<String>,
) {
    val json get() = obj {
        "id" to id
        "name" to name
        "phonetic" to phonetic
        "emoji_codes" to arr[emojiCodes]
    }
}