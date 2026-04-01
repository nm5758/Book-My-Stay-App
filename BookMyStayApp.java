import java.util.*;
class Room {
    String roomType;
    int beds;
    int size;
    int price;
    Room(String roomType, int beds, int size, int price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }
    void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq ft");
        System.out.println("Price per Night: ₹" + price);
        System.out.println("---------------------------");
    }
}
class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 1, 200, 2500);
    }
}
class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 2, 350, 4000);
    }
}
class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 3, 600, 8000);
    }
}
class RoomInventory {
    private Map<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
    }

    void addRoomType(String type, int count) {
        inventory.put(type, count);
    }
    int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }
    Map<String, Integer> getAllInventory() {
        return inventory;
    }
}
class RoomSearchService {
    private RoomInventory inventory;
    private Map<String, Room> roomCatalog;

    RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
        roomCatalog = new HashMap<>();
        roomCatalog.put("Single Room", new SingleRoom());
        roomCatalog.put("Double Room", new DoubleRoom());
        roomCatalog.put("Suite Room", new SuiteRoom());
    }
    void searchAvailableRooms() {

        System.out.println("\n🔍 Available Rooms:\n");

        Map<String, Integer> data = inventory.getAllInventory();

        for (String type : data.keySet()) {

            int available = inventory.getAvailability(type);

            // Defensive check → show only available rooms
            if (available > 0) {

                Room room = roomCatalog.get(type);

                room.displayRoomDetails();
                System.out.println("Available Count: " + available);
                System.out.println("===========================");
            }
        }
    }
}
public class UseCase4RoomSearch {
    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay App - UC4");
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 5);
        inventory.addRoomType("Double Room", 0); 
        inventory.addRoomType("Suite Room", 2);
        RoomSearchService searchService = new RoomSearchService(inventory);
        searchService.searchAvailableRooms();
        System.out.println("\n✅ Inventory remains unchanged after search.");
    }
}
