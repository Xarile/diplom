package main.basekriteriev;

// Реализация интерфейса для первой таблицы
public class tableone implements CommonTableData {
    private int id;
    private String B1;
    private String B2;
    private String B3;
    private String B4;
    private String B5;
    private String B6;
    private String B7;
    private String B8;
    private String B9;
    private String B10;
    private String B11;
    private String B12;




    public tableone(int id, String B1, String B2, String B3, String B4, String B5, String B6, String B7, String B8, String B9, String B10, String B11, String B12 ) {
        this.id = id;
        this.B1 = B1;
        this.B2 = B2;
        this.B3 = B3;
        this.B4 = B4;
        this.B5 = B5;
        this.B6 = B6;
        this.B7 = B7;
        this.B8 = B8;
        this.B9 = B9;
        this.B10 = B10;
        this.B11 = B11;
        this.B12 = B12;

    }

    @Override
    public int getId() {

        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getB1() {
        return B1;
    }

    @Override
    public void setB1(String value) {
        this.B1 = value;
    }

    @Override
    public String getB2() {
        return B2;
    }

    @Override
    public void setB2(String value) {
        this.B2 = value;
    }

    @Override
    public String getB3() {
        return B3;
    }

    @Override
    public void setB3(String value) {
        this.B3 = value;
    }

    @Override
    public String getB4() {
        return B4;
    }

    @Override
    public void setB4(String value) {
        this.B4 = value;
    }

    @Override
    public String getB5() {
        return B5;
    }

    @Override
    public void setB5(String value) {
        this.B5 = value;
    }

    @Override
    public String getB6() {
        return B6;
    }

    @Override
    public void setB6(String value) {
        this.B6 = value;
    }

    @Override
    public String getB7() {
        return B7;
    }

    @Override
    public void setB7(String value) {
        this.B7 = value;
    }
    @Override
    public String getB8() {
        return B8;
    }

    @Override
    public void setB8(String value) {
        this.B8 = value;
    }
    @Override
    public String getB9() {
        return B9;
    }

    @Override
    public void setB9(String value) {
        this.B9 = value;
    }

    @Override
    public String getB10() {
        return B10;
    }

    @Override
    public void setB10(String value) {
        this.B10 = value;

    }
    @Override
    public String getB11() {
        return B11;
    }

    @Override
    public void setB11(String value) {
        this.B11 = value;
    }
    @Override
    public String getB12() {
        return B12;
    }

    @Override
    public void setB12(String value) {
        this.B12 = value;
    }
}
