package banduty.bslfw.util;

public class LimitHunger {
    public static void setLimitHunger(IEntityDataSaver player, boolean limitHunger) {
        player.bslfw$setLimitHunger(limitHunger);
    }

    public static boolean isLimitedHunger(IEntityDataSaver player) {
        return player.bslfw$isLimitedHunger();
    }
}
