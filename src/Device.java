public class Device extends Thread {
    private String name;
    private String type;
    private Router router;

    public Device(String name, String type, Router router) {
        this.name = name;
        this.type = type;
        this.router = router;
    }

    public String getNameDevice() {
        return this.name;
    }
    public String getDeviceType() {
        return this.type;
    }
    public void connect() throws InterruptedException {
        System.out.println("- (" + name + ")(" + type + ") arrived");
        router.occupyConnection(this);
        System.out.println("- Connection " + (router.getConnections().indexOf(this) + 1) + ": " + name + " login");
        Thread.sleep(1000);
        System.out.println("- Connection " + (router.getConnections().indexOf(this) + 1) + ": " + name + " performs online activity");
        disconnect();
    }

    public void disconnect() {
        System.out.println("- Connection " + (router.getConnections().indexOf(this) + 1) + ": " + name + " Logged out");
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
