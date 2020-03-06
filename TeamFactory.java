
public class TeamFactory {
	private static TeamFactory uniqueInstance;
	private TeamFactory() {
		
	}
	
	public static TeamFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new TeamFactory();
		}
		return uniqueInstance;
	}
	
	/***
	 * creeaza o instanta e echipei cu parametrii dati
	 * @param type tipul de sport
	 * @param teamName numele echipei
	 * @param gender genul sportului : masculin sau feminin
	 * @param numberOfPlayers : nr de jucatori dintr-o echipa
	 * @return echipa creata
	 */
	public Team createTeam(String type, String teamName, String gender, int numberOfPlayers) {
		Team team = null;
		if (type.equals("football")) {
			team = new FootballTeam(teamName, gender, numberOfPlayers);
		}
		
		if (type.equals("basketball")) {
			team = new BasketballTeam(teamName, gender, numberOfPlayers);
		}
		
		if (type.equals("handball")) {
			team = new HandballTeam(teamName, gender, numberOfPlayers);
		}
		
		return team;
	}
}
