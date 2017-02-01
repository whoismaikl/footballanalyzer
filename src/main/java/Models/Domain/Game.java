package Models.Domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "Spele")
public class Game {

    @JacksonXmlProperty(localName = "Laiks")
    private String gameDate;
    @JacksonXmlProperty(localName = "Skatitaji")
    private int watcherCount;
    @JacksonXmlProperty(localName = "Vieta")
    private String Location;
    @JacksonXmlProperty(localName = "Komanda")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Team> teams;
    @JacksonXmlProperty(localName = "T")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Referee> referees;
    @JacksonXmlProperty(localName = "VT")
    private Referee mainReferee;

    @Override
    public String toString() {
        return "Game { " +
                "gameDate='" + gameDate + '\'' +
                ", watcherCount=" + watcherCount +
                ", Location='" + Location + '\'' +
                ", teams=" + teams +
                ", referees=" + referees +
                ", mainReferee=" + mainReferee +
                '}';
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) { this.gameDate = gameDate; }

    public int getWatcherCount() {
        return watcherCount;
    }

    public void setWatcherCount(int watcherCount) {
        this.watcherCount = watcherCount;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Referee> getReferees() {
        return referees;
    }

    public void setReferees(List<Referee> referees) {
        this.referees = referees;
    }

    public Referee getMainReferee() {
        return mainReferee;
    }

    public void setMainReferee(Referee mainReferee) {
        this.mainReferee = mainReferee;
    }
}
