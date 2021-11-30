package com.Dhanush.net;
import java.io.*;
import java.sql.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
public class DBtoExcel{
    public static void main(String[] args) {
        new DBtoExcel().export();
    }
    public void export() {
        String url = "jdbc:mysql://localhost:3306/dhan?autoReconnect=true&useSSL=false";
        String uname = "root";
        String pass = "s135a135#@#";
        String sql = "SELECT * FROM emp";
            String excelFilePath = "C:\\Users\\saivarma.akarapu\\Excelsheet.xlsx";

        try (Connection connection = DriverManager.getConnection(url, uname, pass)) {


            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);
            System.out.println("successfully exported excel");
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Excelsheet");

            writeHeaderLine(sheet);

            writeDataLines(result, workbook, sheet);

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();

            statement.close();

        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
    }

    private void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Id");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Name");
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Age");

       
    }

    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
                                XSSFSheet sheet) throws SQLException {
        int rowCount = 1;

        while (result.next()) {
        	int id = result.getInt("Id");
            String name= result.getString("name");
            int age=result.getInt("age");

            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(id)
;

            cell = row.createCell(columnCount++);
            cell.setCellValue(name);

            	cell=row.createCell(columnCount++);
            	cell.setCellValue(age);
            

            cell = row.createCell(columnCount++);

            CellStyle cellStyle = workbook.createCellStyle();
          

        }
    }
}