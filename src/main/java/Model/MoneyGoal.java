package Model;

public class MoneyGoal {
    private String goalName;
    private double targetAmount;
    private String deadline;

    public MoneyGoal(String goalName, double targetAmount, String deadline) {
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.deadline = deadline;
    }

    public String getGoalName() {
        return goalName;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public String getDeadline() {
        return deadline;
    }

    public String toString() {
        return "Goal: " + goalName + "\nTarget Amount: " + targetAmount + "\nDeadline: " + deadline;
    }

    public double calculateCompletion(double currentBalance) {
        if (targetAmount <= 0) {
            return 0.0;
        }
        return (currentBalance / targetAmount) * 100.0;
    }
}
