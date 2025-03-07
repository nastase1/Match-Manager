package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class League {
    private List<Team> teams;
    private List<Match> matches;

    public League() {
        teams = new ArrayList<>();
        matches = new ArrayList<>();
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public void loadMatchesFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(";");
                if (parts.length != 5) {
                    continue;
                }
                String team1Name = parts[0].trim();
                String team2Name = parts[1].trim();
                int goalsTeam1 = Integer.parseInt(parts[2].trim());
                int goalsTeam2 = Integer.parseInt(parts[3].trim());
                boolean finished = Boolean.parseBoolean(parts[4].trim());

                Team team1 = findOrCreateTeam(team1Name);
                Team team2 = findOrCreateTeam(team2Name);

                Match match = new Match(team1, team2, goalsTeam1, goalsTeam2, finished);
                addMatch(match);
            }
        } catch (IOException e) {
            System.out.println("Error reading matches file: " + e.getMessage());
        }
    }

    private Team findOrCreateTeam(String teamName) {
        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(teamName)) {
                return team;
            }
        }
        Team newTeam = new Team(teamName);
        addTeam(newTeam);
        return newTeam;
    }

    public List<TeamStats> calculateTeamStats() {
        Map<String, TeamStats> statsMap = new HashMap<>();
        for (Team team : teams) {
            statsMap.put(team.getName(), new TeamStats(team));
        }
        for (Match match : matches) {
            if (!match.isFinished()) {
                continue;
            }
            TeamStats stats1 = statsMap.get(match.getTeam1().getName());
            TeamStats stats2 = statsMap.get(match.getTeam2().getName());
            int gf1 = match.getGoalsTeam1();
            int ga1 = match.getGoalsTeam2();
            int gf2 = match.getGoalsTeam2();
            int ga2 = match.getGoalsTeam1();

            stats1.addMatchResult(gf1, ga1);
            stats2.addMatchResult(gf2, ga2);

            if (gf1 > ga1) {
                stats1.addPoints(5);
            } else if (gf1 < ga1) {
                stats2.addPoints(5);
            } else {
                stats1.addPoints(2);
                stats2.addPoints(2);
            }
        }
        List<TeamStats> statsList = new ArrayList<>(statsMap.values());
        statsList.sort((ts1, ts2) -> {
            if (ts2.getPoints() != ts1.getPoints()) {
                return ts2.getPoints() - ts1.getPoints();
            } else {
                return ts2.getGoalDifference() - ts1.getGoalDifference();
            }
        });
        return statsList;
    }

    public TeamStats getTeamStats(String teamName) {
        List<TeamStats> statsList = calculateTeamStats();
        for (TeamStats ts : statsList) {
            if (ts.getTeam().getName().equalsIgnoreCase(teamName)) {
                return ts;
            }
        }
        return null;
    }

    public int getTeamRank(String teamName) {
        List<TeamStats> statsList = calculateTeamStats();
        for (int i = 0; i < statsList.size(); i++) {
            if (statsList.get(i).getTeam().getName().equalsIgnoreCase(teamName)) {
                return i + 1;
            }
        }
        return -1;
    }

    public List<TeamStats> getRanking() {
        return calculateTeamStats();
    }
}
