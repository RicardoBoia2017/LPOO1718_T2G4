package mono.model.entities;

public class Companies extends Square {
	private int price;
	private boolean owned;
	private Player owner;
	private int rent = 0;
    private boolean mortgage;
    private double mortgageValue;
    
	public Companies(int price, int position){
		super("Companies", position);
		this.price=price;
		this.owned=false;
		this.owner=null;
		this.rent=0;
		this.mortgage=false;
		this.mortgageValue=price/2;
		this.position=position;
	}

	@Override
	public void doAction(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getType() {

		return "Companies";
	}
}
