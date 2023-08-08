package com.mss;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SpringBootApplication
public class JasperReportApplication {

	public static void main(String[] args) throws FileNotFoundException, JRException {
		SpringApplication.run(JasperReportApplication.class, args);
		File file = ResourceUtils.getFile("classpath:Nutrition.jrxml");
		Nutrient protein = new Nutrient("Protein", 80, 50, "g");
		Nutrient fiber = new Nutrient("Fiber", 100, 150, "g");
		Nutrient carbohydrates = new Nutrient("Carbohydrates", 180, 206, "g");
		Nutrient sugar = new Nutrient("Sugar", 68, 62, "g");
		Nutrient iron = new Nutrient("Iron", 80, 50, "%");
		Nutrient calcium = new Nutrient("Calcium", 80, 50, "mg");
		List<Nutrient> nutrientDataList = new ArrayList<Nutrient>();

		nutrientDataList.add(carbohydrates);
		nutrientDataList.add(protein);
		nutrientDataList.add(fiber);
		nutrientDataList.add(calcium);
		nutrientDataList.add(iron);
		nutrientDataList.add(sugar);
		JRBeanCollectionDataSource nutritionDataSource = new JRBeanCollectionDataSource(nutrientDataList);

		MacroNutrient carboMacroNutrient = new MacroNutrient("Carbohydrates", 35);
		MacroNutrient fatMacroNutrient = new MacroNutrient("Fat", 70);
		MacroNutrient calciumMacroNutrient = new MacroNutrient("Calcium", 25);

		List<MacroNutrient> macroNutrientDataList = new ArrayList<MacroNutrient>();
		macroNutrientDataList.add(carboMacroNutrient);
		macroNutrientDataList.add(fatMacroNutrient);
		macroNutrientDataList.add(calciumMacroNutrient);
		JRBeanCollectionDataSource macroNutrientDataSource = new JRBeanCollectionDataSource(macroNutrientDataList);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("firstName", "Pallavi");
		parameters.put("lastName", "Devanaboyina");
		parameters.put("dob", "03/06/1998");
		parameters.put("age", 25);
		parameters.put("nutritionDataSet", nutritionDataSource);
		parameters.put("macroNutrientDataSet", macroNutrientDataSource);
		parameters.put("foodReport", getFoodReport());
		parameters.put("foodParameter", getFoodParameter());
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		String path = "C:/Users/ddevanaboyana/Documents/";
		JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\nutrition.pdf");
		System.out.println("PDF report generated successfully");
	}
	
	private static JRBeanCollectionDataSource getFoodDataSource() {
		List<Food> foodDataList = new ArrayList<Food>();
        Food banana=new Food("Banana","Breakfast",0,28,1);
        Food milk=new Food("Milk","Breakfast",8,12,8);
        Food chicken=new Food("Chicken","Lunch",2,0,26);
        Food rice=new Food("Rice","Lunch",0,45,26);
        Food egg=new Food("Egg","Lunch",5,0,26);
        Food potatoes=new Food("Potatoes","Lunch",5,51,26);
        foodDataList.add(potatoes);
        foodDataList.add(egg);
        foodDataList.add(rice);
        foodDataList.add(chicken);
        foodDataList.add(milk);
        foodDataList.add(banana);
        JRBeanCollectionDataSource foodNutrientDataSource = new JRBeanCollectionDataSource(foodDataList);
		return foodNutrientDataSource;
		
	}
	
	private static Map getFoodParameter() {
		Map<String,Object> foodParameter= new HashMap<String, Object>();
		foodParameter.put("foodDataSet", getFoodDataSource());
		return foodParameter;
	}
	
	private static JasperReport getFoodReport() throws FileNotFoundException, JRException {
		File file = ResourceUtils.getFile("classpath:food_report.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		return jasperReport;
		
	}

}
