package model

import com.lectra.koson.obj

class Animal(
    val id: String,
    val name: String,
    val phonetic: String,
    val emojiCode: String,
) {
    val json get() = obj {
        "id" to id
        "name" to name
        "phonetic" to phonetic
        "emoji_code" to emojiCode
    }
}