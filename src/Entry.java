public class Entry {
    private String name;
    private String licensePlate;
    private int id;
    private String timeIn;
    private String timeOut;

    public Entry(String name, String licensePlate, int id, String timeIn, String timeOut) {
        this.name = name;
        this.licensePlate = licensePlate;
        this.id = id;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public String getName() {
        return name;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getId() {return id;}

    public String getTimeIn() {return timeIn;}

    public String getTimeOut() {return timeOut;}
}

