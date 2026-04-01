import java.util.*;
class BookingRequest {
    String customerName;
    String roomType;

    public BookingRequest(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

class InventoryService {
    private Map<String, Integer> inventory = new HashMap<>();

    public InventoryService() {
        inventory.put("DELUXE", 2);
        inventory.put("STANDARD", 3);
    }

    public synchronized boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    public synchronized void decrement(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void printInventory() {
        System.out.println("Current Inventory: " + inventory);
    }
}
class BookingService {

    private Queue<BookingRequest> requestQueue = new LinkedList<>();

    private Map<String, Set<String>> allocatedRooms = new HashMap<>();

    private Set<String> allRoomIds = new HashSet<>();

    private InventoryService inventoryService;

    public BookingService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    public void addRequest(BookingRequest request) {
        requestQueue.offer(request);
    }
    private String generateRoomId(String roomType) {
        return roomType.substring(0, 3).toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 5);
    }

    public void processBookings() {

        while (!requestQueue.isEmpty()) {

            BookingRequest request = requestQueue.poll();
            String roomType = request.roomType;

            System.out.println("\nProcessing booking for: " + request.customerName);

            if (!inventoryService.isAvailable(roomType)) {
                System.out.println("❌ No rooms available for type: " + roomType);
                continue;
            }
            synchronized (this) {

                String roomId;
                do {
                    roomId = generateRoomId(roomType);
                } while (allRoomIds.contains(roomId));
                allRoomIds.add(roomId);

                allocatedRooms
                        .computeIfAbsent(roomType, k -> new HashSet<>())
                        .add(roomId);
                inventoryService.decrement(roomType);
                System.out.println("✅ Booking Confirmed!");
                System.out.println("Customer: " + request.customerName);
                System.out.println("Room Type: " + roomType);
                System.out.println("Allocated Room ID: " + roomId);
            }
        }
    }

    public void printAllocations() {
        System.out.println("\nRoom Allocations:");
        for (String type : allocatedRooms.keySet()) {
            System.out.println(type + " -> " + allocatedRooms.get(type));
        }
    }
}
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        InventoryService inventoryService = new InventoryService();
        BookingService bookingService = new BookingService(inventoryService);

        bookingService.addRequest(new BookingRequest("Alice", "DELUXE"));
        bookingService.addRequest(new BookingRequest("Bob", "DELUXE"));
        bookingService.addRequest(new BookingRequest("Charlie", "DELUXE")); // Should fail
        bookingService.addRequest(new BookingRequest("David", "STANDARD"));

        bookingService.processBookings();

        bookingService.printAllocations();
        inventoryService.printInventory();
    }
}