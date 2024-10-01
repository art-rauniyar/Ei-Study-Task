// ObserverPatternDemo.java

import java.util.ArrayList;
import java.util.List;

// Subject interface
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Observer interface
interface Observer {
    void update(float price);
}

// Concrete Subject: Stock
class Stock implements Subject {
    private List<Observer> observers;
    private float price;
    
    public Stock() {
        observers = new ArrayList<>();
    }
    
    public void setPrice(float price) {
        this.price = price;
        notifyObservers();
    }
    
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(price);
        }
    }
}

// Concrete Observer: User
class User implements Observer {
    private String name;
    
    public User(String name) {
        this.name = name;
    }
    
    @Override
    public void update(float price) {
        System.out.println(name + " received stock price update: " + price);
    }
}

// Client
public class ObserverPatternDemo {
    public static void main(String[] args) {
        Stock stock = new Stock();
        User user1 = new User("Alice");
        User user2 = new User("Bob");
        
        stock.registerObserver(user1);
        stock.registerObserver(user2);
        
        stock.setPrice(100.5f); // Alice and Bob receive the update
        
        stock.setPrice(110.0f); // Alice and Bob receive the update
    }
}
