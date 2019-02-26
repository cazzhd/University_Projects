package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;

public class GeneticAlgorithm<T> {

	private final int TOURNAMENT_SET = 3;
	private final double TOURNAMENT_PROB = 0.60;

	private ArrayList<Chromosome> chromosomes; // Population
	private Chromosome best_chromosome;

	private Class<T> class_type;

	// Function
	private Function function = null;

	// Child and parents
	private int initial_population = 0;
	private int total_generations = 0;
	private double gene_precision = 0.0;

	// Probabilities
	private double crossing_prob = 0.0;
	private double mutation_prob = 0.0;

	// Constructor
	public GeneticAlgorithm(Class<T> class_type, int population, int generations, double crossoverProbability,
			double mutationProbability, double precision, Function evaluationFunction) {
		this.class_type = class_type;
		this.initial_population = population;
		this.total_generations = generations;
		this.gene_precision = precision;

		this.crossing_prob = crossoverProbability;
		this.mutation_prob = mutationProbability;
		this.function = evaluationFunction;

		chromosomes = new ArrayList<>();

		try {
			this.createInitialPopulation();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			System.exit(0);
		}
	};

	// Iterate
	public List<double[]> run() {
		int currentGeneration = 0;
		List<double[]> report = new ArrayList<>();
		
		// 1s Generation
		double[] eval_result = this.evaluation(false);
		
		double[] generation_best_abs = new double[total_generations];
		double[] generation_mean = new double[total_generations];
		double[] generation_best = new double[total_generations];
		
		report.add(generation_best_abs);
		report.add(generation_best);
		report.add(generation_mean);
		
		generation_best[0] = function.evaluate(this.best_chromosome.getFenotypes());
		generation_best_abs[0] = generation_best[0];
		
		// Mean
		double mean = 0.0;
		for(Chromosome chr : chromosomes) {
			mean += function.evaluate(chr.getFenotypes());
		}
		generation_mean[0] = mean / initial_population;
		
		Chromosome best_absolute = best_chromosome;

		currentGeneration = 1;
		while (currentGeneration < total_generations) {
			this.selection(SelectionType.ROULETTE, eval_result);
			this.crossover(1);
			this.mutation();
			eval_result = this.evaluation(false);

			/*
			System.out.println("----------------------------");
			for(Chromosome chr : chromosomes) {
				System.out.println("Chromosome " +  i);
				System.out.println("Arguments:" + Arrays.toString(chr.getFenotypes()));
				System.out.println("Function Evaluation " + function.evaluate(chr.getFenotypes()));
				++i;
			}

			System.out.println("---------RESULT");
			System.out.println("Arguments " + Arrays.toString(this.best_chromosome.getFenotypes()));
			System.out.println("Function Evaluation " + function.evaluate(this.best_chromosome.getFenotypes()));
			*/
			
			if(function.compare(
			     function.evaluate(best_absolute.getFenotypes()), 
			     this.best_chromosome.getFenotypes()
			  )
			) {
				best_absolute = this.best_chromosome;
			}
			
			generation_best[currentGeneration] = function.evaluate(this.best_chromosome.getFenotypes());
			generation_best_abs[currentGeneration] = function.evaluate(best_absolute.getFenotypes());
			// Mean
			mean = 0.0;
			for(Chromosome chr : chromosomes) {
				mean += function.evaluate(chr.getFenotypes());
			}
			generation_mean[currentGeneration] = mean / initial_population;
			
			
			currentGeneration++;
		}
		
		System.out.println("------- BEST ABSOLUTE");
		System.out.println(Arrays.toString(best_absolute.getFenotypes()));
		System.out.println("BEST EVALUATION : " + function.evaluate(best_absolute.getFenotypes()));
		System.out.println("END");
		
		return report;
	};

	// Init population
	public void createInitialPopulation() throws Exception {
		// Creationf Binary Chromosomes
		if (class_type.getName().contains("BinaryChromosome")) {

			for (int i = 0; i < initial_population; ++i) {
				chromosomes.add(BinaryChromosome.newInstance(function, gene_precision));
			}

			// Creation of Real Chromosomes
		} else if (class_type.getName().contains("RealChromosome")) {
			for (int i = 0; i < initial_population; ++i) {
				// chromosomes.add((T) BinaryChromosome.newInstance(evaluation,
				// gene_precision));
			}
		}

	}

