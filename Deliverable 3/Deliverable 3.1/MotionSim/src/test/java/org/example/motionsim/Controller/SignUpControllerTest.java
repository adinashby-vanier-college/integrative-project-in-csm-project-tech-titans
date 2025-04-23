package org.example.motionsim.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.File;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;

public class SignUpControllerTest extends ApplicationTest {
    private SignUpController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/motionsim/SignUpScreen.fxml"));
        AnchorPane root = fxmlLoader.load();
        controller = fxmlLoader.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @BeforeEach
    void setUp() throws Exception {
        File file = new File("Data/users.json");
        if (file.exists()) file.delete();
        new File("Data").mkdirs();
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("[]");
        }
    }

    @Test
    void testEmptyFieldShowsError() {
        clickOn("#createAccBtn");
        Text problem = lookup("#problemMsg").query();
        assertEquals("Please fill in both information boxes.", problem.getText());
    }

    @Test
    void testNewAccountSaved() {
        clickOn("#emailField").write("test@gmail.com");
        clickOn("#passwordField").write("pass1234");
        clickOn("#createAccBtn");

        File file = new File("Data/users.json");
        assertTrue(file.exists());
    }

    @Test
    void testDuplicateEmailShowsError() throws Exception {
        File file = new File("Data/users.json");
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("[{\\\"email\\\": \\\"test@example.com\\\", \\\"password\\\": \\\"1234\\\"}]");
        }

        interact(() -> controller.initialize(null, null));

        clickOn("#emailField").write("test@gmail.com");
        clickOn("#passwordField").write("anotherpass");
        clickOn("#createAccBtn");

        Text problem = lookup("problemMsg").query();
        assertEquals("This email is already registered to an existing account.", problem.getText());
    }

    @Test
    void testPasswordToggleSwitching() {
        assertTrue(controller.getPasswordField().isVisible());
        assertFalse(controller.getVisiblePasswordField().isVisible());

        // visible
        clickOn("#hidePasswordBtn");
        assertFalse(controller.getPasswordField().isVisible());
        assertTrue(controller.getVisiblePasswordField().isVisible());

        // back to invisible
        clickOn("#hidePasswordBtn");
        assertTrue(controller.getPasswordField().isVisible());
        assertFalse(controller.getVisiblePasswordField().isVisible());
    }
}
