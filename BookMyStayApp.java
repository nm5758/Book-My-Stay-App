import java.util.*;
class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}
class BookingRequestQueue {

    private Queue<Reservation> queue;

    BookingRequestQueue() {
        queue = new LinkedList<>();
    }
    void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("✅ Request added for " + reservation.guestName);
    }
    void viewAllRequests() {
        System.out.println("\n📋 Current Booking Requests (FIFO Order):");

        if (queue.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        for (Reservation r : queue) {
            r.display();
        }
    }
    void peekNextRequest() {
        System.out.println("\n🔎 Next Request to Process:");

        Reservation r = queue.peek();

        if (r == null) {
            System.out.println("No requests in queue.");
        } else {
            r.display();
        }
    }
}
public class BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay App - UC5");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));
        bookingQueue.addRequest(new Reservation("David", "Single Room"));

        bookingQueue.viewAllRequests();

        bookingQueue.peekNextRequest();

        System.out.println("\n Note: No inventory updates happen in UC5.");
    }
} 