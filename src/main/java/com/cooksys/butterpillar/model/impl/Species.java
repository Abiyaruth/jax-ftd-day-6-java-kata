package com.cooksys.butterpillar.model.impl;
import com.cooksys.butterpillar.model.IButterpillar;
import com.cooksys.butterpillar.model.ICatterfly;
import com.cooksys.butterpillar.model.IGrowthModel;
import com.cooksys.butterpillar.model.ISpecies;

public class Species implements ISpecies{

	private static final Object[] ICatterfly = null;
	private IGrowthModel growthModel;
	private String name;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name=name;
	}

	@Override
	public IGrowthModel getGrowthModel() {
		// TODO Auto-generated method stub
		return growthModel;
	}

	@Override
	public void setGrowthModel(IGrowthModel growthModel) {
		// TODO Auto-generated method stub
		this.growthModel = growthModel;
		
	}

	@Override
	public ICatterfly createCatterfly(double wingspan, double weight) {
		return new Catterfly(wingspan,weight);
		
	}
	@Override
	public IButterpillar createButterpillar(double length, double leavesEaten) {
		return new Butterpillar(length,leavesEaten);
	}

	@Override
	public ICatterfly[] convert(IButterpillar[] butterpillars) {
		int n =butterpillars.length;
		ICatterfly[] catter=new Catterfly[n];
		//double wing, weight;
		for(int i = 0;i<n;i++){
			//wing=growthModel.getLengthToWingspan()*butterpillars[i].getLength() ;//butterpillar.length*Growthstats.lengthToWingspan
			//weight=growthModel.getLeavesEatenToWeight()*butterpillars[i].getLeavesEaten();//butterpillar.leavesEaten*Growthstats.leavesEatenToWeight
			catter[i] = growthModel.butterpillarToCatterfly(butterpillars[i]);
		}
		return  catter;
	}

	@Override
	public IButterpillar[] convert(ICatterfly[] catterflies) {
		int n=catterflies.length;
		IButterpillar[] butter=new Butterpillar[n];
		//double length, leavesEaten;
		for(int i=0;i<n;i++)
		{
		 //length=catterflies[i].getWingspan()/growthModel.getLengthToWingspan();
		// leavesEaten=catterflies[i].getWeight()/growthModel.getLeavesEatenToWeight();
			butter[i] = growthModel.catterflyToButterpillar(catterflies[i]);
		}
		return butter;
	}

	public static Object[] getIcatterfly() {
		return ICatterfly;
	}

}


