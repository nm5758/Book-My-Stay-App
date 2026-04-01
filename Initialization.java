public class Initialization {
    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay App");
        System.out.println("Version: 2.1\n");
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;
        System.out.println("Room Details:\n");
        single.displayRoomDetails();
        System.out.println("Available Rooms: " + singleAvailable + "\n");
        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + doubleAvailable + "\n");
        suite.displayRoomDetails();
        System.out.println("Available Rooms: " + suiteAvailable);
    }
    
}
