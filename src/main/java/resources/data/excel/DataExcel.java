package resources.data.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.table.DefaultTableModel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

public class DataExcel {

    private DefaultTableModel dataTable; // Define y crea una tabla en Java con filas y columnas
    FileInputStream excelFile;
    Workbook workbook; // Metodos de Excel para la ubicación entre filas y columnas
    Sheet sheet; // Llamado al nombre de la hoja con la que se va a interactuar (Excel)
    FileOutputStream fos; // Manejo de escritura en Columnas

    String sheetName = "Hoja1"; // Nombre de la hoja de Excel a usar
    public String excelFilePath = "D:/Información/Documentos/Automatización/Selenium Java/Guia 001/Selenium_Excel/src/main/java/resources/data/database/UserData.xlsx"; // Ruta donde se encuentra el archivo de Excel
    int numberFile = 0; //Número de fila a actualizar (Índice 0 - Primera posición después del encabezado A2, B2)

    // Abrir Conexión en el Excel

    public void excelConnection(boolean updateData){
        try {
            excelFile = new FileInputStream(excelFilePath);
            workbook = new XSSFWorkbook(excelFile);
            // Sheet sheet = workbook.getSheetAt(0); // Permite posicionarse si o si en la primera hoja del excel
            sheet = workbook.getSheet(sheetName); // Permite posicionarse en la hoja con base al nombre (En este caso Hoja1)

            if (updateData) { // Si es true ejecuta la función fos
                fos = new FileOutputStream(excelFilePath); // Se utiliza cuando se va a escribir o a actualizar un dato dentro del Excel
            }

        } catch (Exception fail){
            System.out.println(fail.getMessage());
        }
    }

    // Cerrar Conexión en el Excel

    public void excelCloseConnection(){
        try {
            workbook.close();
            excelFile.close(); // Si no se cierra el Excel ya no permite abrirlo porque no se cerró en el proceso
        } catch (Exception fail) {
            System.out.println(fail.getMessage());
        }
    }

    // Obtener información del Excel

    public DefaultTableModel tableExcel() {

        dataTable = new DefaultTableModel(); // Inicializamos un dataTable y retornamos la información para que se pueda ser utilizado

        Iterator <Row> rowIterator = sheet.iterator(); // Permite iterar por cada fila
        Row headerRow = rowIterator.next(); // Realiza una consulta por cada fila
        Iterator <Cell> headerCellIterator = headerRow.cellIterator(); // Busca y recorre la fila perteneciente a la cabecera (Títulos)

        while (headerCellIterator.hasNext()) { // Recorre cada una de las columnas del Excel que cuentan con texto
            Cell cell = headerCellIterator.next(); // Obtiene el valor de la celda
            dataTable.addColumn(cell.getStringCellValue()); // Retorna el texto de la columna y añade ese texto a la columna dataTable de Java
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next(); // Permite iterar cada fila sin importar que sea el encabezado
            Object[] rowData = new Object[dataTable.getColumnCount()]; // El objeto almacena las posiciones con base a la cantidad de Columnas que tenga el Excel
            Iterator <Cell> cellIterator = row.cellIterator();
            int columnIndex = 0; // Permite hacer seguimiento de la columna que esta trabajando

            while (cellIterator.hasNext()){ // Permite pasar por cada una de las columnas
                Cell cell = cellIterator.next();
                cell.setCellType(CellType.STRING); // Obtiene el texto que tiene la columna y cambia el tipo de celda a un String
                rowData[columnIndex] = cell.getStringCellValue();
                columnIndex++; // Incrementa para poder acceder a cada Columna
            }

            dataTable.addRow(rowData); // Almacena la información del ciclo condicional en el dataTable

        }

        return dataTable;
    }

    // Buscar por columna de Excel (Nombre)

    public Object obtainColumnValue(int rowIndex, String columnName) { // El metodo basado en la posición de la fila y el nombre retorna el atributo en especifico
        int columnIndex = dataTable.findColumn(columnName); // Obtiene el valor numerico de la columna con base al valor que le enviemos

        if (columnIndex == -1){
            System.out.println("Column not found: " + columnName); // Si no lo encuentra muestra el error correspondiente
            return null;
        }

        return dataTable.getValueAt(rowIndex, columnIndex); // Si existe esa fila y esa columna retorna el valor del dataTable (Los objetos pueden almacenar cualquier tipo de dato)

    }

    // Navegar por todas las filas y columnas del Excel para llenar la información

    public int obtainFiles() {
        excelConnection(false);
        int countofFiles = 0; // Cantidad de filas con las que va a iniciar el Excel
        for (Row row : sheet) { // Iterar a través de las filas en la hoja
            if (row != null) { // Iterar a través de las celdas en la fila
                boolean rowhasData = false;
                for (Cell cell : row){
                    if (cell != null && cell.getCellType() != CellType.BLANK){ // En caso de que la celda se encuentré vacía
                        rowhasData = true;
                        break;
                    }
                }
                if (rowhasData){ // Si la fila tiene al menos una celda con datos, incrementa el contador
                    countofFiles++;
                }
            }
        }
        countofFiles--; // Se usa esto para que no cuente la posición de los encabezados
        excelCloseConnection();
        return countofFiles;
    }
}
