package com.mss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nutrient {

	private String nutritionName;
	private int total;
	private int goal;
	private String metrics;
	
	
}
