package org.example.motionsim.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxToolkit.setupFixture;

public class LoginControllerTest extends ApplicationTest {
    private LoginController controller;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        loadLoginScreen();
    }

    private void loadLoginScreen() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/motionsim/LoginScreen.fxml"));
        AnchorPane root = loader.load();
        controller = loader.getController();

        if (stage.getScene() == null) {
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            stage.getScene().setRoot(root);
        }
    }

    @BeforeEach
    void prepareCleanUserFile() throws Exception {
        File file = new File("Data/users.json");
        Files.createDirectories(file.getParentFile().toPath());
        try (FileWriter fw = new FileWriter(file, false)) {
            fw.write("[{\"email\":\"user@example.com\",\"password\":\"pass123\"}]");
        }
        interact(() -> {
            try {
                loadLoginScreen();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @AfterEach
    void cleanup() throws Exception {
        File file  = new File("Data/users.json");
        if (file.exists()) file.delete();
    }

    @Test
    void testLoadUserFromJsonWhenFileMissing() throws Exception {
        new File("Data/users.json").delete();

        interact(() -> {
            try {
                loadLoginScreen();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        sleep(2000);
        clickOn("#emailField").write("user@gmail.com");
        clickOn("#passwordField").write("pass123");
        clickOn("#loginBtn");

        Text msg = lookup("#loginNotSuccessfulMsg").query();
        assertEquals(1.0, msg.getOpacity(), "With no userFile, login must fail.");
    }

//    @Test
//    void testSuccessfulLoginNavigatesToStartingMenu() {
//        sleep(500);
//        clickOn("#emailField").write("user@example.com");
//        clickOn("#passwordField").write("pass123");
//        clickOn("#loginBtn");
//
//        sleep(500);
//        assertTrue(stage.getScene().getRoot() instanceof BorderPane,
//                "Should have navigated to StartingMenu on successful login.");
//    }
}
