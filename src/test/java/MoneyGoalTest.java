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
}