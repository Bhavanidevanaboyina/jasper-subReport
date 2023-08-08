package com.mss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {

	private String foodName;
	private String mealTime;
	private int fat;
	private int carbohydrate;
	private int calcium;
}
