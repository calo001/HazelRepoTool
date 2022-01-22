package repository

import com.lectra.koson.ObjectType
import model.Verb
import model.VerbForm

const val IRREG_VERB_ID = 0

const val IRREG_VERB_BASE = 1
const val IRREG_VERB_BASE_PHONETIC = 2
const val IRREG_VERB_BASE_EXAMPLES = 3

const val IRREG_VERB_SIMP_PAST = 4
const val IRREG_VERB_SIMP_PAST_PHONETIC = 5
const val IRREG_VERB_SIMP_PAST_EXAMPLES = 6

const val IRREG_VERB_PAST_PARTICIPLE = 7
const val IRREG_VERB_PAST_PARTICIPLE_PHONETIC = 8
const val IRREG_VERB_PAST_PARTICIPLE_EXAMPLES = 9

const val IRREG_VERB_ING_FORM = 10
const val IRREG_VERB_ING_FORM_PHONETIC = 11
const val IRREG_VERB_ING_FORM_EXAMPLES = 12

const val EMOJI_CODE = 13

fun getVerbs(sheetName: String, type: String): List<ObjectType> {
    val verbSheet = getSheetBy(sheetName)
     return verbSheet
         ?.asIterable()
         ?.drop(1)
         ?.filterNotNull()
         ?.filterNot { row ->
             row.cellIterator()
                 .asSequence()
                 .all { cell ->
                     cell.stringCellValue.isNullOrEmpty()
                 }
         }?.filterNot { row ->
             row.cellIterator()
                 .asSequence()
                 .any {
                     it.row[EMOJI_CODE].isEmpty()
                 }
         }
         ?.map { row ->
             Verb(
                 id = row[IRREG_VERB_ID],
                 base = VerbForm(
                     value = row[IRREG_VERB_BASE],
                     phonetic = row[IRREG_VERB_BASE_PHONETIC],
                     examples = row[IRREG_VERB_BASE_EXAMPLES]
                         .split("&&")
                         .map { it.trim() },
                 ),
                 simplePast = VerbForm(
                     value = row[IRREG_VERB_SIMP_PAST],
                     phonetic = row[IRREG_VERB_SIMP_PAST_PHONETIC],
                     examples = row[IRREG_VERB_SIMP_PAST_EXAMPLES]
                         .split("&&")
                         .map { it.trim() },
                 ),
                 pastParticiple = VerbForm(
                     value = row[IRREG_VERB_PAST_PARTICIPLE],
                     phonetic = row[IRREG_VERB_PAST_PARTICIPLE_PHONETIC],
                     examples = row[IRREG_VERB_PAST_PARTICIPLE_EXAMPLES]
                         .split("&&")
                         .map { it.trim() },
                 ),
                 ing = VerbForm(
                     value = row[IRREG_VERB_ING_FORM],
                     phonetic = row[IRREG_VERB_ING_FORM_PHONETIC],
                     examples = row[IRREG_VERB_ING_FORM_EXAMPLES]
                         .split("&&")
                         .map { it.trim() },
                 ),
                 emojiCode = formatEmojiName(row[EMOJI_CODE])
             ).json
         }
         ?.toList().orEmpty()
}
