package View;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.File;

public class ShowPNG {

    public void showPng(String fileName) {
        File file = new File(fileName);
        Image image = new Image(file.toURI().toString());

        ImageView imageView = new ImageView(image);

        imageView.setPreserveRatio(true);

        StackPane root = new StackPane();
        root.getChildren().add(imageView);

        Stage stage = new Stage();
        stage.setTitle("JavaFX Show PNG");

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }
}
