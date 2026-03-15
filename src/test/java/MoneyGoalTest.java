import Model.MoneyGoal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class MoneyGoalTest {

    @Test
    public void testGoalCreation() {

        MoneyGoal goal = new MoneyGoal(
                "Vacation",
                1000,
                LocalDate.of(2026,6,1)
        );

        assertEquals("Vacation", goal.getGoalName());
        assertEquals(1000, goal.getTargetAmount());
        assertEquals(0.0, goal.getCurrentAmount());
    }

    @Test
    public void testAddProgress() {

        MoneyGoal goal = new MoneyGoal(
                "Vacation",
                1000,
                LocalDate.of(2026,6,1)
        );

        goal.addProgress(200);

        assertEquals(200, goal.getCurrentAmount());
    }

    @Test
    public void testCompletionPercentage() {

        MoneyGoal goal = new MoneyGoal(
                "Vacation",
                1000,
                LocalDate.of(2026,6,1)
        );

        goal.addProgress(200);

        double percent = goal.getCompletionPercentage();

        assertTrue(percent > 0);
    }
}