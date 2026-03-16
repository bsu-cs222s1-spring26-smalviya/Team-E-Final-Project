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

        assertEquals("Vacation", goal.getGoalName());
        assertEquals(1000, goal.getTargetAmount());
    }

    @Test
    public void testAddProgress() {

        MoneyGoal goal = new MoneyGoal(
                "Vacation",
                1000,
                "6/1/2026"
        );




    }

    @Test
    public void testCompletionPercentage() {

        MoneyGoal goal = new MoneyGoal(
                "Vacation",
                1000,
                "6/1/2026"
        );






    }
}