package model

import com.lectra.koson.arr
import com.lectra.koson.obj

class Color(
    val id:         String,
    val name:       String,
    val phonetic:   String,
    val code:       String,
    val type:       String,
    val examples:   List<String>,
) {
    val json get() = obj {
        "id" to id
        "name" to name
        "code" to code
        "type" to type
        "phonetic" to phonetic
        "examples" to if (examples.any { it.isEmpty() }) arr else arr[examples]
    }
}