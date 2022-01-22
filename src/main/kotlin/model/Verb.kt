package model

import com.lectra.koson.arr
import com.lectra.koson.obj

class Verb(
    val id: String,
    val base: VerbForm,
    val simplePast: VerbForm,
    val pastParticiple: VerbForm,
    val ing: VerbForm,
    val emojiCode: String
) {
    val json get() = obj {
        "id" to id
        "base" to base.json
        "simplePast" to simplePast.json
        "pastParticiple" to pastParticiple.json
        "ing" to ing.json
        "emojiCode" to emojiCode
    }
}

class VerbForm(
    val value: String,
    val phonetic: String,
    val examples: List<String>,
) {
    val json get() = obj {
        "verb" to value
        "phonetic" to phonetic
        "examples" to if (examples.any { it.isEmpty() }) arr else arr[examples]
    }
}
