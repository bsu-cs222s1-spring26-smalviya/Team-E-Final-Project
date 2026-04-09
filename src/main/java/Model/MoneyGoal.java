package Model;

public record MoneyGoal(String goalName, double targetAmount, String deadline) {
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
