package repository

import com.lectra.koson.ObjectType
import model.Color

const val COLOR_ID_COLUMN = 0
const val COLOR_NAME_COLUMN = 1
const val COLOR_PHONETIC_COLUMN = 2
const val COLOR_CODE_COLUMN = 3
const val COLOR_TYPE_COLUMN = 4
const val COLOR_EXAMPLES_COLUMN = 5
const val COLOR_SHEET_NAME = "Colors"

fun getColors(): List<ObjectType> {
    val colorsSheet = getSheetBy(name = COLOR_SHEET_NAME)

    return colorsSheet
        ?.asIterable()
        ?.drop(1)
        ?.filterNotNull()
        ?.filterNot { row ->
            row.cellIterator()
                .asSequence()
                .all { cell ->
                    cell.stringCellValue.isNullOrEmpty()
                }
        }?.map { row ->
            Color(
                id =        row[COLOR_ID_COLUMN],
                name =      row[COLOR_NAME_COLUMN],
                phonetic =  row[COLOR_PHONETIC_COLUMN],
                code =      row[COLOR_CODE_COLUMN],
                type =      row[COLOR_TYPE_COLUMN],
                examples =  row[COLOR_EXAMPLES_COLUMN]
                    .split("&&")
                    .map { it.trim() },
            ).json
        }
        .orEmpty()
}