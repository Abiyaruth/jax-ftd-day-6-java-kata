package com.cooksys.butterpillar.model;

public interface ISpecies {

	String getName();

	void setName(String name);
	IGrowthModel getGrowthModel();
	
	void setGrowthModel(IGrowthModel growthModel);
	
	ICatterfly createCatterfly(double wingspan, double weight);
	
	IButterpillar createButterpillar(double length, double leavesEaten);
	
	ICatterfly[] convert(IButterpillar[] butterpillars);
	
	IButterpillar[] convert(ICatterfly[] catterflies);

}
