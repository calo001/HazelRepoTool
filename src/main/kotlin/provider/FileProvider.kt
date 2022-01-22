package provider

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream

const val EXCEL_FILE_NAME = "src/main/resources/HazelDB.xlsx"
const val JSON_FILE_NAME = "src/main/resources/hazel.json"

object FileProvider {
    val hazelJson by lazy {
        File(JSON_FILE_NAME)
    }

    private val excelInputStream by lazy {
        FileInputStream(File(EXCEL_FILE_NAME))
    }

    val excelFile by lazy {
        XSSFWorkbook(excelInputStream)
    }
}

