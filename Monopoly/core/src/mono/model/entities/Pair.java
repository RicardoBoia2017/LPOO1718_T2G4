package mono.model.entities;

public class Pair {
	public int value1;
	public int value2;
	
	public Pair ()
	{
		this.value1 = 1;
		this.value2 = 1;
	}
	
	public Pair(int value1,int value2){
		this.value1 = value1;
		this.value2 = value2;;
	}
	
	public int getValue1() {return this.value1;}
	
	public int getValue2() {return this.value2;}
	
	public void setValue1 (int newValue) {this.value1 = newValue;}
	
	public void setValue2 (int newValue) {this.value2 = newValue;}

	public boolean sameValue () {return value1 == value2;}

}
