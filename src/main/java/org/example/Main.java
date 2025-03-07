package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        League league = new League();

        league.loadMatchesFromFile("matches.txt");

        List<TeamStats> ranking = league.getRanking();
        System.out.println("Clasament:");
        for (int i = 0; i < ranking.size(); i++) {
            TeamStats ts = ranking.get(i);
            System.out.printf("%d. %s - %d puncte, Diferenta de goluri: %d%n",
                    i + 1, ts.getTeam().getName(), ts.getPoints(), ts.getGoalDifference());
        }

        String searchTeam = "TeamA";
        int rank = league.getTeamRank(searchTeam);
        TeamStats stats = league.getTeamStats(searchTeam);
        if (stats != null) {
            System.out.printf("Echipa %s ocupa locul %d cu %d puncte si o diferenta de goluri de %d.%n",
                    searchTeam, rank, stats.getPoints(), stats.getGoalDifference());
        } else {
            System.out.println("Echipa " + searchTeam + " nu a fost gasita.");
        }
    }
}
