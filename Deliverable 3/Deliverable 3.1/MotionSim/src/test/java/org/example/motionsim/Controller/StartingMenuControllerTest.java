package org.example.motionsim.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isNull;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class StartingMenuControllerTest extends ApplicationTest {
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/motionsim/StartingMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        this.stage = stage;
    }

    @BeforeEach
    public void setUp() {
        interact(() -> {
            if (!stage.isShowing()) {
                stage.show();
            }
        });
    }

    @Test
    public void testGoToSimScene() {
        verifyThat("#startButton", isVisible());
        clickOn("#startButton");
        WaitForAsyncUtils.waitForFxEvents();
        verifyThat("#startButton", isNull());
        verifyThat("#settingsButton", isNull());
    }
}
