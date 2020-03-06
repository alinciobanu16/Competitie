
public class FootballTeam extends Team implements Element {
	public FootballTeam() {
		
	}

	public FootballTeam(String teamName, String gender, int numberOfPlayers) {
		super(teamName, gender, numberOfPlayers);
	}
	
	@Override
	public double accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
