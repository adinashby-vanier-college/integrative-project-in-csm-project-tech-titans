package org.example.motionsim.Model;

public final class GameSettings {
    public enum Difficulty {
        EASY, NORMAL, HARD
    }

    private static Difficulty difficulty = Difficulty.NORMAL;

    private GameSettings() {}

    public static Difficulty getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(Difficulty d) {
        difficulty = d;
    }
}
