package Models.Domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "VG")
public class Goal {

    @JacksonXmlProperty(localName = "Laiks")
    private String goalTime;
    @JacksonXmlProperty(localName = "Nr")
    private int goalPlayer;
    @JacksonXmlProperty(localName = "Sitiens")
    private GoalType goalType;
    @JacksonXmlProperty(localName = "P")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Assist> assists;

    public String getGoalTime() {
        return goalTime;
    }

    public void setGoalTime(String goalTime) {
        this.goalTime = goalTime;
    }

    public GoalType getGoalType() {
        return goalType;
    }

    public void setGoalType(GoalType goalType) {
        this.goalType = goalType;
    }

    public int getGoalPlayer() {
        return goalPlayer;
    }

    public void setGoalPlayer(int goalPlayer) {
        this.goalPlayer = goalPlayer;
    }

    public List<Assist> getAssist() {
        return assists;
    }

    public void setAssist(List<Assist> assist) {
        this.assists = assist;
    }
}
