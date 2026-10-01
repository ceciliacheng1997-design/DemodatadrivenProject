package rough;

import java.util.HashMap;

import utilities.Constant;
import utilities.ExcelReader;

public class Readexceldata {
	public static void main(String[] args) {
		ExcelReader excelReader =new ExcelReader("./src/test/java/utilities/Excelmanager.xlsx");
		int totalrowcount=excelReader.getRowCount(Constant.EXCEL_SHEET);
		System.out.println("Row count: " + totalrowcount);
		String targetDatasource="AddManagerTest";
		int targetDatastartRow=0;
		int targetDataendRow=0;
		for (int i=1; i<=totalrowcount; i++) {
			if (excelReader.getCellData(Constant.EXCEL_SHEET, 0, i).equals(targetDatasource)) {
				targetDatastartRow=i+2;
				break;
			}
		}
		System.out.println("Target data start row: " + targetDatastartRow);
		for (int j=targetDatastartRow; j<=totalrowcount+1; j++) {
			if (excelReader.getCellData(Constant.EXCEL_SHEET, 0, j).equals("")) {
				targetDataendRow=j-1;
				break;
		}
		}
		//System.out.println("Target data end row: " + targetDataendRow);
		int targetDatatotalRow=targetDataendRow-targetDatastartRow+1;
		//System.out.println("Target data totalrow " + targetDatatotalRow);
		
		int totalColcount=0;
		while(!excelReader.getCellData(Constant.EXCEL_SHEET, totalColcount, targetDatastartRow-1).equals("")) {
			totalColcount++;
			
		}
		//System.out.println("Target data totalcol " + totalColcount);
		Object[][] data=new Object[targetDatatotalRow][1];
		for (int i=targetDatastartRow;i<=targetDataendRow;i++) {
			HashMap<String,String> map=new HashMap<String,String>();
			for (int j=0;j<totalColcount;j++) {
				String key=excelReader.getCellData(Constant.EXCEL_SHEET,j,targetDatastartRow-1);
				String value=excelReader.getCellData(Constant.EXCEL_SHEET,j,i);
				map.put(key, value);	
			}
			data[i-targetDatastartRow][0]=map;
		}
		System.out.println(data[0]);
		/*
		Object[][]data=new Object[targetDatatotalRow][totalColcount];
		
		for (int i=targetDatastartRow; i<=targetDataendRow; i++) {
			for (int j=0; j<totalColcount; j++) {
				data[i-targetDatastartRow][j]=excelReader.getCellData(Constant.EXCEL_SHEET, j, i);

			}
		}
		*/


		

		
	}	
}
