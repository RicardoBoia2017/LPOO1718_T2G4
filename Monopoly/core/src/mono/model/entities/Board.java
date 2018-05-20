package mono.model.entities;

import java.util.ArrayList;

public class Board {
	
	ArrayList<Square> spaces = new ArrayList<Square>();

	public Board() {
		Square Square0 = new StartSquare();
		spaces.add(Square0);
		Square Square1 = new Property("Athens","BROWN", 60, 2, 10, 30, 90, 160, 250, 50, 1);
		spaces.add(Square1);
		Square Square2 = new CommunityChest(2);
		spaces.add(Square2);
		Square Square3 = new Property("Lisbon","BROWN", 60, 2, 10, 30, 90, 160, 250, 50, 3);
		spaces.add(Square3);
		Square Square4 = new Taxes("Income Tax", 4, 200);
		spaces.add(Square4);
		Square Square5 = new Station("São Bento", 200, 5);
		spaces.add(Square5);
		Square Square6 = new Property("Brussels","LIGHT_BLUE", 100, 6, 30, 90, 270, 400, 550, 50, 6);
		spaces.add(Square6);
		Square Square7 = new Chance(7);
		spaces.add(Square7);
		Square Square8 = new Property("Casablanca","LIGHT_BLUE", 100, 6, 30, 90, 270, 400, 550, 50, 8);
		spaces.add(Square8);
		Square Square9 = new Property("Buenos Aires","LIGHT_BLUE", 120, 8, 40, 100, 300, 450, 600, 50, 9);
		spaces.add(Square9);
		Square Square10 = new Jail();
		spaces.add(Square10);

		Square Square11 = new Property("Cape Town","PURPLE", 140, 10, 50, 150, 450, 625, 750, 100, 11);
		spaces.add(Square11);
		Square Square12 = new Companies("Electricity", 150, 12);
		spaces.add(Square12);
		Square Square13 = new Property("Moscow","PURPLE", 140, 10, 50, 150, 450, 625, 750, 100, 13);
		spaces.add(Square13);
		Square Square14 = new Property("Amsterdam","PURPLE", 160, 12, 60, 180, 500, 700, 900, 100, 14);
		spaces.add(Square14);
		Square Square15 = new Station("Liege-Guillemins", 200, 15);
		spaces.add(Square15);
		Square Square16 = new Property("Madrid","ORANGE", 180, 14, 70, 200, 550, 750, 950, 100, 16);
		spaces.add(Square16);
		Square Square17 = new CommunityChest(17);
		spaces.add(Square17);
		Square Square18 = new Property("Mexico City","ORANGE", 180, 14, 70, 200, 550, 750, 950, 100, 18);
		spaces.add(Square18);
		Square Square19 = new Property("Tokyo","ORANGE", 200, 16, 80, 220, 600, 800, 1000, 100, 19);
		spaces.add(Square19);
		Square Square20 = new FreeParking();
		spaces.add(Square20);

		Square Square21 = new Property("Cairo","RED", 220, 18, 90, 250, 700, 875, 1050, 150, 21);
		spaces.add(Square21);
		Square Square22 = new Chance(22);
		spaces.add(Square22);
		Square Square23 = new Property("Rome","RED", 220, 18, 90, 250, 700, 875, 1050, 150, 23);
		spaces.add(Square23);
		Square Square24 = new Property("Sydney","RED", 240, 20, 100, 300, 750, 925, 1100, 150, 24);
		spaces.add(Square24);
		Square Square25 = new Station("Dunedin Station", 200, 25);
		spaces.add(Square25);
		Square Square26 = new Property("Singapore","YELLOW", 260, 22, 110, 330, 800, 975, 1150, 150, 26);
		spaces.add(Square26);
		Square Square27 = new Property("New York","YELLOW", 260, 22, 110, 330, 800, 975, 1150, 150, 27);
		spaces.add(Square27);
		Square Square28 = new Companies("Water", 150, 28);
		spaces.add(Square28);
		Square Square29 = new Property("Berlin","YELLOW", 280, 24, 120, 360, 850, 1025, 1200, 150, 29);
		spaces.add(Square29);
		Square Square30 = new GoToJail();
		spaces.add(Square30);

		Square Square31 = new Property("London","GREEN", 300, 26, 130, 390, 900, 1100, 1275, 200, 31);
		spaces.add(Square31);
		Square Square32 = new Property("Rio de Janeiro","GREEN", 300, 26, 130, 390, 900, 1100, 1275, 200, 32);
		spaces.add(Square32);
		Square Square33 = new CommunityChest(33);
		spaces.add(Square33);
		Square Square34 = new Property("Shaghai","GREEN", 320, 28, 150, 450, 1000, 1200, 1400, 200, 34);
		spaces.add(Square34);
		Square Square35 = new Station("Milano Centrale", 200, 35);
		spaces.add(Square35);
		Square Square36 = new Chance(36);
		spaces.add(Square36);
		Square Square37 = new Property("Paris","DARK_BLUE", 350, 35, 175, 500, 1100, 1300, 1500, 200, 37);
		spaces.add(Square37);
		Square Square38 = new Taxes("Luxury Tax", 38, 100);
		spaces.add(Square38);
		Square Square39 = new Property("Dubai","DARK_BLUE", 400, 50, 200, 600, 1400, 1700, 2000, 200, 39);
		spaces.add(Square39);
	}
	
	public ArrayList<Square> getBoardArray(){
		return spaces;
	}
}
