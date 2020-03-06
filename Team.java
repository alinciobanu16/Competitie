import java.util.ArrayList;

public class Team implements Element, Comparable<Team> {
	private String teamName;
	private String gender;
	private int numberOfPlayers;
	private double points; /* puncte obtinute de echipa*/
	ArrayList<Player> players;
	
	public Team() {
		
	}
	
	/***
	 * 
	 * @param teamName numele echipei
	 * @param gender genul : masculin sau feminin
	 * @param numberOfPlayers : numar de jucatori dintr-o echipa
	 */
	public Team(String teamName, String gender, int numberOfPlayers) {
		super();
		this.teamName = teamName;
		this.gender = gender;
		this.numberOfPlayers = numberOfPlayers;
		players = new ArrayList<Player>(numberOfPlayers);
	}
	
	/***
	 * afiseaza detalii despre echipa inscrisa
	 */
	public void inscriere() {
		System.out.print("{teamName: " + getTeamName() + ", gender: " + getGender() + 
				", numberOfPlayers: " + getNumberOfPlayers() + ", players: [");
		int cnt = 0;
		for (Player p : players) {
			cnt++;
			if (cnt > 1) {
				System.out.print(", ");
			}

			System.out.print("{name: " + p.getName() + ", " + "score: " + p.getScore() + "}");			
		}
		System.out.println("]}");
	}
	
	@Override
	public double accept(Visitor visitor) {
		return 1;
	}
	
	/***
	 * @return cel mai mare scor al membrului dintr-o echipa
	 */
	public int maxScore() {
		int max = players.get(0).getScore();
		for (Player p : players) {
			if (p.getScore() > max) {
				max = p.getScore();
			}
		}
		
		return max;
	}
	
	/**
	 * @return cel mai mic scor al membrului dintr-o echipa
	 */
	public int minScore() {
		int min = players.get(0).getScore();
		for (Player p : players) {
			if (p.getScore() < min) {
				min = p.getScore();
			}
		}
		
		return min;
	}
	
	/**
	 * @return suma scorurilor membrilor dintr-o echipa
	 */
	public int sumScore() {
		int sum = 0;
		for (Player p : players) {
			sum += p.getScore();
		}
		
		return sum;
	}
	
	/**
	 * @return produsul scorurilor membrilor dintr-o echipa
	 */
	public int produsScore() {
		int produs = 1;
		for (Player p : players) {
			produs *= p.getScore();
		}
		
		return produs;
	}
	
	@Override
	public int compareTo(Team team) {
		return (this.getPoints() < team.getPoints() ? -1 :
			(this.getPoints() == team.getPoints() ? 0 : 1));
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
	
	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}
}
