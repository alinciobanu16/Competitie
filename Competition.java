import java.util.ArrayList;
import java.util.Collections;

public class Competition extends Observer implements Element {
	private String type;
	private String gender;
	ArrayList<Team> teams;
	ArrayList<Team> clasament;
	private static Competition uniqueInstance;
	
	private Competition(Subject subject) {
		teams = new ArrayList<Team>();
		clasament = new ArrayList<Team>();
		this.subject = subject;
		this.subject.attach(this);
	}
	
	public static Competition getInstance(Subject subject) {
		if (uniqueInstance == null) {
			uniqueInstance = new Competition(subject);
		}
		return uniqueInstance;
	}

	@Override
	public double accept(Visitor visitor) {
		for (Team u : teams) {
			u.accept(visitor);
		}
		
		return 1;
	}
	
	public void update() {
		Collections.sort(clasament, Collections.reverseOrder());
	}
	
	/**
	 * 
	 * @param team echipa pe care o caut in clasament
	 * @return pozitia pe care se afla echipa in clasament
	 */
	public int getPosition(Team team) {
		for (int j = 0; j < clasament.size(); j++) {
			if (team.getTeamName().equals(clasament.get(j).getTeamName())) {
				return j + 1;
			}
		}
		
		return 0;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
