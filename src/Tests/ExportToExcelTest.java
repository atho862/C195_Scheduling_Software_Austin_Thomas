package Tests;

import org.apache.poi.hssf.usermodel.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;

import static org.junit.Assert.assertNull;


public class ExportToExcelTest {
    @Test
    public void exportToExcel_generateTestReport_reportSuccessfullyGenerated() throws IOException {
        //Arrange
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet =  workbook.createSheet("Test");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell =  row.createCell((short) 0);
        cell.setCellValue(new HSSFRichTextString("Hello World!"));
        FileOutputStream outputStream = new FileOutputStream("test.xls");
        workbook.write(outputStream);
        outputStream.close();

        //Act
        File file = new  File(System.getProperty("user.dir") + "\\test.xls");

        //Assert
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.getName().equals("test.xls"));
    }
}
