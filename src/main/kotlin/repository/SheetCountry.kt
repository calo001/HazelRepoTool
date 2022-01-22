package repository

import com.lectra.koson.ObjectType
import model.Country

const val COUNTRY_ID_COLUMN_NAME = 0
const val COUNTRY_NAME_COLUMN_NAME = 1
const val COUNTRY_NAME_PHONETIC_COLUMN_NAME = 2
const val COUNTRY_NATIONALITY_COLUMN_NAME = 3
const val COUNTRY_NATIONALITY_PHONETIC_COLUMN_NAME = 4
const val COUNTRY_LANGUAGE_COLUMN_NAME = 5
const val COUNTRY_LANGUAGE_PHONETIC_COLUMN_NAME = 6
const val COUNTRY_EMOJI_COLUMN_NAME = 7
const val COUNTRY_EMOJI_LATITUDE = 8
const val COUNTRY_EMOJI_LONGITUDE = 9
const val COUNTRY_ZOOM_COLUMN_NAME = 10
const val COUNTRY_LINK_MAPS_COLUMN_NAME = 11

fun getSheetCountry(): List<ObjectType> {
    val sheetCountry = getSheetBy("Countries and Nationalities")

    return sheetCountry
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
            Country(
                id = row[COUNTRY_ID_COLUMN_NAME],
                name = row[COUNTRY_NAME_COLUMN_NAME],
                countryPhonetic = row[COUNTRY_NAME_PHONETIC_COLUMN_NAME],
                nationality = row[COUNTRY_NATIONALITY_COLUMN_NAME],
                nationalityPhonetic = row[COUNTRY_NATIONALITY_PHONETIC_COLUMN_NAME],
                language = row[COUNTRY_LANGUAGE_COLUMN_NAME],
                languagePhonetic = row[COUNTRY_LANGUAGE_PHONETIC_COLUMN_NAME],
                emojiCode = formatEmojiName(row[COUNTRY_EMOJI_COLUMN_NAME]),
                latitude = row.getCell(COUNTRY_EMOJI_LATITUDE).numericCellValue.toString(),
                longitude = row.getCell(COUNTRY_EMOJI_LONGITUDE).numericCellValue.toString(),
                zoom = row[COUNTRY_ZOOM_COLUMN_NAME].toIntOrNull() ?: 1,
                linkMaps = row[COUNTRY_LINK_MAPS_COLUMN_NAME],
            ).json
        } ?: listOf()
}