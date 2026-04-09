import java.util.*;

class UseCase10BookingCancellation {
    static Map<String, Integer> inventory = new HashMap<>();
    static Map<String, String> bookings = new HashMap<>();
    static Stack<String> rollbackStack = new Stack<>();

    public static void main(String[] args) {
        inventory.put("Single", 2);
        inventory.put("Double", 2);
        bookings.put("B101", "Single");
        bookings.put("B102", "Double");
        inventory.put("Single", inventory.get("Single") - 1);
        inventory.put("Double", inventory.get("Double") - 1);

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Booking ID to cancel: ");
        String bookingId = sc.nextLine();

        cancelBooking(bookingId);
        System.out.println("\nUpdated Inventory: " + inventory);
        System.out.println("Remaining Bookings: " + bookings);
        System.out.println("Rollback Stack: " + rollbackStack);
    }

    public static void cancelBooking(String bookingId) {
        if (!bookings.containsKey(bookingId)) {
            System.out.println("Error: Booking does not exist or already cancelled.");
            return;
        }
        String roomType = bookings.get(bookingId);
        String roomId = roomType + "_Room";
        rollbackStack.push(roomId);
        inventory.put(roomType, inventory.get(roomType) + 1);
        bookings.remove(bookingId);

        System.out.println("Booking " + bookingId + " cancelled successfully.");
        System.out.println("Room released: " + roomId);
    }
}