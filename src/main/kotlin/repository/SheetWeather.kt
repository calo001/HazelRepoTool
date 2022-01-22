package repository

import com.lectra.koson.ObjectType
import model.Weather

const val WEATHER_ID_COLUMN_NAME = 0
const val WEATHER_NAME_COLUMN_NAME = 1
const val WEATHER_PHONETIC_COLUMN_NAME = 2
const val WEATHER_EMOJI_COLUMN_NAME = 3

fun getSheetWeather(): List<ObjectType> {
    val sheetWeather = getSheetBy("Weather types")

    return sheetWeather
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
            Weather(
                id = row[WEATHER_ID_COLUMN_NAME],
                name = row[WEATHER_NAME_COLUMN_NAME],
                phonetic = row[WEATHER_PHONETIC_COLUMN_NAME],
                emojiCodes = row[WEATHER_EMOJI_COLUMN_NAME]
                    .split("&&")
                    .map { formatEmojiName(it.trim())}

            ).json
        } ?: listOf()
}