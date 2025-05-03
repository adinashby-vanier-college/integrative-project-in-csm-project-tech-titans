/*
package org.example.motionsim.Model;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class ThemeUtil {

    /**
     * Applies a background image to the given Pane using the specified image path.
     *
     * @param pane      The Pane to which the background image should be applied.
     * @param imagePath The path to the image file (e.g., "file:wallpaper/DEFAULT.png").
     */
/*    public static void applyBackgroundImage(Pane pane, String imagePath) {
        Image image = new Image(imagePath);
        BackgroundSize backgroundSize = new BackgroundSize(
                BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true
        );
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize
        );
        pane.setBackground(new Background(backgroundImage));
    }
}

 */

package org.example.motionsim.Model;
/*
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class ThemeUtil {

    public enum Theme {
        DEFAULT("file:wallpaper/DEFAULT.png"),
        LIGHT("file:wallpaper/LIGHT.jpg"),
        DARK("file:wallpaper/dark.jpg");

        private final String path;

        Theme(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }

    private static Theme currentTheme = Theme.DEFAULT;

    public static void setTheme(Theme theme) {
        currentTheme = theme;
    }

    public static Theme getTheme() {
        return currentTheme;
    }

    public static void applyBackground(Pane pane) {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(currentTheme.getPath()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        pane.setBackground(new Background(backgroundImage));
    }
}

 */
/*
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class ThemeUtil {

    public enum Theme {
        DEFAULT,
        LIGHT,
        DARK
    }

    private static Theme currentTheme = Theme.DEFAULT;

    public static void setTheme(Theme theme) {
        currentTheme = theme;
    }

    public static Theme getTheme() {
        return currentTheme;
    }

    public static void applyBackground(Pane pane) {
        String imagePath;
        switch (currentTheme) {
            case DARK:
                imagePath = "/wallpapers/dark.jpg";
                break;
            case LIGHT:
                imagePath = "/wallpapers/LIGHT.jpg";
                break;
            case DEFAULT:
            default:
                imagePath = "/wallpapers/DEFAULT.png";
                break;
        }

        Image image = new Image(ThemeUtil.class.getResourceAsStream(imagePath));
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
        );
        pane.setBackground(new Background(backgroundImage));
    }
}

 */
/*
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import java.util.Objects;

public class ThemeUtil {

    public enum AppTheme {
        DEFAULT("/wallpapers/DEFAULT.png"),
        LIGHT("/wallpapers/LIGHT.jpg"),
        DARK("/wallpapers/dark.jpg");

        private final String path;

        AppTheme(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }

    private static AppTheme currentTheme = AppTheme.DEFAULT;

    public static void setTheme(AppTheme theme) {
        currentTheme = theme;
    }

    public static AppTheme getCurrentTheme() {
        return currentTheme;
    }

    public static void applyThemeToPane(Pane pane) {
        Image image = new Image(Objects.requireNonNull(ThemeUtil.class.getResource(currentTheme.getPath())).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        pane.setBackground(new Background(backgroundImage));
    }
}



import javafx.scene.image.Image;

public class ThemeUtil {

    public enum AppTheme {
        DEFAULT("/wallpapers/DEFAULT.png"),
        LIGHT("/wallpapers/LIGHT.jpg"),
        DARK("/wallpapers/dark.jpg");

        private final String path;

        AppTheme(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }

    private static AppTheme currentTheme = AppTheme.DEFAULT;

    public static void setTheme(AppTheme theme) {
        currentTheme = theme;
    }

    public static AppTheme getCurrentTheme() {
        return currentTheme;
    }

    public static String getWallpaperPath() {
        return currentTheme.getPath();
    }

    public static Image getCurrentBackgroundImage() {
        return new Image(ThemeUtil.class.getResourceAsStream(getWallpaperPath()));
    }
}


 */
/*
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.util.Objects;
public class ThemeUtil {
    /*
    public class ThemeUtil {

        public enum AppTheme {
            DEFAULT("/wallpapers/DEFAULT.png"),
            LIGHT("/wallpapers/LIGHT.jpg"),
            DARK("/wallpapers/dark.jpg");

            private final String path;

            AppTheme(String path) {
                this.path = path;
            }

            public String getPath() {
                return path;
            }
        }

        private static AppTheme currentTheme = AppTheme.DEFAULT;

        public static void setTheme(AppTheme theme) {
            currentTheme = theme;
        }

        public static AppTheme getCurrentTheme() {
            return currentTheme;
        }
        public static String getWallpaperPath() {
            return currentTheme.getPath();
        }

        public static void applyThemeToPane(Pane pane) {
            Image image = new Image(Objects.requireNonNull(ThemeUtil.class.getResource(currentTheme.getPath())).toExternalForm());
            BackgroundImage backgroundImage = new BackgroundImage(
                    image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
            );
            pane.setBackground(new Background(backgroundImage));
        }
    }
    public enum AppTheme {
        DEFAULT("/wallpapers/DEFAULT.png"),
        LIGHT("/wallpapers/LIGHT.jpg"),
        DARK("/wallpapers/dark.jpg");

        private final String path;

        AppTheme(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }

    private static AppTheme currentTheme = AppTheme.DEFAULT;

    public static void setTheme(AppTheme theme) {
        currentTheme = theme;
    }

    public static AppTheme getCurrentTheme() {
        return currentTheme;
    }

    public static String getWallpaperPath() {
        return currentTheme.getPath();
    }

    public static void applyThemeToImageView(ImageView imageView) {
        Image image = new Image(Objects.requireNonNull(ThemeUtil.class.getResourceAsStream(currentTheme.getPath())));
        imageView.setImage(image);
    }
}

 */

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import java.util.Objects;

public class ThemeUtil {

    public enum AppTheme {
        DEFAULT("/wallpapers/DEFAULT.png"),
        LIGHT("/wallpapers/LIGHT.jpg"),
        DARK("/wallpapers/dark.jpg");

        private final String path;

        AppTheme(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }

    private static AppTheme currentTheme = AppTheme.DEFAULT;

    public static void setTheme(AppTheme theme) {
        currentTheme = theme;
    }

    public static AppTheme getCurrentTheme() {
        return currentTheme;
    }

    public static String getWallpaperPath() {
        return currentTheme.getPath();
    }

    public static void applyThemeToPane(Pane pane) {
        try {
            Image image = new Image(Objects.requireNonNull(
                    ThemeUtil.class.getResourceAsStream(currentTheme.getPath())
            ));
            BackgroundImage backgroundImage = new BackgroundImage(
                    image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(
                            BackgroundSize.AUTO, BackgroundSize.AUTO,
                            false, false, true, true
                    )
            );
            pane.setBackground(new Background(backgroundImage));
        } catch (Exception e) {
            System.err.println("Error applying theme to pane: " + currentTheme.getPath());
            e.printStackTrace();
        }
    }
}


