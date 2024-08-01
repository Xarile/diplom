package main.basekriteriev.tables;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class tableone {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty B1;
    private final SimpleStringProperty B2;
    private final SimpleStringProperty B3;
    private final SimpleStringProperty B4;
    private final SimpleStringProperty B5;
    private final SimpleStringProperty B6;
    private final SimpleStringProperty B7;

    public tableone(int id, String B1, String B2 , String B3 , String B4, String B5, String B6 , String B7) {
        this.id = new SimpleIntegerProperty(id);
        this.B1 = new SimpleStringProperty(B1);
        this.B2 = new SimpleStringProperty(B2);
        this.B3 = new SimpleStringProperty(B3);
        this.B4 = new SimpleStringProperty(B4);
        this.B5 = new SimpleStringProperty(B5);
        this.B6 = new SimpleStringProperty(B6);
        this.B7 = new SimpleStringProperty(B7);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getB1() {
        return B1.get();
    }

    public void setB1(String B1) {
        this.B1.set(B1);
    }

    public String getB2() {
        return B2.get();
    }

    public void setB2(String B2) {
        this.B2.set(B2);
    }

    public String getB3() {
        return B3.get();
    }

    public void setB3(String B3) {
        this.B3.set(B3);
    }
    public String getB4() {
        return B4.get();
    }

    public void setB4(String B4) {
        this.B4.set(B4);
    }

    public String getB5() {
        return B5.get();
    }

    public void setB5(String B5) {
        this.B5.set(B5);
    }

    public String getB6() {
        return B6.get();
    }

    public void setB6(String B6) {
        this.B6.set(B6);
    }

    public String getB7() {
        return B7.get();
    }

    public void setB7(String B7) {
        this.B7.set(B7);
    }

}