# java-multithreading-concurrency-practice

Comprehensive Java multithreading examples covering Thread lifecycle, synchronization, inter-thread communication, and advanced concurrency utilities.

## Topics Covered             

### Core Fundamentals (Completed - May 26, 2026)
* Thread creation (Thread class, Runnable, Lambda expressions)
* Synchronization mechanics & intrinsic locks
* Fundamental `wait()` / `notify()` patterns
* Resource management using `Thread.join()` and `Thread.sleep()`

---

## 📦 Producer-Consumer & Task Coordination Examples

### 1. Basic Architecture (Completed - May 27, 2026)
* **Monolithic Approach (`multithreads.pro_consumer.basic`):**
  * `A_ProducerConsumer.java` — Classic wait/notify implementation inside a single monolithic structure.
  * `B_Queued_Pro_Consumer.java` — Inter-thread communications utilizing standard Queue structures.
* **Modular Refactored Approach (`multithreads.pro_consumer.basic_reffactored`):**
  * Decoupled components (`Producer.java`, `Consumer.java`, `SharedBuffer.java`) separating execution logic to maximize code readability and maintainability.

### 2. Timed Task Coordination Simulation (Completed - May 28, 2026)
Located in `multithreads.pro_consumer.ex_2`, this iteration explores state management, defensive looping patterns, and temporal tracking during thread execution blocks.

| File | Description |
| :--- | :--- |
| `TaskHandler.java` | Coordinates production and consumption logic, safeguarding threads with a `while` conditional check loop against spurious wakeups, utilizing `wait(timeout)`. |
| `TaskRunner.java` | Runnable engine simulating a manager context constantly checking task status metrics. |
| `Waiter.java` | Runnable worker thread checking operational flags and executing under explicit timed locks. |
| `WaiterTask_Main.java` | Driver entry point orchestrating resource threads and validating latency time overhead. |

### ⏳ Coming Soon
* Complex real-world domain multi-thread simulations (Chef & Waiter interaction models)
* Networked Multi-User concurrent TCP chat servers utilizing Socket structures
## Why this repo?
- Clean, documented code with beginner-friendly explanations
- Practical examples demonstating thread coordination
- Ready-to-run scenarios for interview practice

## Tech Stack
- Java 8

## How to Run
```bash
javac multithreads/basics/*.java
java multithreads.basics.B_UseThread
