package banduty.bslfw.util;

public class LimitCorrupted {
    public static void setLimitCorrupted(IEntityDataSaver player, boolean limitCorrupted) {
        player.bslfw$setLimitCorrupted(limitCorrupted);
    }

    public static boolean isLimitedCorrupted(IEntityDataSaver player) {
        return player.bslfw$isLimitedCorrupted();
    }
}
