public class Device extends Thread {
    private String name;
    private String type;
    private Router router;
    private int connection;

    public Device(String name, String type, Router router) {
        this.name = name;
        this.type = type;
        this.router = router;
    }
    public void setConnection(int connection) {
        this.connection = connection;
    }
    public int getConnection() {
        return this.connection;
    }
    public String getNameDevice() {
        return this.name;
    }
    public String getDeviceType() {
        return this.type;
    }
    
    public void connect() throws InterruptedException {
        router.occupyConnection(this);
        System.out.println("- Connection " + connection + ": " + name + " login");
        Thread.sleep((long) (Math.random() * 1000));
        System.out.println("- Connection " + connection + ": " + name + " performs online activity");
        disconnect();
    }

    public void disconnect() {
        System.out.println("- Connection " + connection + ": " + name + " Logged out");
        router.releaseConnection(this);
    }

    @Override
    public void run() {
        try {
            connect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
