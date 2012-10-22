package com.mindbar.life.runner;

import com.mindbar.life.exception.PopulationIsDeadException;
import com.mindbar.life.gen.Generator;
import com.mindbar.life.model.Configurations;
import com.mindbar.life.model.Population;

public class LifeRunner {
	public static void main(String[] args) throws InterruptedException {
		try {
			Generator generator = new Generator();
			Population p = new Population(generator.getRandomPopulation(480, 800));
			//Population p = new Population(Configurations.getGlider());
			// print new population every 1 second
			final int TIME_STEP = 1000;
			long population = 0;
			while (true) {
				//Thread.sleep(TIME_STEP);
				System.out.println("Population " + population++ + ": [" + p.getCount() + "]");
				//System.out.println(p.toString());
				p.move();
			}
		} catch (PopulationIsDeadException e) {
			System.err.println("Population is dead!");
		}
	}
}
