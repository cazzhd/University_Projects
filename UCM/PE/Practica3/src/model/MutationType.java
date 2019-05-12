package model;

public enum MutationType {
	RANDOM("Aleatorio"),
	UNIFORM("Uniforme"),
	NONUNIFORM("No Uniforme"),
	INSERTION("Inserci�n"),
	SWAP("Intercambio"),
	INVERSION("Inversi�n"),
	HEURISTIC("Heur�stica"),
	SELF_METHOD_1("M�todo Propio 1"),
	SIMPLE_TERMINAL("Terminal Simple"),
	SUBTREE("Sub�rbol"),
	PERMUTATION("Permutaci�n"),
	SIMPLE_FUNCTION("Funcional Simple");
	
	private final String name;
	
	private MutationType(String n) {
		this.name = n;
	};
	
	@Override
	public String toString() { return this.name; };
}
