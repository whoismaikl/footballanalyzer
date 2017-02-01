package Models.Database;

import javax.persistence.*;

@Entity
@Table(name = "referees")
public class DatabaseReferee {

    @javax.persistence.Id
    @Column(name = "Id", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Name", columnDefinition = "CHAR(30)")
    private String name;
    @Column(name = "Surname", columnDefinition = "CHAR(50)")
    private String surname;
    @Column(name = "AssignedPenalties", columnDefinition = "INTEGER")
    private int assignedPenalties;


    @Override
    public String toString() {
        return "Referee { " +
                "Name='" + name + '\'' +
                ", Surname='" + surname + '\'' +
                ", Assigned Penalties=" + assignedPenalties +
                '}';
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAssignedPenalties() {
        return assignedPenalties;
    }

    public void setAssignedPenalties(int assignedPenalties) {
        this.assignedPenalties = assignedPenalties;
    }
}
