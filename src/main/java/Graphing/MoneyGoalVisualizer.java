package Graphing;

import Model.Account;
import Model.MoneyGoal;

public class MoneyGoalVisualizer {
    public String generateGoalBar(Account account, MoneyGoal goal){

        if(account == null||goal == null){
            throw new NullArgumentException("account or money goal doesn't exist");
        }
        double balance = account.getBalance();
        double target = goal.targetAmount();
        if(balance <0 || target<0){
            throw new NegativeAmountException("balance or target is negative");
        }

        double progress = balance/target;
        progress = Math.min(progress, 1.0);

        int percent = (int) (progress * 100);


        int barLength = 30;

        int filled = (int) (barLength * progress);
        int empty = barLength - filled;


        StringBuilder bar = new StringBuilder();

        bar.append("Goal Progress\n");
        bar.append("[");

        for (int i = 0; i < filled; i++) {
            bar.append("#");
        }
        for (int i = 0; i < empty; i++) {
            bar.append("-");
        }

        bar.append("] ");

        bar.append(percent).append("%\n");

        bar.append(String.format("Balance: %.2f / Goal: %.2f",
                balance, target));

        return bar.toString();
    }
}
