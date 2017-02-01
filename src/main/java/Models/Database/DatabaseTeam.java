package Models.Database;

import javax.persistence.*;

@Entity
@Table(name = "team")
public class DatabaseTeam {

    @javax.persistence.Id
    @Column(name = "id", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Name", columnDefinition = "CHAR(100)")
    private String name;
    @Column(name = "Score", columnDefinition = "INTEGER")
    private int score;
    @Column(name = "WinsInMainTime", columnDefinition = "INTEGER")
    private int winInMainTime;
    @Column(name = "LosesInMainTime", columnDefinition = "INTEGER")
    private int loseInMainTime;
    @Column(name = "WinsInAdditionalTime", columnDefinition = "INTEGER")
    private int winInAdditionalTime;
    @Column(name = "LosesInAdditionalTime", columnDefinition = "INTEGER")
    private int loseInAdditionalTime;
    @Column(name = "ScoredGoals", columnDefinition = "INTEGER")
    private int scoredGoals;
    @Column(name = "LostGoals", columnDefinition = "INTEGER")
    private int lostGoals;


    @Override
    public String toString() {
        return "Team { " +
                "Team Id='" + id + '\'' +
                "Name='" + name + '\'' +
                ", Score=" + score +
                ", Wins In Main Time=" + winInMainTime +
                ", Loses In Main Time=" + loseInMainTime +
                ", Wins In Additional Time=" + winInAdditionalTime +
                ", Loses In Additional Time=" + loseInAdditionalTime +
                ", Scored Goals=" + scoredGoals +
                ", Lost Goals=" + lostGoals +
                '}';
    }

    public DatabaseTeam() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWinInMainTime() {
        return winInMainTime;
    }

    public void setWinInMainTime(int winInMainTime) {
        this.winInMainTime = winInMainTime;
    }

    public int getLoseInMainTime() {
        return loseInMainTime;
    }

    public void setLoseInMainTime(int loseInMainTime) {
        this.loseInMainTime = loseInMainTime;
    }

    public int getWinInAdditionalTime() {
        return winInAdditionalTime;
    }

    public void setWinInAdditionalTime(int winInAdditionalTime) {
        this.winInAdditionalTime = winInAdditionalTime;
    }

    public int getLoseInAdditionalTime() {
        return loseInAdditionalTime;
    }

    public void setLoseInAdditionalTime(int loseInAdditionalTime) {
        this.loseInAdditionalTime = loseInAdditionalTime;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public int getLostGoals() {
        return lostGoals;
    }

    public void setLostGoals(int lostGoals) {
        this.lostGoals = lostGoals;
    }
}
