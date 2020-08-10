package Domain.Services;

import Contracts.Interfaces.Services.IReportsService;
import Domain.Dtos.AppointmentDto;
import Domain.Dtos.AppointmentTypeCountDto;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.apache.poi.hssf.model.Sheet;
import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.hssf.record.formula.functions.Row;
import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.Month;

public class ReportsService implements IReportsService {
    private String[] columns;
    private LocalDateTimeStringConverter formatter = new LocalDateTimeStringConverter();

    @Override
    public void exportAppointmentsByCustomerReportToExcel(String customerName, ObservableList<AppointmentDto> appointments) {
        columns = new String[] { "Title", "Type", "Start", "End" };
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("AppointmentsFor" + customerName);

        HSSFFont headerFont = setHeaderFont(workbook);

        HSSFCellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            HSSFCell cell = headerRow.createCell((short) i);
            cell.setCellValue(new HSSFRichTextString(columns[i]));
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        for (AppointmentDto appointmentDto : appointments
             ) {
            HSSFRow row = sheet.createRow(rowNum++);
            row.createCell((short) 0)
                    .setCellValue(new HSSFRichTextString(appointmentDto.getTitle()));
            row.createCell((short) 1)
                    .setCellValue(new HSSFRichTextString(appointmentDto.getType()));
            row.createCell((short) 2)
                    .setCellValue(new HSSFRichTextString(String.valueOf(formatter.toString(appointmentDto.getStart()))));
            row.createCell((short) 3)
                    .setCellValue(new HSSFRichTextString(String.valueOf(formatter.toString(appointmentDto.getEnd()))));

        }

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn((short) i);
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("Appointments_For_" + customerName + ".xls");
            workbook.write(fileOut);
            fileOut.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void exportAppointmentTypeByMonthToExcel(ObservableList<AppointmentTypeCountDto> appointmentTypeCountDtos, Month month) {
        columns = new String[] { "Appointment Type", "Count" };
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("AppointmentTypeByMonth");

        HSSFFont headerFont = setHeaderFont(workbook);

        HSSFCellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++){
            HSSFCell cell = headerRow.createCell((short) i);
            cell.setCellValue(new HSSFRichTextString(columns[i]));
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        for (AppointmentTypeCountDto appointmentTypeCountDto : appointmentTypeCountDtos){
            HSSFRow row = sheet.createRow(rowNum++);
            row.createCell((short)0)
                    .setCellValue(new HSSFRichTextString(appointmentTypeCountDto.getType()));

            row.createCell((short)1)
                    .setCellValue(new HSSFRichTextString(String.valueOf(appointmentTypeCountDto.getCount())));
        }

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn((short)i);
        }
        try {
            FileOutputStream fileOut = new FileOutputStream("Appointment_Types_" + month.getValue() + ".xls");
            workbook.write(fileOut);
            fileOut.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void exportScheduleByConsultantToExcel(String consultantName, ObservableList<AppointmentDto> appointments) {
        columns = new String[]{ "Title", "Customer", "Type","Start", "End" };
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("AppointmentsFor" + consultantName);

        HSSFFont headerFont = setHeaderFont(workbook);

        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);

        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            HSSFCell cell = headerRow.createCell((short)i);
            cell.setCellValue(new HSSFRichTextString(columns[i]));
            cell.setCellStyle(headerStyle);
        }

        int rowNum = 1;
        for (AppointmentDto appointment : appointments
             ) {
            HSSFRow row = sheet.createRow(rowNum++);
            row.createCell((short) 0)
                    .setCellValue(new HSSFRichTextString(appointment.getTitle()));

            row.createCell((short) 1)
                    .setCellValue(new HSSFRichTextString(appointment.getCustomerName()));

            row.createCell((short) 2)
                    .setCellValue(new HSSFRichTextString(appointment.getType()));

            row.createCell((short) 3)
                    .setCellValue(new HSSFRichTextString(formatter.toString(appointment.getStart())));

            row.createCell((short) 4)
                    .setCellValue(new HSSFRichTextString(formatter.toString(appointment.getEnd())));
        }

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn((short)i);
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("ScheduleFor" + consultantName + ".xls");
            workbook.write(fileOut);
            fileOut.close();
        }
        catch (Exception e){
            System.out.println(e);
        }


    }

    private HSSFFont setHeaderFont(HSSFWorkbook workbook){
        HSSFFont headerFont = workbook.createFont();
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(HSSFFont.COLOR_NORMAL);

        return headerFont;
    }
}
