package model

import com.lectra.koson.obj

class Season(
    val id: String,
    val name: String,
    val seasonPhonetic: String,
    val emojiCode: String,
) {
    val json get() = obj {
        "id" to id
        "name" to name
        "season_phonetic" to seasonPhonetic
        "emoji_code" to emojiCode
    }
}