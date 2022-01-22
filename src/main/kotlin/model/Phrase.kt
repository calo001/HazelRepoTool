package model

import com.lectra.koson.arr
import com.lectra.koson.obj

class CategoryPhrase(
    val id: String,
    val category: String,
    val phrases: List<Phrase>,
    val emojiCode: String,
) {
    val json get() = obj {
        "id" to id
        "category" to category
        "phrases" to arr[phrases.map { it.json }]
        "emoji_code" to emojiCode
    }
}

class Phrase(
    val id: String,
    val expression: String,
    val howToUse: String,
) {
    val json get() = obj {
        "id" to id
        "expression" to expression
        "how_to_use" to howToUse
    }
}
