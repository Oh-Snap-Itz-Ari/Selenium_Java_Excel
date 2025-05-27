package resources.data.excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.table.DefaultTableModel;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DataExcel {

    private DefaultTableModel dataTable; // Define y crea una tabla en Java con filas y columnas
    FileInputStream excelFile;
    Workbook workbook; // Metodos de Excel para la ubicación entre filas y columnas
    Sheet sheet; // Llamado al nombre de la hoja con la que se va a interactuar (Excel)
    FileOutputStream fos; // Manejo de escritura en Columnas

    String sheetName = "Hoja1"; // Nombre de la hoja de Excel a usar
    public String excelFilePath = "D:/Información/Documentos/Automatización/Selenium Java/Guia 001/Selenium_Excel/src/main/java/resources/data/database/UserData.xlsx"; // Ruta donde se encuentra el archivo de Excel
    int numberFile = 0; //Número de fila a actualizar (Índice 0 - Primera posición después del encabezado A2, B2)

    public void excelConnection(){
        try {
            excelFile = new FileInputStream(excelFilePath);
            workbook = new XSSFWorkbook(excelFile);
            // Sheet sheet = workbook.getSheetAt(0); // Permite posicionarse si o si en la primera hoja del excel
            sheet = workbook.getSheet(sheetName); // Permite posicionarse en la hoja con base al nombre (En este caso Hoja1)
            fos = new FileOutputStream(excelFilePath);

        } catch (Exception fail){
            System.out.println(fail.getMessage());
        }
    }

}
