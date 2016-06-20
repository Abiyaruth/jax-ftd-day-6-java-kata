package com.cooksys.butterpillar.test.model;

import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.reflections.Reflections;

import com.cooksys.butterpillar.model.IButterpillar;
import com.cooksys.butterpillar.model.ICatterfly;
import com.cooksys.butterpillar.model.IGrowthModel;
import com.cooksys.butterpillar.model.ISpecies;
import com.cooksys.butterpillar.model.impl.Butterpillar;
import com.cooksys.butterpillar.model.impl.Catterfly;
import com.cooksys.butterpillar.model.impl.GrowthModel;

public class SpeciesTest {

	private static Reflections reflections;
	private static Set<Class<? extends ISpecies>> implementations;
	private static Class<? extends ISpecies> implementation;

	private static ISpecies species;
	private static IGrowthModel model;
	private static IButterpillar[] butterpillars;
	private static ICatterfly[] catterflies;

	@BeforeClass
	public static void beforeClass() {
		SpeciesTest.reflections = new Reflections("com.cooksys");
		SpeciesTest.implementations = reflections.getSubTypesOf(ISpecies.class);
		for (Class<? extends ISpecies> implementation : SpeciesTest.implementations) {
			SpeciesTest.implementation = implementation;
		}
	}

	@AfterClass
	public static void afterClass() {
		SpeciesTest.reflections = null;
		SpeciesTest.implementations.clear();
		SpeciesTest.implementations = null;
		SpeciesTest.implementation = null;
	}

	@Before
	public void before() throws InstantiationException, IllegalAccessException {

		model = new GrowthModel();
		model.setLengthToWingspan(1.2);
		model.setLeavesEatenToWeight(0.25);

		this.species = SpeciesTest.implementation.newInstance();
		this.species.setName("Test Species");
		this.species.setGrowthModel(this.model);

		butterpillars = new Butterpillar[5];
		butterpillars[0] = species.createButterpillar(0.5,20);
		butterpillars[1] = species.createButterpillar(0.6,17);
		butterpillars[2] = species.createButterpillar(0.5,21);
		butterpillars[3] = species.createButterpillar(0.4,20);
	    butterpillars[4] = species.createButterpillar(0.7,18);

		catterflies = new Catterfly[5];
		catterflies[0] =species.createCatterfly(0.6,5);
		catterflies[1] = species.createCatterfly(0.72,4.25);
		catterflies[2] = species.createCatterfly(0.6,5.25);
		catterflies[3] = species.createCatterfly(0.48,5.0);
		catterflies[4] = species.createCatterfly(0.84,4.5);
	}

	@After
	public void after() {
		this.model = null;
		this.butterpillars = null;
		this.catterflies = null;
	}

	@Test
	public void testSingleImplementation() {
		Assert.assertEquals("There should be a single implementation of com.cooksys.butterpillar.model.ISpecies", 1,
				SpeciesTest.implementations.size());
	}

	@Test
	public void testGetName() {
		Assert.assertEquals("Test Species", this.species.getName());
	}

	@Test
	public void testGetGrowthModel() {
		Assert.assertEquals(SpeciesTest.model, SpeciesTest.species.getGrowthModel());
	}

	@Test
	public void testConvertIButterpillarArray() {
		Assert.assertArrayEquals(SpeciesTest.catterflies, SpeciesTest.species.convert(SpeciesTest.butterpillars));
	}

	@Test
	public void testConvertICatterflyArray() {
		Assert.assertArrayEquals(SpeciesTest.butterpillars, SpeciesTest.species.convert(SpeciesTest.catterflies));
	}

}
