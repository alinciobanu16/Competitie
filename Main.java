import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File(args[1]));
		PrintStream out = new PrintStream(new File(args[3]));
		System.setOut(out);
			
		String teamName, gender, type;
		int numberOfPlayers;
		TeamFactory team = TeamFactory.getInstance();
		ArrayList<Team> registeredTeams = new ArrayList<>(); /*vector cu echipele inscrise*/	
		String line;
		String lineSplit[];
			
		while (input.hasNext()) {
			line = input.nextLine(); /*citesc o linie din fisier*/
			lineSplit = line.split(", "); /*fac split dupa virgula si spatiu*/
			if (lineSplit[0].equals("football")) {
				type = lineSplit[0];
				teamName = lineSplit[1];
				gender = lineSplit[2];
				numberOfPlayers = Integer.parseInt(lineSplit[3]);
				Team t = team.createTeam(type, teamName, gender, numberOfPlayers); /*creez noua echipa*/
				for (int i = 0; i < numberOfPlayers; i++) {
					/*citesc membrii echipei*/
					line = input.nextLine();
					lineSplit = line.split(", ");
					t.players.add(new Player(lineSplit[0], Integer.parseInt(lineSplit[1])));
				}
				registeredTeams.add(t); /*adaug echipa in vectorul de echipe inregistrate*/
			}
				
			if (lineSplit[0].equals("basketball")) {
				type = lineSplit[0];
				teamName = lineSplit[1];
				gender = lineSplit[2];
				numberOfPlayers = Integer.parseInt(lineSplit[3]);
				Team t = team.createTeam(type, teamName, gender, numberOfPlayers);
				for (int i = 0; i < numberOfPlayers; i++) {
					line = input.nextLine();
					lineSplit = line.split(", ");
					t.players.add(new Player(lineSplit[0], Integer.parseInt(lineSplit[1])));
				}
				registeredTeams.add(t);
			}
				
			if (lineSplit[0].equals("handball")) {
				type = lineSplit[0];
				teamName = lineSplit[1];
				gender = lineSplit[2];
				numberOfPlayers = Integer.parseInt(lineSplit[3]);
				Team t = team.createTeam(type, teamName, gender, numberOfPlayers);
				for (int i = 0; i < numberOfPlayers; i++) {
					line = input.nextLine();
					lineSplit = line.split(", ");
					t.players.add(new Player(lineSplit[0], Integer.parseInt(lineSplit[1])));
				}
				registeredTeams.add(t);
			}
		}
			
		if (args[0].equals("inscriere")) {
			for (Team t : registeredTeams) {
				t.inscriere();
				/**
				 * pt. fiecare echipa inscrisa afisez detaliile despre ea
				 */
			}
			
			input.close();
			out.close();		
		}
		
		if (args[0].equals("competitie")) {
			Scanner input2 = new Scanner(new File(args[2]));
			Subject subject = new Subject();
			Competition competition = Competition.getInstance(subject);
			line = input2.nextLine();
			lineSplit = line.split(", ");
			competition.setType(lineSplit[0]); /*setez tipul competitiei*/
			competition.setGender(lineSplit[1]); /*setez genul competitiei*/
			while (input2.hasNext()) {
				line = input2.nextLine();
				for (Team t : registeredTeams) { /*caut in echipele inregistrate pe cele care se incadreaza in tip si gen*/
					if (t.getTeamName().equals(line) && t.getGender().equals(competition.getGender())) {
						competition.teams.add(t); /*le adaug in competitie*/
						competition.clasament.add(t); /*le adaug totodata si in clasament*/
						break;
					}
				}
			}
			
			Visitor visitor = new VisitorScore();
			
			for (Team t : competition.teams) {
				t.setPoints(0);
				/*la inceput fiecare echipa are cate 0 puncte*/
			}
			
			/*fiecare echipa joaca cate un meci impotriva celorlalte*/
			for (int i = 0; i < competition.teams.size() - 1; i++) {
				for (int j = i + 1; j < competition.teams.size(); j++) {
					double score1 = competition.teams.get(i).accept(visitor);
					double score2 = competition.teams.get(j).accept(visitor);
					if (score1 < score2) {
						/*adaug 3 puncte echipei castigatoare sau cate 1p ambelor in caz de egalitate*/
						competition.teams.get(j).setPoints(competition.teams.get(j).getPoints() + 3);
					} else if (score1 > score2) {
						competition.teams.get(i).setPoints(competition.teams.get(i).getPoints() + 3);
					} else if (score1 == score2) {
						competition.teams.get(i).setPoints(competition.teams.get(i).getPoints() + 1);
						competition.teams.get(j).setPoints(competition.teams.get(j).getPoints() + 1);
					}
					competition.subject.notifyAllObservers(); /*actualizez clasamentul*/
				}
			}
			
			/*afisez echipele de pe primele 3 locuri din clasament*/
			for (int i = 0 ; i < 3; i++) {
				System.out.println(competition.clasament.get(i).getTeamName());
			}
			
			/*afisez echipele in ordinea intrarii in competitie cu pozitia ocupata in clasament*/
			for (Team t : competition.teams) {
				System.out.println("Echipa " + t.getTeamName() + " a ocupat locul " + competition.getPosition(t));
			}	
			
			input2.close();
			out.close();
		}	
	}
}
