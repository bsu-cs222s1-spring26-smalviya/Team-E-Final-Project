# Team-E-Final-Project

## Development checklist

### Controller

- The FinanceManager class in the Controller folder is the main entry point and control center of the application.

### View

- The User class in the View folder is responsible for providing user interaction functionality, including all input and
  output behaviors.

### Model

- The Account class in the Model folder encapsulates the user's core data and the actions to manipulate these states.
- The MoneyGoal class in the Model folder encapsulates the user's savings goals and the actions for manipulating this
  data.
- The Transaction class in the Model folder encapsulates user transaction data and the actions for manipulating this
  data.

## Program running and file logic

The program starts running, displays a welcome screen, and prompts the user to enter a username. The program retrieves
the username and checks if the user exists in the local file. If the username does not exist, it's a new user, and the
account opening process begins. If the username exists, it's an existing user, reads the user data, and directly enters
the program's main interface.