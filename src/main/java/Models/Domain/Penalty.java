package Models.Domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Penalty {
    @JacksonXmlProperty(localName = "Laiks")
    private String penaltyTime;
    @JacksonXmlProperty(localName = "Nr")
    private int playerNumber;

    public String getPenaltyTime() {
        return penaltyTime;
    }

    public void setPenaltyTime(String penaltyTime) {
        this.penaltyTime = penaltyTime;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
}
