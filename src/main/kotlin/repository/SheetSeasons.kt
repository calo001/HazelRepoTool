package repository

import com.lectra.koson.ObjectType
import model.Season

const val SEASONS_ID_COLUMN_NAME = 0
const val SEASONS_NAME_COLUMN_NAME = 1
const val SEASONS_NAME_PHONETIC_COLUMN_NAME = 2
const val SEASONS_EMOJI_CODE_COLUMN_NAME = 3

fun getSheetSeasons(): List<ObjectType> {
    val sheetSeasons = getSheetBy("Seasons")

    return sheetSeasons
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
            Season(
                id = row[SEASONS_ID_COLUMN_NAME],
                name = row[SEASONS_NAME_COLUMN_NAME],
                seasonPhonetic = row[SEASONS_NAME_PHONETIC_COLUMN_NAME],
                emojiCode = formatEmojiName(row[SEASONS_EMOJI_CODE_COLUMN_NAME]),
            ).json
        } ?: emptyList()
}