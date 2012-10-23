package com.mindbar.life.runner;

import com.mindbar.life.exception.PopulationIsDeadException;
import com.mindbar.life.gen.Generator;
import com.mindbar.life.model.Cell;
import com.mindbar.life.model.Configurations;
import com.mindbar.life.model.Population;

public class LifeRunner {
	public static void main(String[] args) throws InterruptedException {
		try {
			Generator generator = new Generator();
			Population p = new Population(generator.getRandomPopulation(30, 130));
			// ha-ha this is a virus
				p.getField()[3][7] = Cell.VIRUS;
				p.getField()[6][4] = Cell.VIRUS;
				p.getField()[12][65] = Cell.VIRUS;
				p.getField()[17][21] = Cell.VIRUS;
				p.getField()[28][106] = Cell.VIRUS;
				p.getField()[4][111] = Cell.VIRUS;
				p.getField()[26][45] = Cell.VIRUS;
				p.getField()[3][15] = Cell.VIRUS;
				p.getField()[16][66] = Cell.VIRUS;
				p.getField()[2][108] = Cell.VIRUS;
			//Population p = new Population(Configurations.getGlider());
			// print new population every 1 second
			final int TIME_STEP = 1000;
			long population = 0;
			while (true) {
				Thread.sleep(TIME_STEP);
				System.out.println("Population " + population++ + ":" +
						"[LIVE=" + p.getLiveCount() + ", VIRUS=" + p.getVirusCount() + "]");
				System.out.println(p.toString());
				p.move();
			}
		} catch (PopulationIsDeadException e) {
			System.err.println("Population is dead!");
		}
	}
}
