import java.util.ArrayList;

class Network {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        ArrayList<Device> devices = new ArrayList<>();
        System.out.println("What is the number of WI-FI Connections?");
        int maxConnections = scanner.nextInt();
        System.out.println("What is the number of devices Clients want to connect?");
        int totalDevices = scanner.nextInt();

        Router router = new Router(maxConnections);
        for (int i = 0; i < totalDevices; i++) {
            System.out.println("Enter device " + (i + 1) + " details (Name Type):");
            String name = scanner.next();
            String type = scanner.next();
            devices.add(new Device(name, type, router));
        }
        devices.forEach(Device::start);
        String split = "----------------------------------------\n";
        router.AppendToFile(split);
        System.out.println("Please go check the output.txt file in path: " + System.getProperty("user.dir"));
    }
}
