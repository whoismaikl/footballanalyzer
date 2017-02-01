package Models.Database;

import javax.persistence.*;

@Entity
@Table(name = "game")
public class DatabaseGame {

    @javax.persistence.Id
    @Column(name="id",columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "Date", columnDefinition = "CHAR(10)")
    private String date; // no need for calculating something in timestamps
    @Column(name = "Watchers", columnDefinition = "INTEGER")
    private int watchers;
    @Column(name = "Location", columnDefinition = "INTEGER")
    private String location;
    @Column(name = "Length", columnDefinition = "CHAR(6)")
    private int length;
    @Column(name = "TeamOneId", columnDefinition = "BIGINT")
    private Long teamOneId;
    @Column(name = "Score", columnDefinition = "CHAR(5)")
    private String score;  //format 2:1
    @Column(name = "TeamTwoId", columnDefinition = "BIGINT")
    private Long teamTwoId;
    @Column(name = "RefereeOneId", columnDefinition = "BIGINT")
    private Long refereeOneId;
    @Column(name = "RefereeTwoId", columnDefinition = "BIGINT")
    private Long refereeTwoId;
    @Column(name = "MainRefereeId", columnDefinition = "BIGINT")
    private Long mainRefereeId;

    @Override
    public String toString() {
        return "DatabaseGame { " +
                "id=" + id +
                ", date='" + date + '\'' +
                ", watchers=" + watchers +
                ", location='" + location + '\'' +
                ", length=" + length +
                ", teamOneId=" + teamOneId +
                ", score='" + score + '\'' +
                ", teamTwoId=" + teamTwoId +
                ", refereeOneId=" + refereeOneId +
                ", refereeTwoId=" + refereeTwoId +
                ", mainRefereeId=" + mainRefereeId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(int watchers) {
        this.watchers = watchers;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Long getTeamOneId() {
        return teamOneId;
    }

    public void setTeamOneId(Long teamOneId) {
        this.teamOneId = teamOneId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Long getTeamTwoId() {
        return teamTwoId;
    }

    public void setTeamTwoId(Long teamTwoId) {
        this.teamTwoId = teamTwoId;
    }

    public Long getRefereeOneId() {
        return refereeOneId;
    }

    public void setRefereeOneId(Long refereeOneId) {
        this.refereeOneId = refereeOneId;
    }

    public Long getRefereeTwoId() {
        return refereeTwoId;
    }

    public void setRefereeTwoId(Long refereeTwoId) {
        this.refereeTwoId = refereeTwoId;
    }

    public Long getMainRefereeId() {
        return mainRefereeId;
    }

    public void setMainRefereeId(Long mainRefereeId) {
        this.mainRefereeId = mainRefereeId;
    }
}
