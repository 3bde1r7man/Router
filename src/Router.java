import java.util.ArrayList;

class Router {
    private ArrayList<Device> connections;
    private Semaphore semaphore;

    public Router(int maxConnections) {
        connections = new ArrayList<>(maxConnections);
        semaphore = new Semaphore(maxConnections);
    }

    public void occupyConnection(Device device) throws InterruptedException {
        
        semaphore.P(device.getNameDevice());
        connections.add(device);
        System.out.println("- Connection " + (connections.indexOf(device) + 1) + ": " + device.getNameDevice() + " Occupied");
    }

    public void releaseConnection(Device device) {
        connections.remove(device);
        semaphore.V();
    }

    public ArrayList<Device> getConnections() {
        return connections;
    }
}