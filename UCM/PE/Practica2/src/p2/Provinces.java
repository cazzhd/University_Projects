package p2;

import model.Pair;

public enum Provinces {
	
	GRANADA("Granada", new Pair(470, 520)),
	ALMERIA("Almer�a", new Pair(540, 530)),
	JAEN("Ja�n", new Pair(465, 470)),
	CORDOBA("Cordoba", new Pair(390, 460)),
	SEVILLA("Sevilla", new Pair(330, 504)),
	MALAGA("Malaga", new Pair(405, 550)),
	HUELVA("Huelva", new Pair(250, 500)),
	CADIZ("Cadiz", new Pair(310, 560)),
	
	MURCIA("Murcia", new Pair(610, 450)),
	
	CACERES("Caceres", new Pair(305, 320)),
	BADAJOZ("Badajoz", new Pair(260, 395)),
	
	CIUDAD_REAL("Ciudad Real", new Pair(450, 390)),
	ALBACETE("Albacete", new Pair(555, 390)),
	TOLEDO("Toledo", new Pair(430, 310)),
	CUENCA("Cuenca", new Pair(535, 300)),
	GUADALAJARA("Guadalajara", new Pair(490, 250)),
	
	ALICANTE("Alicante", new Pair(645, 420)),
	VALENCIA("Valencia", new Pair(650, 330)),
	CASTELLON("Castell�n", new Pair(665, 290)),
	
	MADRID("Madrid", new Pair(445, 260)),
	;
	
	private final String name;
	private final Pair<Integer, Integer> coord;
	
	private Provinces(String n, Pair p) {
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
		ALBACETE, ALICANTE, ALMERIA, BADAJOZ, CACERES, CADIZ, CASTELLON, CIUDAD_REAL, CORDOBA, CUENCA, GRANADA, GUADALAJARA, HUELVA, JAEN, MADRID, MALAGA
	};
}
