package org.example.motionsim.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class IPCSMFXMLGameControllerTest extends ApplicationTest {

    private IPCSMFXMLGameController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/motionsim/IPCSMFXMLGame.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @BeforeEach
    public void setup() {
        interact(() -> {}); // Ensures JavaFX is initialized
    }

    @Test
    public void testUIComponentsExist() {
        // Basic presence checks
        assertNotNull(lookup("#StartBtn").queryButton());
        assertNotNull(lookup("#ResetBtn").queryButton());
        assertNotNull(lookup("#AmplitudeSlider").queryAs(Slider.class));
        assertNotNull(lookup("#AngleSlider").queryAs(Slider.class));
        assertNotNull(lookup("#MassSlider").queryAs(Slider.class));
        assertNotNull(lookup("#VVelocityComboBox").queryComboBox());
        assertNotNull(lookup("#HVelocityComboBox").queryComboBox());
        assertNotNull(lookup("#AnimationPane").queryAs(Pane.class));
        assertNotNull(lookup("#TimeRemainingFieldLabel").queryLabeled());
        assertNotNull(lookup("#PointsFieldLabel").queryLabeled());
    }

    @Test
    public void testStartButtonFunctionality() {
        // Click start button and ensure velocity fields are updated
        clickOn("#StartBtn");

        String vVelocity = lookup("#VVelocityFieldLabel").queryLabeled().getText();
        String hVelocity = lookup("#HVelocityFieldLabel").queryLabeled().getText();

        assertNotNull(vVelocity);
        assertNotNull(hVelocity);
        assertDoesNotThrow(() -> Double.parseDouble(vVelocity));
        assertDoesNotThrow(() -> Double.parseDouble(hVelocity));
    }

    @Test
    public void testResetButtonFunctionality() {
        // Click reset and ensure values are reset
        clickOn("#ResetBtn");

        verifyThat("#VVelocityFieldLabel", hasText("0.00"));
        verifyThat("#HVelocityFieldLabel", hasText("0.00"));
        verifyThat("#TimeRemainingFieldLabel", hasText("180"));
        verifyThat("#PointsFieldLabel", hasText("0"));
    }

    @Test
    public void testComboBoxUnitConversion() throws Exception {
        // Simulate selecting different units and verify no crash
        ComboBox<String> hCombo = lookup("#HVelocityComboBox").queryComboBox();
        ComboBox<String> vCombo = lookup("#VVelocityComboBox").queryComboBox();

        interact(() -> {
            hCombo.getSelectionModel().select("Centimeter(cm/s)");
            vCombo.getSelectionModel().select("Millimeter(mm/s)");
        });

        // Call private methods via reflection
        Method handleH = IPCSMFXMLGameController.class.getDeclaredMethod("handleHVelocityComboBox", javafx.event.ActionEvent.class);
        Method handleV = IPCSMFXMLGameController.class.getDeclaredMethod("handleVVelocityComboBox", javafx.event.ActionEvent.class);
        handleH.setAccessible(true);
        handleV.setAccessible(true);

        interact(() -> {
            try {
                handleH.invoke(controller, (Object) null);
                handleV.invoke(controller, (Object) null);
            } catch (Exception e) {
                fail("Failed to invoke combo box handlers via reflection: " + e.getMessage());
            }
        });

        String vText = lookup("#VVelocityFieldLabel").queryLabeled().getText();
        String hText = lookup("#HVelocityFieldLabel").queryLabeled().getText();
        assertDoesNotThrow(() -> Double.parseDouble(vText));
        assertDoesNotThrow(() -> Double.parseDouble(hText));
    }

    @Test
    public void testAmplitudeSliderAdjustsValue() {
        Slider ampSlider = lookup("#AmplitudeSlider").queryAs(Slider.class);

        interact(() -> ampSlider.setValue(ampSlider.getMax() / 2));
        String labelValue = lookup("#AmplitudeFieldLabel").queryLabeled().getText();

        assertDoesNotThrow(() -> Double.parseDouble(labelValue));
    }
}
