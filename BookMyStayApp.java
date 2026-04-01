import java.util.*;
class Reservation {
    private String reservationId;
    private String customerName;
    private String roomType;

    public Reservation(String reservationId, String customerName, String roomType) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId +
               ", Customer: " + customerName +
               ", Room Type: " + roomType;
    }
}

class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(history); 
    }
}

class BookingReportService {

    public void printAllBookings(List<Reservation> reservations) {
        System.out.println("\n=== Booking History ===");

        if (reservations.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }

    public void generateRoomTypeReport(List<Reservation> reservations) {

        Map<String, Integer> roomTypeCount = new HashMap<>();

        for (Reservation r : reservations) {
            roomTypeCount.put(
                r.getRoomType(),
                roomTypeCount.getOrDefault(r.getRoomType(), 0) + 1
            );
        }

        System.out.println("\n=== Room Type Summary Report ===");

        for (String type : roomTypeCount.keySet()) {
            System.out.println(type + " -> " + roomTypeCount.get(type) + " bookings");
        }
    }

    public void findBookingsByCustomer(List<Reservation> reservations, String customerName) {

        System.out.println("\n=== Bookings for Customer: " + customerName + " ===");

        boolean found = false;

        for (Reservation r : reservations) {
            if (r.getCustomerName().equalsIgnoreCase(customerName)) {
                System.out.println(r);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No bookings found for this customer.");
        }
    }
}

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory bookingHistory = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        bookingHistory.addReservation(new Reservation("DEL-11111", "Alice", "DELUXE"));
        bookingHistory.addReservation(new Reservation("STA-22222", "Bob", "STANDARD"));
        bookingHistory.addReservation(new Reservation("DEL-33333", "Charlie", "DELUXE"));
        bookingHistory.addReservation(new Reservation("STA-44444", "Alice", "STANDARD"));

        List<Reservation> reservations = bookingHistory.getAllReservations();

        reportService.printAllBookings(reservations);
        reportService.generateRoomTypeReport(reservations);
        reportService.findBookingsByCustomer(reservations, "Alice");
    }
}