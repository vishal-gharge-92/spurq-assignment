package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;



public class Simple {

	 WebDriver driver;
	@BeforeMethod()
	public void TestMethod() {
		
		System.setProperty("webdriver.chrome.driver","F:\\driver\\ab\\chromedriver.exe");
	    driver = new ChromeDriver(); 
		driver.get("https://calculator.net");
		driver.manage().window().maximize();
	}
	
	@DataProvider(name="DataContainer")
	public Object[][] read(){
		
		ArrayList<ArrayList<Object>> entries = new ArrayList();

		try {
			FileInputStream fis=new FileInputStream(new File("C:\\Users\\RSC\\Desktop\\test.xlsx"));
			XSSFWorkbook wb=new XSSFWorkbook(fis);
			XSSFSheet sheet=wb.getSheetAt(0);
			Iterator<Row> itr=sheet.iterator();
			
			itr.next();
			int jRow =0;
			while(itr.hasNext())
			{
				Row row=itr.next();
				
				Iterator<Cell> cellItr=row.cellIterator();
				System.out.println("");
				
				int icol =0;
				
				ArrayList<Object> temp = new ArrayList();
				while(cellItr.hasNext()){
					
					Cell cell = cellItr.next();
					
					switch (cell.getCellType())               
					{  
					case STRING: 
						temp.add(cell.getStringCellValue());
					break;  
					case NUMERIC:
						temp.add(cell.getNumericCellValue());					
					break;
					case FORMULA:
						temp.add(cell.getNumericCellValue());
					break;  
					default:  
					}  
					icol++;
				}
				entries.add(temp);
				icol=0;
				jRow++;
				
			}
			wb.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Object[][] array = new Object[entries.size()][];
		for (int i = 0; i < entries.size(); i++) {
		    ArrayList<Object> row = entries.get(i);
		    array[i] = row.toArray(new Object[row.size()]);
		}
		return array;
		
	}
	@Test(dataProvider="DataContainer")
    public void operate(double n1, double n2 , String op, double result){
		String one ="/html/body/div[3]/div/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/div/div[3]/span[1]";
		String two="/html/body/div[3]/div/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/div/div[3]/span[2]";
		String three="/html/body/div[3]/div/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/div/div[3]/span[3]";
		String four="/html/body/div[3]/div/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/div/div[2]/span[1]";
		String five="/html/body/div[3]/div/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/div/div[2]/span[2]";
		String six="/html/body/div[3]/div/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/div/div[2]/span[3]";
		String seven="/html/body/div[3]/div/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/div/div[1]/span[1]";
		String eight="/html/body/div[3]/div/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/div/div[1]/span[2]";
		String nine="/html/body/div[3]/div/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/div/div[1]/span[3]";
		String zero="/html/body/div[3]/div/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/div/div[4]/span[1]";
		String div="/html/body/div[3]/div/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/div/div[4]/span[4]";
		String mul="/html/body/div[3]/div/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/div/div[3]/span[4]";
		String sub="/html/body/div[3]/div/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/div/div[2]/span[4]";
		String add="/html/body/div[3]/div/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/div/div[1]/span[4]";
		String res="/html/body/div[3]/div/table/tbody/tr/td[1]/table/tbody/tr[1]/td/div/div[2]";
		String eq ="/html/body/div[3]/div/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/div/div[5]/span[4]";
		String clear="/html/body/div[3]/div/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/div/div[5]/span[3]";

		 int[] n1Digits = Integer.toString((int)n1).chars().map(c -> c-'0').toArray();
		 for(int d : n1Digits){
		 if(d ==0){
			 driver.findElement(By.xpath(zero)).click();
		 }
		 if(d ==1){
			 driver.findElement(By.xpath(one)).click();
		 }	
		if(d ==2){
			 driver.findElement(By.xpath(two)).click();	 
		}
		
		if(d ==3){
			 driver.findElement(By.xpath(three)).click();	 
		}
		if(d ==4){
			driver.findElement(By.xpath(four)).click();	
		}
		if(d ==5){
			driver.findElement(By.xpath(five)).click();	
		}
		if(d ==6){
			driver.findElement(By.xpath(six)).click();		 
		}
		if(d ==7){
			driver.findElement(By.xpath(seven)).click();
		}
		if(d ==8){
			driver.findElement(By.xpath(eight)).click();
		}
		if(d ==9){
			driver.findElement(By.xpath(nine)).click();
		}
		 }
		      
		 if(op.toLowerCase().equals("addition")){
			 driver.findElement(By.xpath(add)).click();
		 }else if(op.toLowerCase().equals("subtraction")){
			 driver.findElement(By.xpath(sub)).click();
		 }else if(op.toLowerCase().equals("multiplication")){
			 driver.findElement(By.xpath(mul)).click();
		 }else if(op.toLowerCase().equals("division")){
			 driver.findElement(By.xpath(div)).click();
		 }
		 int[] n2Digits = Integer.toString((int)n2).chars().map(c -> c-'0').toArray();
		 
		 for(int d : n2Digits){
			 if(d ==0){
				 driver.findElement(By.xpath(zero)).click();
			 }
			 if(d ==1){
				 driver.findElement(By.xpath(one)).click();
			 }	
			if(d ==2){
				 driver.findElement(By.xpath(two)).click();	 
			}
			
			if(d ==3){
				 driver.findElement(By.xpath(three)).click();	 
			}
			if(d ==4){
				driver.findElement(By.xpath(four)).click();	
			}
			if(d ==5){
				driver.findElement(By.xpath(five)).click();	
			}
			if(d ==6){
				driver.findElement(By.xpath(six)).click();		 
			}
			if(d ==7){
				driver.findElement(By.xpath(seven)).click();
			}
			if(d ==8){
				driver.findElement(By.xpath(eight)).click();
			}
			if(d ==9){
				driver.findElement(By.xpath(nine)).click();
			}
			 }
		 
		 double actual = Double.parseDouble(driver.findElement(By.xpath(res)).getText());
		 Assert.assertEquals(result, actual);
		 
		System.out.println(n1);
		
	}
}
