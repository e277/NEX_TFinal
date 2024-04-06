public class Driver {
    private String id;
    private String name;
    private String licensePlate;
    private String timeIn;
    private String timeOut;

    public Driver(String id, String name, String licensePlate, String timeIn, String timeOut) {
        this.id = id;
        this.name = name;
        this.licensePlate = licensePlate;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getLicensePlate() { return licensePlate; }
    public String getTimeIn() { return timeIn; }
    public String getTimeOut() { return timeOut; }
}