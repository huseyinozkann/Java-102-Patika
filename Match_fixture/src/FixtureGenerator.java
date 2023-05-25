import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FixtureGenerator {
    private List<String> teams;
    private List<String> fixtures;

    public FixtureGenerator(List<String> teams) {
        this.teams = teams;
        this.fixtures = new ArrayList<>();
    }

    public void generateFixtures() {
        int totalTeams = teams.size();
        int totalRounds = totalTeams - 1;
        int matchesPerRound = totalTeams / 2;

        if (totalTeams % 2 != 0) {
            teams.add("Bay");
            totalTeams++;
        }

        for (int round = 0; round < totalRounds * 2; round++) {
            StringBuilder roundFixtures = new StringBuilder();

            for (int match = 0; match < matchesPerRound; match++) {
                String homeTeam;
                String awayTeam;

                if (round % 2 == 0) {
                    homeTeam = teams.get(match);
                    awayTeam = teams.get(totalTeams - 1 - match);
                } else {
                    homeTeam = teams.get(totalTeams - 1 - match);
                    awayTeam = teams.get(match);
                }

                if (awayTeam.equals("Bay")) {
                    roundFixtures.append(homeTeam).append(" Bay - ");
                } else if (homeTeam.equals("Bay")) {
                    roundFixtures.append("Bay - ").append(awayTeam);
                } else {
                    roundFixtures.append(homeTeam).append(" vs ").append(awayTeam).append(" - ");
                }
            }

            fixtures.add(roundFixtures.toString());
            Collections.rotate(teams.subList(1, totalTeams), 1);
        }
    }

    public void printFixtures() {
        int totalRounds = fixtures.size();

        for (int round = 0; round < totalRounds; round++) {
            System.out.println("Round " + (round + 1));
            System.out.println(fixtures.get(round));
            System.out.println();
        }
    }
}