// FactoryPatternDemo.java

// Product Interface
interface Vehicle {
    void drive();
}

// Concrete Products
class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a car");
    }
}

class Bike implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Riding a bike");
    }
}

// Factory Class
class VehicleFactory {
    public static Vehicle getVehicle(String type) {
        if (type.equalsIgnoreCase("car")) {
            return new Car();
        } else if (type.equalsIgnoreCase("bike")) {
            return new Bike();
        }
        return null;
    }
}

// Client
public class FactoryPatternDemo {
    public static void main(String[] args) {
        Vehicle vehicle1 = VehicleFactory.getVehicle("car");
        vehicle1.drive(); // Driving a car
        
        Vehicle vehicle2 = VehicleFactory.getVehicle("bike");
        vehicle2.drive(); // Riding a bike
    }
}
