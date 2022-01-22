package repository

import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import provider.FileProvider
import java.util.*

operator fun Row.get(index: Int): String {
    return try {
        getCell(index, Row.MissingCellPolicy.RETURN_NULL_AND_BLANK).stringCellValue
    } catch (e: Exception) {
        try {
            getCell(index, Row.MissingCellPolicy.RETURN_NULL_AND_BLANK).numericCellValue.toInt().toString()
        } catch (e: Exception) { "" }
    }
}

fun getSheetBy(name: String): XSSFSheet? {
    return FileProvider.excelFile.getSheet(name)
}

fun formatEmojiName(name: String): String {
    return name
        .toLowerCase((Locale.getDefault()))
        .replace("-", "_")
        .let {
            "openmoji_$it"
        }
}