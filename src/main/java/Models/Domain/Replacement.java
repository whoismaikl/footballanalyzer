package Models.Domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Replacement {
    @JacksonXmlProperty(localName = "Laiks")
    private String replacementTime;
    @JacksonXmlProperty(localName = "Nr1")
    private int player1;
    @JacksonXmlProperty(localName = "Nr2")
    private int player2;

    public String getReplacementTime() {
        return replacementTime;
    }

    public void setReplacementTime(String replacementTime) {
        this.replacementTime = replacementTime;
    }

    public int getPlayer1() {
        return player1;
    }

    public void setPlayer1(int player1) {
        this.player1 = player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }
}
