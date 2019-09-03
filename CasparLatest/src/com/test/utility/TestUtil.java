package com.test.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class TestUtil 
{
 private static XSSFSheet ExcelWSheet;
 private static XSSFWorkbook ExcelWBook;
 private static XSSFCell Cell;
 private static XSSFCell Cell1;
 private static XSSFRow Row;

   public static Object[][] getTableArray(File file, String SheetName) throws Exception 
	{   
 	 String[][] tabArray = null;
 	  try
	   { 		 
		FileInputStream fileInptStrm = new FileInputStream(file);
		// Access the required test data sheet
		ExcelWBook = new XSSFWorkbook(fileInptStrm);
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int startRow = 1;
		int startCol = 0;
		int ci,cj;
		int totalRows = ExcelWSheet.getLastRowNum();
	    int totalCols= ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
	    
	    tabArray=new String[totalRows][totalCols];
	    ci=0;
	     for (int i=startRow;i<=totalRows;i++, ci++) 
	      {           	   
	       cj=0;
	     	for (int j=startCol;j<=(totalCols-1);j++, cj++)
	     	 {
	     	 tabArray[ci][cj]= getCellData(i,j);
	     	 }
	      }
	       fileInptStrm.close();
		}
 	  catch (FileNotFoundException e)
 	   {
 	  	System.out.println("Could not read the Excel sheet");
        e.printStackTrace();
 	   }
 	  catch (IOException e)
 	   {
 	  	System.out.println("Could not read the Excel sheet");
 	  	e.printStackTrace();
 	   }
 	  	return(tabArray);
	}

   public static String getCellData(int RowNum, int ColNum) throws Exception 
    {
     try
     {
      Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
	   if(Cell==null)
		{
    	 System.out.println("Cell is null");
    	}
		CellType dataType = Cell.getCellType();

		switch(dataType)
		{
		 case STRING:
		  {
		   return Cell.getStringCellValue();
		  }
		 case NUMERIC:
		  {
		   int inum= (int)Cell.getNumericCellValue();
		   return Integer.toString(inum);
		  }
		 default:
		 return "";
		}
	 }
	  catch (Exception e)
 	   {
		System.out.println(e.getMessage());
		throw (e);
 	   }
    }

   public static void setCellData(File file, String SheetName, String patient_id, String temppsswrd) throws Exception
    {
	 try
	  {	   
		// Access the required test data sheet
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int totalRows = ExcelWSheet.getLastRowNum();
	        
	    Row=ExcelWSheet.createRow(totalRows+1);	
	    Cell= Row.createCell(0);
	    Cell.setCellValue(patient_id);
	    Cell1=Row.createCell(1);
	    Cell1.setCellValue(temppsswrd);
	    FileOutputStream fileOutptStrm = new FileOutputStream(file);
	    ExcelWBook.write(fileOutptStrm);
	    fileOutptStrm.flush();
	    fileOutptStrm.close();
	 }
	  catch(FileNotFoundException e)
	   {
	    System.out.println("Could not read the Excel sheet");
	    e.printStackTrace();
	   }
	  catch (IOException e)
	   {
	    System.out.println("Could not read the Excel sheet");
	    e.printStackTrace();	
	   }
    }
}
