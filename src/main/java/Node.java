public class Node {

    private int id;
    private String companyName;
    private double latitude;
    private double longitude;
    private int priority;

    public Node() {
    }

    public Node(int id, String companyName, double latitude, double longitude, int priority) {
        this.id = id;
        this.companyName = companyName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
