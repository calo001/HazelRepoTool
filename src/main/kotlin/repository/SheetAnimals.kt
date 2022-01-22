package repository

import com.lectra.koson.ObjectType
import model.Animal

const val ANIMAL_ID_COLUMN_NAME = 0
const val ANIMAL_NAME_COLUMN_NAME = 1
const val ANIMAL_PHONETIC_COLUMN_NAME = 2
const val ANIMAL_EMOJI_COLUMN_NAME = 3

fun getSheetAnimal(): List<ObjectType> {
    val sheetAnimals = getSheetBy("Animals")

    return sheetAnimals
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
            Animal(
                id = row[ANIMAL_ID_COLUMN_NAME],
                name = row[ANIMAL_NAME_COLUMN_NAME],
                phonetic = row[ANIMAL_PHONETIC_COLUMN_NAME],
                emojiCode = formatEmojiName(row[ANIMAL_EMOJI_COLUMN_NAME])
            ).json
        } ?: listOf()
}