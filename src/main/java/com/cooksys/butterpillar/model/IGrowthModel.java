package com.cooksys.butterpillar.model;

public interface IGrowthModel {
	int lengthtoWingspan = 0;
	int leavesEatentoWeight = 0;

	ICatterfly butterpillarToCatterfly(IButterpillar butterpillar);

	IButterpillar catterflyToButterpillar(ICatterfly catterfly);

	double getLengthToWingspan();

	void setLengthToWingspan(double lengthToWingspan);

	double getLeavesEatenToWeight();

	void setLeavesEatenToWeight(double leavesEatenToWeight);
}
