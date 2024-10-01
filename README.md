# Design Patterns in Java & Astronaut Daily Schedule Organizer

## Exercise 1: Design Patterns in Java

### 1. Behavioral Design Patterns

#### 1.1 Command Pattern: Remote Control System
**File:** `CommandPatternDemo.java`

A remote control is used to turn a light on and off, with each action represented as a command.

#### 1.2 Observer Pattern: Stock Price Updates
**File:** `ObserverPatternDemo.java`

Users are notified of stock price changes through the observer pattern.

### 2. Creational Design Patterns

#### 2.1 Singleton Pattern: Database Connection
**File:** `SingletonPatternDemo.java`

Ensures a single instance of a database connection.

#### 2.2 Factory Pattern: Vehicle Factory
**File:** `FactoryPatternDemo.java`

Creates vehicles (car, bike) based on user input.

### 3. Structural Design Patterns

#### 3.1 Adapter Pattern: Media Player
**File:** `AdapterPatternDemo.java`

Adapts a media player to support MP4 files in addition to MP3.

#### 3.2 Decorator Pattern: Coffee Shop
**File:** `DecoratorPatternDemo.java`

Adds ingredients like milk and sugar to a coffee using decorators.

---

## Exercise 2: Astronaut Daily Schedule Organizer

### Key Functionalities
- **Add Task**: Create tasks with description, time, and priority.
- **Remove Task**: Delete tasks.
- **View Tasks**: View tasks sorted by start time.
- **Task Conflict**: Prevent overlapping tasks.

### Design Patterns Used
1. **Singleton**: Single `ScheduleManager` instance for task management.
2. **Factory**: `TaskFactory` for creating task objects.
3. **Observer**: Alerts for task conflicts.

### Example Commands
- **Add Task**: `Add Task("Morning Exercise", "07:00", "08:00", "High")`
- **Remove Task**: `Remove Task("Morning Exercise")`
- **View Tasks**: View tasks sorted by start time.
