import java.util.*;
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}
class RoomInventory {
    private Map<String, Integer> rooms;
    public RoomInventory() {
        rooms = new HashMap<>();
        rooms.put("Single", 5);
        rooms.put("Double", 3);
        rooms.put("Suite", 2);
    }
    public void validateRoomType(String type) throws InvalidBookingException {
        if (!rooms.containsKey(type)) {
            throw new InvalidBookingException("Invalid room type: " + type);
        }
    }
    public void validateAvailability(String type, int count) throws InvalidBookingException {
        int available = rooms.get(type);
        if (count <= 0) {
            throw new InvalidBookingException("Booking count must be greater than 0");
        }
        if (available - count < 0) {
            throw new InvalidBookingException("Not enough rooms available for " + type);
        }
    }
    public void bookRoom(String type, int count) {
        rooms.put(type, rooms.get(type) - count);
    }
    public void displayInventory() {
        System.out.println("Current Room Availability:");
        for (Map.Entry<String, Integer> entry : rooms.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
class BookingValidator {
    public static void validate(RoomInventory inventory, String type, int count)
            throws InvalidBookingException {

        inventory.validateRoomType(type);
        inventory.validateAvailability(type, count);
    }
}
public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RoomInventory inventory = new RoomInventory();

        try {
            System.out.print("Enter Room Type (Single/Double/Suite): ");
            String type = sc.nextLine();

            System.out.print("Enter number of rooms: ");
            int count = sc.nextInt();
            BookingValidator.validate(inventory, type, count);
            inventory.bookRoom(type, count);
            System.out.println("Booking Successful!");

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid Input! Please enter correct data.");
        }
        System.out.println();
        inventory.displayInventory();

        sc.close();
    }
}