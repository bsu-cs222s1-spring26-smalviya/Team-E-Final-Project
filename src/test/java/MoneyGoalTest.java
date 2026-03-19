import Model.MoneyGoal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyGoalTest {

    @Test
    public void testGoalCreation() {

        MoneyGoal goal = new MoneyGoal(
                "Vacation",
                1000,
                "6/1/2026"
        );

        assertNotNull(goal);
    }

    @Test
    public void testGoalName() {

        MoneyGoal goal = new MoneyGoal(
                "Vacation",
                1000,
                "6/1/2026"
        );

        assertEquals("Vacation", goal.getGoalName());
    }

    @Test
    public void testTargetAmount() {

        MoneyGoal goal = new MoneyGoal(
                "Vacation",
                1000,
                "6/1/2026"
        );

        assertEquals(1000, goal.getTargetAmount());
    }

    @Test
    public void testDeadline() {

        MoneyGoal goal = new MoneyGoal(
                "Vacation",
                1000,
                "6/1/2026"
        );

        assertEquals("6/1/2026", goal.getDeadline());
    }

    @Test
    public void testCalculateCompletion() {
        MoneyGoal goal = new MoneyGoal("iphone", 2000, "6/18/2026");
        assertEquals(25.0, goal.calculateCompletion(500.0));
    }

    @Test
    public void testCalculateCompletionWithZeroTarget() {
        MoneyGoal goal = new MoneyGoal("Free item",0,"6/18/2026");
        assertEquals(0, goal.calculateCompletion(500.0));
    }
}