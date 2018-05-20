package mono.model.entities;

public class Companies extends BuyableSquare {
	
	private int rent = 0;
    private double mortgageValue;
    
	public Companies(String name, int cost, int position){
		super(name, position, cost);
		this.rent = 0;
	}

	@Override
	protected void payRent(Player p1) {
		
	}
	
	@Override
	public String getType() {

		return "Company";
	}

}
