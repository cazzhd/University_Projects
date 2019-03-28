package p2;

import model.Pair;

public enum Provinces {
	ALBACETE("Albacete", new Pair<Integer, Integer>(555, 390)),
	ALICANTE("Alicante", new Pair<Integer, Integer>(645, 420)),
	ALMERIA("Almer�a", new Pair<Integer, Integer>(540, 530)),
	AVILA("�vila", new Pair<Integer, Integer>(387, 250)),
	BADAJOZ("Badajoz", new Pair<Integer, Integer>(260, 395)),
	BARCELONA("Barcelona", new Pair<Integer, Integer>(780, 177)),
	BILBAO("Bilbao", new Pair<Integer, Integer>(488, 44)),
	BURGOS("Burgos", new Pair<Integer, Integer>(442, 120)),
	CACERES("Caceres", new Pair<Integer, Integer>(305, 320)),
	CADIZ("Cadiz", new Pair<Integer, Integer>(310, 560)),
	CASTELLON("Castell�n", new Pair<Integer, Integer>(665, 290)),
	CIUDAD_REAL("Ciudad Real", new Pair<Integer, Integer>(450, 390)),
	CORDOBA("Cordoba", new Pair<Integer, Integer>(390, 460)),
	A_CORU�A("A Coru�a", new Pair<Integer, Integer>(180, 38)),
	CUENCA("Cuenca", new Pair<Integer, Integer>(535, 300)),
	GERONA("Gerona", new Pair<Integer, Integer>(820, 120)),
	GRANADA("Granada", new Pair<Integer, Integer>(470, 520)),
	GUADALAJARA("Guadalajara", new Pair<Integer, Integer>(490, 250)),
	HUELVA("Huelva", new Pair<Integer, Integer>(250, 500)),
	HUESCA("Huesca", new Pair<Integer, Integer>(635, 130)),
	JAEN("Ja�n", new Pair<Integer, Integer>(465, 470)),
	LEON("Le�n", new Pair<Integer, Integer>(325, 100)),
	LERIDA("L�rida", new Pair<Integer, Integer>(695, 165)),
	LOGRO�O("Logro�o", new Pair<Integer, Integer>(505, 110)),
	LUGO("Lugo", new Pair<Integer, Integer>(225, 65)),
	MADRID("Madrid", new Pair<Integer, Integer>(445, 260)),
	MALAGA("Malaga", new Pair<Integer, Integer>(405, 550)),
	
	
	
	MURCIA("Murcia", new Pair<Integer, Integer>(610, 450)),
	SEVILLA("Sevilla", new Pair<Integer, Integer>(330, 504)),
	TOLEDO("Toledo", new Pair<Integer, Integer>(430, 310)),
	VALENCIA("Valencia", new Pair<Integer, Integer>(650, 330)),

	;
	
	private final String name;
	private final Pair<Integer, Integer> coord;
	
	private Provinces(String n, Pair<Integer, Integer> p) {
		this.name = n;
		this.coord = p;
	};
	
	public static String[] CITIES_TEXT = {"Albacete", "Alicante", "Almer�a", "�vila", "Badajoz", "Barcelona", "Bilbao", "Burgos", "C�ceres", "C�diz", "Castell�n", "Ciudad Real", "C�rdoba", "A Coru�a", "Cuenca", "Gerona", "Granada", "Guadalajara", "Huelva", "Huesca", "Ja�n", "Le�n", "L�rida", "Logro�o", "Lugo", "Madrid", "M�laga"};

	@Override
	public String toString() {
		return this.name;
	};
	
	public Pair<Integer, Integer> getCoords(){
		return this.coord;
	};
	
	public static Provinces[] CITIES = {
		ALBACETE, ALICANTE, ALMERIA, AVILA, BADAJOZ, BARCELONA, BILBAO, BURGOS, CACERES, CADIZ, CASTELLON, CIUDAD_REAL, CORDOBA, A_CORU�A, CUENCA, GERONA, GRANADA, GUADALAJARA, HUELVA, HUESCA, JAEN, LEON, LERIDA, LOGRO�O, LUGO, MADRID, MALAGA
	};
}
