
public class HandballTeam extends Team implements Element {
	public HandballTeam() {
		
	}

	public HandballTeam(String teamName, String gender, int numberOfPlayers) {
		super(teamName, gender, numberOfPlayers);
	}
	
	@Override
	public double accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
