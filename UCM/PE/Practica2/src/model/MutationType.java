package model;

public enum MutationType {
	RANDOM("Aleatorio"),
	UNIFORM("Uniforme"),
	NONUNIFORM("No Uniforme"),
	INSERTION("Inserci�n"),
	SWAP("Intercambio"),
	INVERSION("Inversi�n"),
	HEURISTIC("Heur�stica"),
	SELF_METHOD_1("M�todo Propio 1");
	
	private final String name;
	
	private MutationType(String n) {
		this.name = n;
	};
	
	@Override
	public String toString() { return this.name; };
}
