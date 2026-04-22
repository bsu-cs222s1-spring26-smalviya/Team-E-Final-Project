import Graphing.*;
import Model.Account;
import Model.MoneyGoal;
import org.junit.jupiter.api.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyGoalVisualizerTest {

    private static MoneyGoalVisualizer visualizer = new MoneyGoalVisualizer();

    @Test
    public void testGoalBarExist () throws Exception {
        MoneyGoal goal = new MoneyGoal("house",10000000,"2006.11.29");
        Account account = new Account("daniel",100);
        assertNotNull(visualizer.generateGoalBar(account,goal));
    }

    @Test
    public void testGoalBarContainsPercentageSign() throws Exception {
        MoneyGoal goal = new MoneyGoal("house",10000000,"2006.11.29");
        Account account = new Account("daniel",100);
        String result = visualizer.generateGoalBar(account, goal);

        assertTrue(result.contains("%"));

    }

    @Test
    public void testGoalBarContainsGoalProgress() throws Exception {
        MoneyGoal goal = new MoneyGoal("house",10000000,"2006.11.29");
        Account account = new Account("daniel",100);
        String result = visualizer.generateGoalBar(account, goal);

        assertTrue(result.contains("Goal Progress"));
    }

    @Test
    public void testNullAccount(){
        Account account = null;
        MoneyGoal goal = new MoneyGoal("house",10000000,"2006.11.29");
        assertThrows(NullArgumentException.class, ()->{
            visualizer.generateGoalBar(account, goal);
        });
    }

    @Test
    public void testNullMoneyGoal(){
        Account account = new Account("daniel",100);
        MoneyGoal goal = null;
        assertThrows(NullArgumentException.class, ()->{
            visualizer.generateGoalBar(account, goal);
        });
    }

    @Test
    public void testInvalidBalance(){
        MoneyGoal goal = new MoneyGoal("house",10000000,"2006.11.29");
        Account account = new Account("daniel",-100);
        assertThrows(NegativeAmountException.class, ()->{
            visualizer.generateGoalBar(account, goal);
        });
    }

    @Test
    public void testInvalidMoneyGoal(){
        MoneyGoal goal = new MoneyGoal("house",-10000000,"2006.11.29");
        Account account = new Account("daniel",100);
        assertThrows(NegativeAmountException.class, ()->{
            visualizer.generateGoalBar(account, goal);
        });
    }

    @Test
    public void testZeroTarget(){
        MoneyGoal goal = new MoneyGoal("house",0,"2006.11.29");
        Account account = new Account("daniel",100);
        assertThrows(DividedByZeroException.class,()->{
            visualizer.generateGoalBar(account, goal);
        });
    }



}
