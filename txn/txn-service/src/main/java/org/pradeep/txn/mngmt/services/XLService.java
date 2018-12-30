package org.pradeep.txn.mngmt.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.pradeep.platform.beans.ExcelInput;
import org.pradeep.platform.beans.ExpenseInput;
import org.pradeep.platform.enums.AccountCategory;
import org.pradeep.platform.enums.ExpenseCategory;
import org.pradeep.platform.utils.CoreHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author psingarakannan on 19/12/18
 **/
@Service
public class XLService {

    private Map<Integer, String> columnHeaderMap = new HashMap <Integer, String> (  ){{
        put(0, "ID");
        put(1, "SPENT_DATE");
        put(2, "DESCRIPTION");
        put(3, "ACCOUNT_CATEGORY");
        put(4, "EXPENSE_CATEGORY");
        put(5, "SUB_TYPE");
        put(6, "AMOUNT");
    }};

    public List<ExpenseInput> mapExcelToExpenseInput(ExcelInput excelInput) throws Exception{

        System.out.println (CoreHelper.deepSerialize ( excelInput ) );
        if(StringUtils.hasText ( excelInput.getExcelPath () )){
            excelInput.setExcelPath (excelInput.getExcelPath ());
        }
        else{
            excelInput.setExcelPath ( "/home/ezetap/Desktop/account_management.xlsx" );
        }
        try (FileInputStream file = new FileInputStream ( new File ( excelInput.getExcelPath ( ) ) )){

            XSSFWorkbook workbook = new XSSFWorkbook ( file );
            XSSFSheet sheet ;
            String sheetName;
            if( StringUtils.hasText ( excelInput.getMonthShortText () ) && StringUtils.hasText ( excelInput.getYear () )){
                sheetName = excelInput.getMonthShortText ()+ "_" + new DateTime ( ).year ( ).getAsShortText ( );
                sheet = workbook.getSheet ( sheetName );
            }
            else {
                sheetName = new DateTime ( ).monthOfYear ( ).getAsShortText ( ).toLowerCase ( ) + "_" + new DateTime ( ).year ( ).getAsShortText ( );
                sheet = workbook.getSheet ( sheetName );
            }
            if(sheet == null){
                throw new FileNotFoundException ( "Could not find the sheet with name "+sheetName );
            }
            Iterator <Row> rowIterator = sheet.iterator ( );

            Stream <Row> targetStream = StreamSupport.stream (
                    Spliterators.spliteratorUnknownSize ( rowIterator, Spliterator.ORDERED ),
                    false );

            return

                    targetStream
                            .filter ( r -> r.getRowNum () == 0 || r.getRowNum ()>excelInput.getLastAccessedRow () )
                            .map ( this::createExpenseInput )
                            .filter ( Optional::isPresent  )
                            .map ( Optional::get )
                            .filter ( expenseInput -> expenseInput.getAmount ()!=null && expenseInput.getAmount ()>0 )
                            .collect ( Collectors.toList ( ) );
        }
    }
    private Optional<ExpenseInput> createExpenseInput( Row row){
        System.out.println ("Accessing row "+row.getRowNum () );

        Iterator<Cell> cellIterator = row.iterator();

        Stream<Cell> targetStream = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(cellIterator, Spliterator.ORDERED),
                false);

        ExpenseInput expenseInput = new ExpenseInput ();
        expenseInput.setCellIndex ( row.getSheet ().getSheetName () +"::"+ row.getRowNum () );

        targetStream
                .forEach ( cell -> fetchData ( cell, expenseInput )  );

        return Optional.of(expenseInput);
    }

    private void fetchData(Cell cell, ExpenseInput expenseInput){
        if(cell.getRowIndex () == 0){
            if( columnHeaderMap.get ( cell.getColumnIndex () ).equalsIgnoreCase ( cell.getStringCellValue () ) ){
                return;
            }
            throw new UnknownFormatConversionException("Column heading mapping is incorrect "+cell.getColumnIndex ()+" rowindex "+cell.getRowIndex ());
        }

        String value = "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                value = String.valueOf ( cell.getNumericCellValue() );
                break;
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue ();
                break;

            default:
                System.out.println(cell.getRichStringCellValue () + " ");

        }

        switch (cell.getColumnIndex ()){
            case 1:
                try {
                    expenseInput.setSpentDate ( new SimpleDateFormat ( "yyyy-mm-dd" ).parse ( value ) );
                }
                catch (Exception r){
                    r.printStackTrace ();
                }
                break;
            case 2:
                expenseInput.setDescription ( value );
                break;
            case 3:
                expenseInput.setAccountCategory ( AccountCategory.valueOf ( value ) );
                break;
            case 4:
                expenseInput.setExpenseCategory ( ExpenseCategory.valueOf ( value ) );
                break;
            case 5:
                expenseInput.setSubType ( ExpenseCategory.SubType.valueOf ( value ) );
                break;
            case 6:
                expenseInput.setAmount ( Float.valueOf ( value ).longValue () );
                break;
        }
    }

    public static void main(String[] args) throws Exception{
        ExcelInput excelInput = new ExcelInput ();
        excelInput.setLastAccessedRow ( 0 );
        List<ExpenseInput> list = new XLService ().mapExcelToExpenseInput ( excelInput );

        System.out.println (list );
    }

}
