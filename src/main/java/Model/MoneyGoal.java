package Model;

import java.time.LocalDate;

public class MoneyGoal {
    private String goalName;
    private double targetAmount;
    private double currentAmount;
    private LocalDate deadline;

    public MoneyGoal(String goalName, double targetAmount, LocalDate deadline) {
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.deadline = deadline;
        this.currentAmount = 0.0;
    }

    public void addProgress(double amount) {
        this.currentAmount += amount;
    }

    public String getGoalName() {
        return goalName;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public String getDeadline() {
        return deadline.toString();
    }

    public double getCompletionPercentage() {
        if (targetAmount == 0.0) {
            return 0.0;
        }
        return targetAmount * 100.0 / currentAmount;
    }
}
