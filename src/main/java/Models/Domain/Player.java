package Models.Domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

//@JacksonXmlRootElement(localName = "Speletajs")
public class Player {
    @JacksonXmlProperty(localName = "Loma")
    private char role;
    @JacksonXmlProperty(localName = "Uzvards")
    private String surname;
    @JacksonXmlProperty(localName = "Vards")
    private String name;
    @JacksonXmlProperty(localName = "Nr")
    private int playerNumber;


  public Player(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public Player() {
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

    public char getRole() {
        return role;
    }

    public void setRole(char role) {
        this.role = role;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
}
