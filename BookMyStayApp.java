public class BookMyStayApp{
    

    void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq ft");
        System.out.println("Price per Night: ₹" + price);
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
   