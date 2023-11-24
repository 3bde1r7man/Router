import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Router {
    private ArrayList<Device> connections;
    private Queue<Integer> availableIndices;
    private Semaphore semaphore;

    public Router(int maxConnections) {
        connections = new ArrayList<>(maxConnections);
        semaphore = new Semaphore(maxConnections);
        availableIndices = new LinkedList<>();
        for (int i = 1; i <= maxConnections; i++) {
            availableIndices.add(i);
        }
    }

    public void occupyConnection(Device device) throws InterruptedException {
        if(semaphore.value <= 0){
            System.out.println("- (" + device.getNameDevice() + ")" +"(" + device.getDeviceType() + ") arrived and waiting");
        }
        semaphore.P();
        System.out.println("- (" + device.getNameDevice() + ")(" + device.getDeviceType() + ") arrived");
        int connectionIndex = availableIndices.poll();
        connections.add(device);
        device.setConnection(connectionIndex);
        System.out.println("- Connection " + connectionIndex + ": " + device.getNameDevice() + " Occupied");
        
    }

    public void releaseConnection(Device device) {
        connections.remove(device);
        int releasedIndex = device.getConnection();
        availableIndices.offer(releasedIndex);
        semaphore.V();
    }

    public ArrayList<Device> getConnections() {
        return  connections;
    }
}