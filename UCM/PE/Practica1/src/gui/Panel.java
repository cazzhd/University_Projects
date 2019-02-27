package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.math.plot.*;

import model.BinaryChromosome;
import model.Chromosome;
import model.Function;
import model.Function1;
import model.Function2;
import model.Function3;
import model.Function4;
import model.FunctionType;
import model.GeneticAlgorithm;

import javax.swing.*;

public class Panel extends JFrame {

	private static final long serialVersionUID = 2569879142816556337L;
	
	Plot2DPanel plot;

	private JTextField size_population;
	private JTextField num_generations;
	private JTextField crossover_perc;
	private JTextField mutation_perc;
	private JTextField prec;
	private String[] function_sel_ops = { "Funci�n 1", "Funci�n 2", "Funci�n 3", "Funci�n 4" };
	private JComboBox<String> function_sel;
	private JButton start;
	private JButton restart;
	private JCheckBox elitism;
	private Dimension size;
	private GeneticAlgorithm<BinaryChromosome> ga;

	public Panel() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		setTitle("Pr�ctica 1");
		this.setMinimumSize(new Dimension(1200, 700));

		this.plot = new Plot2DPanel();
		plot.addLegend("SOUTH");
		add(plot, BorderLayout.CENTER);

		// Components
		this.size_population = new JTextField("100", 12);
		this.num_generations = new JTextField("100", 12);
		this.crossover_perc = new JTextField("0.6", 12);
		this.mutation_perc = new JTextField("0.05", 12);
		this.prec = new JTextField("0.0001", 12);
		this.function_sel = new JComboBox<>(function_sel_ops);
		this.elitism = new JCheckBox("Elitismo");
		start = new JButton("Iniciar");
		restart = new JButton("Restablecer");
		JCheckBox elitism = new JCheckBox("Elitismo");
		this.size = new Dimension(200, 20);

		function_sel.setPreferredSize(new Dimension(200, 25));

		function_sel.setMaximumSize(function_sel.getPreferredSize());

		size_population.setPreferredSize(size);
		size_population.setMaximumSize(size_population.getPreferredSize());

		num_generations.setPreferredSize(size);
		num_generations.setMaximumSize(size_population.getPreferredSize());

		crossover_perc.setPreferredSize(size);
		crossover_perc.setMaximumSize(size_population.getPreferredSize());

		mutation_perc.setPreferredSize(size);
		mutation_perc.setMaximumSize(size_population.getPreferredSize());

		prec.setPreferredSize(size);
		prec.setMaximumSize(size_population.getPreferredSize());

		JPanel barraizq = new JPanel();

		barraizq.setLayout(new GridLayout(20, 2, 10, 10));

		barraizq.add(new JLabel("Tama�o poblaci�n:"));
		barraizq.add(size_population);
		barraizq.add(new JLabel("N�mero generaciones:"));
		barraizq.add(num_generations);
		barraizq.add(new JLabel("Porcentaje de cruces:"));
		barraizq.add(crossover_perc);
		barraizq.add(new JLabel("Porcentaje de mutaci�n:"));
		barraizq.add(mutation_perc);
		barraizq.add(new JLabel("Precisi�n:"));
		barraizq.add(prec);
		barraizq.add(elitism);
		barraizq.add(function_sel);

		barraizq.add(start);
		barraizq.add(restart);

		barraizq.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 30));
		add(barraizq, BorderLayout.LINE_START);
		add(new JLabel("Realizado por Lukas Haring y Ra�l Torrijos", SwingConstants.RIGHT), BorderLayout.PAGE_END);
		setVisible(true);
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * x = new double[Integer.parseInt(num_generations.getText())];
				 * y = new double[Integer.parseInt(num_generations.getText())];
				 */
				Function f = new Function1(null);
				String function_name = (String) function_sel.getSelectedItem();
				System.out.println(function_name);
				switch (function_name) {
					case "Funci�n 1":
						f = new Function1(FunctionType.MAXIMIZE);
					break;
					case "Funci�n 2":
						f = new Function2(FunctionType.MINIMIZE);
					break;
					case "Funci�n 3":
						f = new Function3(FunctionType.MINIMIZE);
					break;
					case "Funci�n 4":
						f = new Function4(1, FunctionType.MINIMIZE);
					break;
				}
				
				int num_gen = Integer.parseInt(num_generations.getText());
				ga = new GeneticAlgorithm<BinaryChromosome>(BinaryChromosome.class,
						Integer.parseInt(size_population.getText()), num_gen,
						Double.parseDouble(crossover_perc.getText()), Double.parseDouble(mutation_perc.getText()),
						Double.parseDouble(prec.getText()), f);
				
				List<double[]> best_chromosomes = ga.run();
				
				double[] generations = new double[num_gen];
				for(int i = 0; i < num_gen; ++i) generations[i] = i;
				plot.addLinePlot("Mejor absoluto"        , generations, best_chromosomes.get(0));
				plot.addLinePlot("Mejor de la generaci�n", generations, best_chromosomes.get(1));
				plot.addLinePlot("Media de la generaci�n", generations, best_chromosomes.get(2));
				
				/*for(Chromosome chr : best_chromosomes) {
					System.out.println(Arrays.toString(chr.getFenotypes()));
				}*/

			}
		});

		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plot = new Plot2DPanel();

			}
		});
	}

	public void plotUpdate() {
		plot.update(getGraphics());
	}
}
