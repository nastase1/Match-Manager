package org.example;

public class TeamStats {
    private Team team;
    private int points;
    private int goalsFor;
    private int goalsAgainst;

    public TeamStats(Team team) {
        this.team = team;
        this.points = 0;
        this.goalsFor = 0;
        this.goalsAgainst = 0;
    }

    public Team getTeam() {
        return team;
    }

    public int getPoints() {
        return points;
    }

    public int getGoalDifference() {
        return goalsFor - goalsAgainst;
    }

    public void addMatchResult(int gf, int ga) {
        goalsFor += gf;
        goalsAgainst += ga;
    }

    public void addPoints(int pts) {
        points += pts;
    }
}

