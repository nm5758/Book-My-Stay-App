import java.util.*;
class AddOnService {
    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return serviceName + " (₹" + cost + ")";
    }
}
class AddOnServiceManager {
    private Map<String, List<AddOnService>> reservationServicesMap = new HashMap<>();
    public void addServices(String reservationId, List<AddOnService> services) {

        reservationServicesMap
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .addAll(services);

        System.out.println("✅ Services added for Reservation ID: " + reservationId);
    }
    public List<AddOnService> getServices(String reservationId) {
        return reservationServicesMap.getOrDefault(reservationId, new ArrayList<>());
    }
    public double calculateTotalCost(String reservationId) {
        double total = 0.0;

        List<AddOnService> services = reservationServicesMap.get(reservationId);

        if (services != null) {
            for (AddOnService service : services) {
                total += service.getCost();
            }
        }

        return total;
    }
    public void printServices(String reservationId) {
        List<AddOnService> services = getServices(reservationId);

        System.out.println("\nAdd-On Services for Reservation ID: " + reservationId);

        if (services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        for (AddOnService service : services) {
            System.out.println("- " + service);
        }

        System.out.println("Total Add-On Cost: ₹" + calculateTotalCost(reservationId));
    }
}
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        AddOnServiceManager serviceManager = new AddOnServiceManager();
        String reservation1 = "DEL-12345";
        String reservation2 = "STA-67890";
        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 1200);
        AddOnService extraBed = new AddOnService("Extra Bed", 800);
        List<AddOnService> servicesForRes1 = Arrays.asList(breakfast, airportPickup);
        List<AddOnService> servicesForRes2 = Arrays.asList(extraBed);
        serviceManager.addServices(reservation1, servicesForRes1);
        serviceManager.addServices(reservation2, servicesForRes2);
        serviceManager.printServices(reservation1);
        serviceManager.printServices(reservation2);
    }
}