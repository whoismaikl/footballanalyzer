package Models.Domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "Komanda")
public class Team {
    //private Long id;
    @JacksonXmlProperty(localName = "Nosaukums")
    private String teamName;
    @JacksonXmlProperty(localName = "Speletaji")
    private List<Player> players;
    @JacksonXmlProperty(localName = "Mainas")
    private List<Replacement> replacements;
    @JacksonXmlProperty(localName = "Pamatsastavs")
    private List<BasePlayer> basePlayers;
    @JacksonXmlProperty(localName = "Sodi")
    private List<Penalty> penalties;
    @JacksonXmlProperty(localName = "Varti")
    private List<Goal> goals;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Replacement> getReplacements() {
        return replacements;
    }

    public void setReplacements(List<Replacement> replacements) {
        this.replacements = replacements;
    }

    public List<BasePlayer> getBasePlayers() {
        return basePlayers;
    }

    public void setBasePlayers(List<BasePlayer> basePlayers) {
        this.basePlayers = basePlayers;
    }

    public List<Penalty> getPenalties() {
        return penalties;
    }

    public void setPenalties(List<Penalty> penalties) {
        this.penalties = penalties;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }
}
