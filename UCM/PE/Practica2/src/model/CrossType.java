package model;

import java.util.Arrays;
import java.util.Optional;

public enum CrossType {
	MONOPOINT("Monopunto"),
	MULTIPOINT("Multipunto"),
	UNIFORM("Uniforme"),
	PARTIALLY_MAPPED("Emparejamiento Parcial (PMX)"),
	ORDERED("Cruce por Orden (OX)"),
	ORDERED_VARIANT("Variante de OX"),
	CICLES("Ciclos (CX)"),
	RECOMBINATION("Recombinaci�n"),
	ORDINAL_CODIFICATION("Codificaci�n Ordinal"),
	SELF_METHOD_1("M�todo propio 1"),
	SELF_METHOD_2("M�todo propio 2");
	
	private final String name;
	
	private CrossType(String n) {
		this.name = n;
	};
	
	@Override
	public String toString() { return this.name; };
}
