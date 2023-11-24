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
        
        router.AppendToFile("- Connection " + connection + ": " + name + " login\n");
        Thread.sleep((long) (Math.random() * 1000));
        
        router.AppendToFile("- Connection " + connection + ": " + name + " performs online activity\n");
        disconnect();
    }

    public void disconnect() {
        
        router.AppendToFile("- Connection " + connection + ": " + name + " Logged out\n");
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
