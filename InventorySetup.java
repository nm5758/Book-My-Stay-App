
public class InventorySetup {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay App - UC3\n");

        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 10);
        inventory.addRoomType("Double Room", 5);
        inventory.addRoomType("Suite Room", 2);

        inventory.displayInventory();
        System.out.println("\nBooking 3 Single Rooms...");
        inventory.updateAvailability("Single Room", -3);
        System.out.println("\nCancelling 1 Single Room...");
        inventory.updateAvailability("Single Room", +1);
        System.out.println("\nTrying to overbook Suite Room...");
        inventory.updateAvailability("Suite Room", -5);
        inventory.displayInventory();
    }
}