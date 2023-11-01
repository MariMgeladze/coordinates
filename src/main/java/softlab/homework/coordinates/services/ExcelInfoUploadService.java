package softlab.homework.coordinates.services;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import softlab.homework.coordinates.entities.ExcelInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ExcelInfoUploadService {

    public static List<ExcelInfo> getExcelInfoFromExcel(InputStream inputStream) {
        List<ExcelInfo> excelData = new ArrayList<>();

        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheet("ExcelData");
            Iterator<Row> rows = sheet.iterator();
            //   XSSFWorkbook workbook= new XSSFWorkbook(inputStream);
            // XSSFSheet sheet = workbook.getSheet("excelData");

            int rowIndex = 0;

            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                ExcelInfo excelInfo = new ExcelInfo();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> excelInfo.setId((int) cell.getNumericCellValue());
                        case 1 -> excelInfo.setPoint1(cell.getNumericCellValue());
                        case 2 -> excelInfo.setPoint2(cell.getNumericCellValue());
                        case 3 -> excelInfo.setNumber(cell.getStringCellValue());
                    }
                    cellIndex++;
                }
                excelData.add(excelInfo);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return excelData;
    }

    public static boolean isValidExcelFile(MultipartFile file) {
        return false;
    }
}


