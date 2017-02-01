package Models.Domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Referee {
    @JacksonXmlProperty(localName = "Vards")
    private String name;
    @JacksonXmlProperty(localName = "Uzvards")
    private String surname;

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
}
