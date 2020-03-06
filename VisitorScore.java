
public class VisitorScore implements Visitor{

	@Override
	public double visit(FootballTeam football) {
		double score = 0;
		if (football.getGender().equals("masculin")) {
			score = 2 * football.maxScore();
			score += football.sumScore();
			score -= football.maxScore();
			return score;
		}
		
		if (football.getGender().equals("feminin")) {
			score = 2 * football.minScore();
			score += football.sumScore();
			score -= football.minScore();
			return score;
		}
		
		return 0;
	}

	@Override
	public double visit(HandballTeam handball) {
		if (handball.getGender().equals("masculin")) {
			return handball.sumScore();
		}
		
		if (handball.getGender().equals("feminin")) {
			return handball.produsScore();
		}
		
		return 0;
	}

	@Override
	public double visit(BasketballTeam basketball) {
		return (double)basketball.sumScore() / basketball.getNumberOfPlayers();
	}
}
