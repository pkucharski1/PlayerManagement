package pawelkucharski.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Player {
    private int id;
    private String name;
    private String surname;
    private final String date;
    private int points;

    public Player(String name, String surname, int points, int id) {
        this.name = name;
        this.surname = surname;
        this.points = points;
        this.id = id;
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Name = " + name +
                ", Surname = " + surname +
                ", Created = " + date +
                ", Points = " + points +
                ", Id = " + id;
    }
}
