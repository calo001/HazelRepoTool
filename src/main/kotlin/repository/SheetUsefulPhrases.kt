package repository

import com.lectra.koson.ObjectType
import model.CategoryPhrase
import model.Phrase

const val USEFUL_PHRASES_SHEET_NAME = "Useful phrases"
const val PHASE_ID_COLUMN = 0
const val CATEGORY_ID_COLUMN = 1
const val CATEGORY_NAME_COLUMN = 2
const val EXPRESSION_NAME_COLUMN = 3
const val HOW_TO_USE_NAME_COLUMN = 4
const val EMOJI_CODE_NAME_COLUMN = 5

fun getUsefulPhrases(): List<ObjectType> {
    val usefulPhrasesSheet = getSheetBy(USEFUL_PHRASES_SHEET_NAME)

    return usefulPhrasesSheet
        ?.iterator()
        ?.asSequence()
        ?.drop(1)
        ?.filterNotNull()
        ?.filterNot { row ->
            row.cellIterator()
                .asSequence()
                .all { cell ->
                    cell.stringCellValue.isNullOrEmpty()
                }
        }?.groupBy { row ->
            row[CATEGORY_NAME_COLUMN]
        }?.map { mapCategoryRow ->
            CategoryPhrase(
                id = mapCategoryRow.value.first()[CATEGORY_ID_COLUMN],
                category = mapCategoryRow.key,
                emojiCode = formatEmojiName(mapCategoryRow.value.first()[EMOJI_CODE_NAME_COLUMN]),
                phrases = mapCategoryRow.value.map { row ->
                    Phrase(
                        id = row[PHASE_ID_COLUMN],
                        expression = row[EXPRESSION_NAME_COLUMN],
                        howToUse = row[HOW_TO_USE_NAME_COLUMN]
                    )
                }
            ).json
        } ?: listOf()
}