# Team-E-Final-Project

## Development checklist

### Controller

- The FinanceManager class in the Controller folder is the main entry point and control center of the application.
    - [ ] Case 3 haven't been implemented.
    - Case 3: I don't know how to draw the chart in console.

### View

- The User class in the View folder is responsible for providing user interaction functionality, including all input and
  output behaviors.
    - [ ] If getUserInputInt is not used afterward, it should be deleted.

### Model

- The Account class in the Model folder encapsulates the user's core data and the actions to manipulate these states.
- The MoneyGoal class in the Model folder encapsulates the user's savings goals and the actions for manipulating this
  data.
- The Transaction class in the Model folder encapsulates user transaction data and the actions for manipulating this
  data.
- The DataStorage class in the Model folder encapsulates functions of saving Account to json, reading from json and
  check if one's file is there.

### Test

- I didn't thoroughly check any of the tests; I just made sure they passed. However, I glanced at them and noticed that
  due to some recent changes, some new classes lacked corresponding tests, and some classes with existing tests were
  missing.

## Program running and file logic

The program starts running, displays a welcome screen, and prompts the user to enter a username. The program retrieves
the username and checks if the user exists in the local file.
If the username does not exist, it's a new user, and the account opening process begins.
If the username exists, it's an existing user, reads the user data, and directly enters the program's main interface.
Then the user can select functions such as trading, viewing trading history, viewing financial charts, Currency
Converter,and Manage Money Goals . Selecting
option 6 will exit and automatically save the data.