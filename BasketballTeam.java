
public class BasketballTeam extends Team implements Element {

	public BasketballTeam() {
		
	}

	public BasketballTeam(String teamName, String gender, int numberOfPlayers) {
		super(teamName, gender, numberOfPlayers);
	}
	
	@Override
	public double accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
