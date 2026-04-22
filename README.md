# Team-E-Final-Project

## Project Summary

Finance Manager is a robust, console-based personal finance application designed to help users track their daily income
and expenses, manage customized savings goals, and perform real-time currency conversions.

Developed with a strict adherence to the Model-View-Controller (MVC) architectural pattern and Test-Driven Development (
TDD), this project ensures high maintainability and robust data protection. It provides tangible user value by offering
a stable, bug-free environment for personal wealth management, featuring persistent JSON data storage and secure API
integration for live exchange rates.

## Authors

* Ye, Fei (GitHub: AlecFeiYe)
* Zhang, Daniel (GitHub: DanielZhang1129)
* Johnson, Silas (GitHub: LordRaymik/Lord-Raymik)
* Palenske, Alejandro (GitHub: alexsome22)

## Build and Run Instructions

### Prerequisites

* **Java:** Java Development Kit (JDK) 11 or higher installed on your system.
* **Dependencies:** The project utilizes `Gson` for JSON serialization/deserialization and `JUnit 5` for unit testing.

### How to Build and Run

1. **Clone the repository:** Open your terminal and clone the project from the course's GitHub organization:
   ```bash
   git clone https://github.com/bsu-cs222s1-spring26-smalviya/Team-E-Final-Project
   ```
2. Open the project: Open the cloned directory in your preferred Java IDE (such as IntelliJ IDEA or Eclipse).
3. Resolve Dependencies: If you are using a dependency manager (like Gradle), ensure it has successfully synced. If you
   are managing libraries manually, verify that the Gson JAR file is added to your project's build path.|

      Step 4 and 5 will change depending on if you are using the console gui, or the JavaFX gui.

4. Run the Application:  
   [If using the console gui]
    - Navigate to the src/Controller directory.
    - Locate the FinanceManager.java file.
    - Run the main method within FinanceManager.java to launch the application.

   [If using the JavaFX gui]
    - Make sure the project has gradle built properly.
    - Run the program using the gradle 'run' task
5. Usage:  
   [If using the console gui]
      Follow the on-screen console prompts to create a user profile, record transactions, and check your money
      goal progress.  
   [If using the JavaFX gui]
      Login via the initial startup screen, if you dont have an account, it will make a new one for you. From there, navigate through the various menus to record transactions, goals for your money, your balance, as        well as currency conversion rates.