	// Evaluate Population
	private double[] evaluation(boolean elitism) {
		int size = chromosomes.size();
		double[] eval_results = new double[size];
		
		Chromosome first_chromosome = chromosomes.get(0);
		double maxmin_value = function.evaluate((first_chromosome).getFenotypes());
		double minmax_value = maxmin_value;
		best_chromosome = first_chromosome;
		eval_results[0] = maxmin_value;
		
		for (int i = 1; i < size; ++i) {
			Chromosome current = chromosomes.get(i);
			double[] fenotypes = (current).getFenotypes();
			eval_results[i] = function.evaluate(fenotypes);
			if(function.compare(minmax_value, fenotypes)) {
				minmax_value = eval_results[i];
				best_chromosome = current;
			} else {
				if(function.compare(maxmin_value, fenotypes)) {
					maxmin_value = eval_results[i];
				}
			}
		}
		
		final double value = (function.maxmin == FunctionType.MAXIMIZE ? -1 : 1) * maxmin_value;
		
		DoubleStream map2 = Arrays.stream(eval_results).map((e) -> value - e);
		double total_sum = map2.sum();
		DoubleStream map3 = Arrays.stream(eval_results).map((e) -> (value - e) / total_sum);
		return map3.toArray();
	};

	private void selection(SelectionType type, double[] evaluations) {
		ArrayList<Chromosome> generation = new ArrayList<>();
		switch (type) {
		case ROULETTE:

			for (int i = 1; i < evaluations.length; ++i) {
				evaluations[i] += evaluations[i - 1];
			}
			for (int i = 0; i < initial_population; ++i) {
				// Lanzamos y buscamos el siguiente elemento el cual supere el
				// valor de la ruleta
				double roulette = Math.random();
				int throul = 0;
				while (evaluations[throul++] < roulette);

				// Nos quedamos con el anterior.
				generation.add(chromosomes.get(--throul).clone());
			}

			break;
		case DETE_TOURNAMENT:
		case PRB_TOURNAMENT:

			for (int i = 0; i < initial_population; ++i) {
				// Select random chromosome (Suppose it is the best).
				int first = (int) (Math.random() * initial_population);
				Chromosome best_cr = chromosomes.get(first);
				Chromosome worst_cr = best_cr;
				double best = function.evaluate(best_cr.getFenotypes());
				double worst = best;

				// Generate n more and compare.
				for (int j = 0; j < TOURNAMENT_SET; ++j) {
					int random = (int) (Math.random() * initial_population);
					Chromosome chromosme = chromosomes.get(random);
					// Check if better than the best.
					if (function.compare(best, chromosme.getFenotypes())) {
						best = function.evaluate(chromosme.getFenotypes());
						best_cr = chromosme;
					} else {
						// Check if it is worse than the worst.
						if (function.compare(worst, chromosme.getFenotypes())) {
							best = function.evaluate(chromosme.getFenotypes());
							worst_cr = chromosme;
						}
					}
				}

				if (type == SelectionType.DETE_TOURNAMENT) {
					generation.add(best_cr);
				} else {
					if (Math.random() <= TOURNAMENT_PROB) {
						generation.add(best_cr);
					} else {
						generation.add(worst_cr);
					}
				}
			}

			this.chromosomes = generation;
			break;
		}
		this.chromosomes = generation;
	}

	private void crossover(int n) {

		ArrayList<Integer> quieren_cruzarse = new ArrayList<>();
		for (int i = 0; i < initial_population; ++i) {
			if (Math.random() <= this.crossing_prob) {
				quieren_cruzarse.add(i);
			}
		}

		// Convertir en par
		int size = quieren_cruzarse.size() & ~0x1;

		for (int i = 0; i < size; i += 2) {
			Chromosome chr1 = chromosomes.get(quieren_cruzarse.get(i + 0));
			Chromosome chr2 = chromosomes.get(quieren_cruzarse.get(i + 1));

			chr1.cross(chr2, n); // Symmetric
		}

	};

	private void mutation() {
		for (Chromosome chromosome : chromosomes) {
			chromosome.randomMutation(mutation_prob);
		}
	};

}
