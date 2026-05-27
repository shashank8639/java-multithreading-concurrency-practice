# java-multithreading-concurrency-practice

Comprehensive Java multithreading examples covering Thread lifecycle, synchronization, inter-thread communication, and advanced concurrency utilities.

## Topics Covered             

### Completed (May26, 2026)
- Thread creation (Thread class, Runnable, Lamda)
- Synchronization & locks
- wait()/notify() patterns
- Thread.join() & Thread.sleep()

## Producer-Consumer Examples

### Basic Version (Completed - May 27, 2026)

| File |  Description  |
| :--- |  :--- |
| `A_ProducerConsumer.java` | Classic wait/notify implementation simple example |
| `B_Queued_Pro_Consumer.java` | Queue-based implementation (preferred for production) |

### Producer-Consumer Examples (Refactored version) (Completed - May 27, 2026)

| File | Description |
| :--- | :--- |
| `SharedBuffer.java` | Shared queue with wait/notify logic |
| `Producer.java` | Producer Java class |
| `Consumer.java` | Consumer Java class |
| `Queued_Producer_Consumer.java` | Main class to run the example |

**Improvement:** Seperated into individual classes for better modularity.

### Coming Soon
- Producer-Consumer problems with Simulation
- MutipleUser simutation

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
