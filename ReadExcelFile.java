/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stiw3054_assignment.quiz1xcel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author abdullah
 */
public class ReadExcelFile {
    private String file_path;
    StudentList studentList;
    FileInputStream fis = null;
    Workbook workbook;
    int numberOfSheets;
    Sheet sheet;
    DataFormatter dataFormatter;

    public ReadExcelFile(String file_path) throws IOException {
       this.file_path= file_path; 
       studentList = new StudentList();
       fis = new FileInputStream(file_path);
       workbook = new XSSFWorkbook(fis);
       dataFormatter = new DataFormatter();
    }
    public ReadExcelFile() throws IOException {
        file_path = "/Users/abdullah/Downloads/example.xlsx";
        studentList = new StudentList();
        fis = new FileInputStream("/Users/abdullah/Downloads/example.xlsx");
        workbook = new XSSFWorkbook(fis);
        dataFormatter = new DataFormatter();
    }
    public void setNumOfSheet(){
      numberOfSheets  = workbook.getNumberOfSheets();
     }
    public void setSheet(int i){
       
       sheet = workbook.getSheetAt(i);
     }
    
    public StudentList createList() throws IOException{
                    
                    setNumOfSheet();
                    
            for (int i = 0; i < numberOfSheets; i++) {
                 setSheet(i);
                 Student student;
                 Row row;
                 Iterator cellIterator;
                 String sv ="";
                 //long value;
                 String cellValue;
                 //DataFormatter dataFormatter = new DataFormatter();
                 Iterator rowIterator = sheet.iterator();
                
                while (rowIterator.hasNext()) {
                student = new Student();
                row = (Row) rowIterator.next();
                cellIterator = row.cellIterator();
                      while (cellIterator.hasNext()) {
                            Cell cell = (Cell)cellIterator.next();
                            cellValue = dataFormatter.formatCellValue(cell);
                            if ((cellValue != null)&&!(cellValue.equals(""))){
                    switch (cell.getColumnIndex()) {
                        case 0:student.setNum(cellValue);
                               break;
                        case 1:student.setMatric(cellValue);
                               break;
                        case 2:student.setName(cellValue);
                               break;
                        case 3: sv = cellValue;
                               student.setSv(sv);
                               break;
//                        default:student.setSv(cellValue);
//                            break;
                    }
                    
                            }
                       if(cell.getColumnIndex()==3)
                          student.setSv(sv); 
                        

                    }
                    //end iterating a row, add all the elements of a row in list
                    if (student.getMatric()!=null){
                    studentList.setStudent(student);}
                }
            fis.close();
        } 
        
return studentList;
}
}