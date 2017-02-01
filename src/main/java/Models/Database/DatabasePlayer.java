package Models.Database;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class DatabasePlayer {

    @Override
    public String toString() {
        return "Player { " +
                "Team=" + teamId +
                ", Name='" + name + '\'' +
                ", Surname='" + surname + '\'' +
                ", Games Played=" + gamesPlayed +
                ", Time Played=" + timePlayed +
                ", Role=" + role +
                ", Player Number=" + number +
                ", Times as Main Player=" + timesAsMainPlayer +
                ", Goal Count=" + goals +
                ", Assist Copunt=" + assists +
                ", Yellow Cards=" + yellowCards +
                ", Red Cards=" + redCards +
                '}';
    }

    @javax.persistence.Id
    @Column(name = "Id", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TeamId", columnDefinition = "BIGINT")
    private Long teamId;

    @Column(name = "Name", columnDefinition = "CHAR(30)")
    private String name;
    @Column(name = "Surname", columnDefinition = "CHAR(50)")
    private String surname;
    @Column(name = "GamesPlayed", columnDefinition = "INTEGER")
    private int gamesPlayed;
    @Column(name = "TimePlayed", columnDefinition = "INTEGER")
    private int timePlayed;
    @Column(name = "Role", columnDefinition = "CHAR(1)")
    private char role;
    @Column(name = "Number", columnDefinition = "INTEGER")
    private int number;
    @Column(name = "TimesAsMainPlayer", columnDefinition = "INTEGER")
    private int timesAsMainPlayer;
    @Column(name = "GoalCount", columnDefinition = "INTEGER")
    private int goals;
    @Column(name = "AssistCount", columnDefinition = "INTEGER")
    private int assists;
    @Column(name = "YellowCards", columnDefinition = "INTEGER")
    private int yellowCards;
    @Column(name = "RedCards", columnDefinition = "INTEGER")
    private int redCards;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(int timePlayed) {
        this.timePlayed = timePlayed;
    }

    public char getRole() {
        return role;
    }

    public void setRole(char role) {
        this.role = role;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTimesAsMainPlayer() {
        return timesAsMainPlayer;
    }

    public void setTimesAsMainPlayer(int timesAsMainPlayer) {
        this.timesAsMainPlayer = timesAsMainPlayer;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }
}
