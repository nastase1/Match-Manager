package org.example;

public class Match {
    private Team team1;
    private Team team2;
    private int goalsTeam1;
    private int goalsTeam2;
    private boolean finished;

    public Match(Team team1, Team team2, int goalsTeam1, int goalsTeam2, boolean finished) {
        this.team1 = team1;
        this.team2 = team2;
        this.goalsTeam1 = goalsTeam1;
        this.goalsTeam2 = goalsTeam2;
        this.finished = finished;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public int getGoalsTeam1() {
        return goalsTeam1;
    }

    public int getGoalsTeam2() {
        return goalsTeam2;
    }

    public boolean isFinished() {
        return finished;
    }
}

