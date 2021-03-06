package model;

import java.util.*;

public abstract class Chromosome<T> {

	public ArrayList<T> genes;

	protected Function func;
	protected double prec = 0.0;

	public Chromosome(Function f, double p) {
		prec = p;
		func = f;
		genes = new ArrayList<T>(f.getTotalArguments());
	};

	public double[] getFenotypes() {
		return null;
	};

	public void mutate(MutationType mut, double prob) {};

	@Override
	public String toString() {
		String result = "\t\n Chromosome: \n";
		for (T gene : genes) {
			result += gene + ", \n";
		}
		result += "Evaluation: " + func.evaluate(this) + "\n";
		return result;
	}

	protected void cross(Chromosome<T> chr1, CrossType t) {
	};

	public Chromosome<T> clone() {
		return null;
	};
}
