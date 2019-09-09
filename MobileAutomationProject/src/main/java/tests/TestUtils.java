package tests;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestUtils {
 private static XSSFSheet ExcelWSheet;
 private static XSSFWorkbook ExcelWBook;
 private static XSSFCell Cell;

 /**********************************************
	Function Name: getTableArray
	Description: Reading the input values from the Excel file
	**********************************************/	
 	public static Object[][] getTableArray(File file, String SheetName) throws Exception {
 		
	  String[][] tabArray = null;
	   try {		   
		 FileInputStream fileInptStrm = new FileInputStream(file);
		 ExcelWBook = new XSSFWorkbook(fileInptStrm);
		 ExcelWSheet = ExcelWBook.getSheet(SheetName);
		 int startRow = 1;
		 int startCol = 0;
		 int ci,cj;
		 int totalRows = ExcelWSheet.getPhysicalNumberOfRows();
		 int totalCols= ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
		 tabArray=new String[totalRows-1][totalCols];
		  ci=0;
			for (int i=startRow;i<totalRows;i++, ci++) {           	   
			  cj=0;
			   for (int j=startCol;j<=(totalCols-1);j++, cj++){
				 tabArray[ci][cj]= getCellData(i,j);
				}
			 }
			fileInptStrm.close();
		 }
		  catch (FileNotFoundException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		   }
		    catch (IOException e){
			  System.out.println("Could not read the Excel sheet");
			  e.printStackTrace();
       		 }
		      return(tabArray);
		}
 	 /**********************************************
 		Function Name: getCellData
 		Description: Reading the cell value from the Excel file
 		**********************************************/	
	public static String getCellData(int RowNum, int ColNum) throws Exception {
	 try {
	     Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		  if(Cell==null) {
			 System.out.println("Cell is null");
			}
			 CellType dataType = Cell.getCellType();
			 int columnIndex= Cell.getColumnIndex();
			 switch(dataType) {
			 case STRING: {
			   return Cell.getStringCellValue();
			  }
			 case NUMERIC: {
			   if(columnIndex == 3) {
				 double inum= (double)Cell.getNumericCellValue();
				 return Double.toString(inum);
				}
				 else {
				   int inum= (int)Cell.getNumericCellValue();
				   return Integer.toString(inum);
				  }
			   }
			  case FORMULA: {
				double inum= (double)Cell.getNumericCellValue();
				return Double.toString(inum); 
			   }
			  default: {
				System.out.println("No matching cell type");
				return "";
			   }
			}
		}
		  catch (Exception e) {
		   System.out.println(e.getMessage());
		   throw (e);
		   }
	}
}
