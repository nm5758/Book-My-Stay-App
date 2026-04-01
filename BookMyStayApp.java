import java.util.HashMap;
import java.util.Map;
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
    void updateAvailability(String type, int change) {
        int current = inventory.getOrDefault(type, 0);

        if (current + change < 0) {
            System.out.println("Not enough rooms available for " + type);
            return;
        }

        inventory.put(type, current + change);
    }
    void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}


