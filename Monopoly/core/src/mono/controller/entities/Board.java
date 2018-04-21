package mono.controller.entities;

import java.util.ArrayList;

public class Board {
	
	ArrayList<Square> spaces = new ArrayList<Square>();

	public Board() {
		Square Square0 = new StartSquare();
		spaces.add(Square0);
		HouseSquare Square1 = new HouseSquare("Athens","BROWN", 60, 2, 10, 30, 90, 160, 250, 50, 1);
		spaces.add(Square1);
		CommunityChest Square2 = new CommunityChest(2);
		spaces.add(Square2);
		HouseSquare Square3 = new HouseSquare("Lisbon","BROWN", 60, 2, 10, 30, 90, 160, 250, 50, 3);
		spaces.add(Square3);
		IncomeTax Square4 = new IncomeTax(4);
		spaces.add(Square4);
		Station Square5 = new Station(200, 5);
		spaces.add(Square5);
		HouseSquare Square6 = new HouseSquare("Brussels","LIGHT_BLUE", 100, 6, 30, 90, 270, 400, 550, 50, 6);
		spaces.add(Square6);
		Chance Square7 = new Chance(7);
		spaces.add(Square7);
		HouseSquare Square8 = new HouseSquare("Casablanca","LIGHT_BLUE", 100, 6, 30, 90, 270, 400, 550, 50, 8);
		spaces.add(Square8);
		HouseSquare Square9 = new HouseSquare("Buenos Aires","LIGHT_BLUE", 120, 8, 40, 100, 300, 450, 600, 50, 9);
		spaces.add(Square9);
		JailSquare Square10 = new JailSquare();
		spaces.add(Square10);

		HouseSquare Square11 = new HouseSquare("Cape Town","PURPLE", 140, 10, 50, 150, 450, 625, 750, 100, 11);
		spaces.add(Square11);
		Companies Square12 = new Companies(150, 12);
		spaces.add(Square12);
		HouseSquare Square13 = new HouseSquare("Moscow","PURPLE", 140, 10, 50, 150, 450, 625, 750, 100, 13);
		spaces.add(Square13);
		HouseSquare Square14 = new HouseSquare("Amsterdam","PURPLE", 160, 12, 60, 180, 500, 700, 900, 100, 14);
		spaces.add(Square14);
		Station Square15 = new Station(200, 15);
		spaces.add(Square15);
		HouseSquare Square16 = new HouseSquare("Madrid","ORANGE", 180, 14, 70, 200, 550, 750, 950, 100, 16);
		spaces.add(Square16);
		CommunityChest Square17 = new CommunityChest(17);
		spaces.add(Square17);
		HouseSquare Square18 = new HouseSquare("Mexico City","ORANGE", 180, 14, 70, 200, 550, 750, 950, 100, 18);
		spaces.add(Square18);
		HouseSquare Square19 = new HouseSquare("Tokyo","ORANGE", 200, 16, 80, 220, 600, 800, 1000, 100, 19);
		spaces.add(Square19);
		ParkingSquare Square20 = new ParkingSquare();
		spaces.add(Square20);

		HouseSquare Square21 = new HouseSquare("Cairo","RED", 220, 18, 90, 250, 700, 875, 1050, 150, 21);
		spaces.add(Square21);
		Chance Square22 = new Chance(22);
		spaces.add(Square22);
		HouseSquare Square23 = new HouseSquare("Rome","RED", 220, 18, 90, 250, 700, 875, 1050, 150, 23);
		spaces.add(Square23);
		HouseSquare Square24 = new HouseSquare("Sydney","RED", 240, 20, 100, 300, 750, 925, 1100, 150, 24);
		spaces.add(Square24);
		Station Square25 = new Station(200, 25);
		spaces.add(Square25);
		HouseSquare Square26 = new HouseSquare("Singapore","YELLOW", 260, 22, 110, 330, 800, 975, 1150, 150, 26);
		spaces.add(Square26);
		HouseSquare Square27 = new HouseSquare("New York","YELLOW", 260, 22, 110, 330, 800, 975, 1150, 150, 27);
		spaces.add(Square27);
		Companies Square28 = new Companies(150, 28);
		spaces.add(Square28);
		HouseSquare Square29 = new HouseSquare("Berlin","YELLOW", 280, 24, 120, 360, 850, 1025, 1200, 150, 29);
		spaces.add(Square29);
		GoToJailSquare Square30 = new GoToJailSquare();
		spaces.add(Square30);

		HouseSquare Square31 = new HouseSquare("London","GREEN", 300, 26, 130, 390, 900, 1100, 1275, 200, 31);
		spaces.add(Square31);
		HouseSquare Square32 = new HouseSquare("Rio de Janeiro","GREEN", 300, 26, 130, 390, 900, 1100, 1275, 200, 32);
		spaces.add(Square32);
		CommunityChest Square33 = new CommunityChest(33);
		spaces.add(Square33);
		HouseSquare Square34 = new HouseSquare("Shaghai","GREEN", 320, 28, 150, 450, 1000, 1200, 1400, 200, 34);
		spaces.add(Square34);
		Station Square35 = new Station(200, 35);
		spaces.add(Square35);
		Chance Square36 = new Chance(36);
		spaces.add(Square36);
		HouseSquare Square37 = new HouseSquare("Paris","DARK_BLUE", 350, 35, 175, 500, 1100, 1300, 1500, 200, 37);
		spaces.add(Square37);
		IncomeTax Square38 = new IncomeTax(38);
		spaces.add(Square38);
		HouseSquare Square39 = new HouseSquare("Dubai","DARK_BLUE", 400, 50, 200, 600, 1400, 1700, 2000, 200, 39);
		spaces.add(Square39);
	}
	
	public ArrayList<Square> getBoardArray(){
		return spaces;
	}
}
