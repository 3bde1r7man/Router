import java.io.File;
import java.io.FileWriter;
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
            AppendToFile("- (" + device.getNameDevice() + ")" +"(" + device.getDeviceType() + ") arrived and waiting\n");
        }
        semaphore.P();
        AppendToFile("- (" + device.getNameDevice() + ")(" + device.getDeviceType() + ") arrived\n");
        int connectionIndex = availableIndices.poll();
        connections.add(device);
        device.setConnection(connectionIndex);
        AppendToFile("- Connection " + connectionIndex + ": " + device.getNameDevice() + " Occupied\n");
        
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
    public void AppendToFile(String text){
        File file = new File("output.txt");
        try {
            FileWriter fr = new FileWriter(file, true);
            fr.write(text);
            fr.close();
        } catch (Exception e) {
            System.out.println("Error in file output");
        }
    }
}