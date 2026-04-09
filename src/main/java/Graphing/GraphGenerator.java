package Graphing;

import Model.Account;
import Model.DataStorage;
import Model.Transaction;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;

public class GraphGenerator {
    public static void generateCharts(String path) throws Exception {
        Account account;

        try {
            account = DataStorage.readUser(path);
        } catch (Exception e) {

            throw new JsonLoadException("json loading failed");
        }

        if (account == null) {
            throw new AccountDataException("account info is empty");
        }
        if (account.getTransactionList() == null) {
            throw new AccountDataException("transaction history null");
        }

        Map<String, Double> incomeMap = new HashMap<>();
        Map<String, Double> expenseMap = new HashMap<>();


        List<Transaction> transactions = account.getTransactionList();

        for (Transaction t : transactions) {
            if (t == null) {
                throw new AccountDataException("transaction info missing");
            }
            String category = t.getDescription();

            if (t.getAmount() >= 0) {

                incomeMap.put(category,
                        incomeMap.getOrDefault(category, 0.0) + t.getAmount());
            } else {

                expenseMap.put(category,
                        expenseMap.getOrDefault(category, 0.0) + Math.abs(t.getAmount()));
            }
        }
        String expenseChartFileName = "data/" + account.getUsername() + "-expense_chart.png";
        String incomeChartFileName = "data/" + account.getUsername() + "-income_chart.png";
        try {
            savePieChart(incomeMap, "Income", incomeChartFileName);
            savePieChart(expenseMap, "Expense", expenseChartFileName);
        } catch (Exception e) {
            throw new ChartSaveException("fail to save charts");
        }
    }

    private static void savePieChart(Map<String, Double> data, String title, String fileName) throws Exception {

        int width = 600, height = 400;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double total = data.values().stream().mapToDouble(Double::doubleValue).sum();

        double startAngle = 0;
        int i = 0;

        Color[] colors = {
                Color.RED, Color.BLUE, Color.GREEN,
                Color.ORANGE, Color.MAGENTA, Color.CYAN
        };


        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString(title, 250, 30);

        int x = 150, y = 80, diameter = 200;
        int centerX = x + diameter / 2;
        int centerY = y + diameter / 2;
        int radius = diameter / 2;

        for (Map.Entry<String, Double> entry : data.entrySet()) {

            double value = entry.getValue();
            String category = entry.getKey();

            double angle = value / total * 360;


            if (angle < 3) {
                startAngle += angle;
                continue;
            }


            g.setColor(colors[i % colors.length]);
            g.fillArc(x, y, diameter, diameter,
                    (int) startAngle,
                    (int) Math.round(angle));


            double midAngle = Math.toRadians(-(startAngle + angle / 2.0));


            int edgeX = (int) (centerX + radius * Math.cos(midAngle));
            int edgeY = (int) (centerY + radius * Math.sin(midAngle));


            int labelX = (int) (centerX + radius * 1.3 * Math.cos(midAngle));
            int labelY = (int) (centerY + radius * 1.3 * Math.sin(midAngle));

            boolean isRight = Math.cos(midAngle) >= 0;
            int lineEndX = isRight ? labelX + 10 : labelX - 10;


            g.setColor(Color.BLACK);
            g.drawLine(edgeX, edgeY, labelX, labelY);
            g.drawLine(labelX, labelY, lineEndX, labelY);


            double percent = value / total * 100;
            String label = category + String.format(" (%.1f%%)", percent);

            g.setFont(new Font("Arial", Font.PLAIN, 12));

            if (isRight) {
                g.drawString(label, lineEndX + 5, labelY);
            } else {
                int textWidth = g.getFontMetrics().stringWidth(label);
                g.drawString(label, lineEndX - textWidth - 5, labelY);
            }

            startAngle += angle;
            i++;
        }

        g.dispose();
        ImageIO.write(image, "png", new File(fileName));
    }

}
