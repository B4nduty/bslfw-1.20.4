package banduty.bslfw.util;

public class LimitReinforced {
    public static void setLimitReinforced(IEntityDataSaver player, boolean limitReinforced) {
        player.bslfw$setLimitReinforced(limitReinforced);
    }

    public static boolean isLimitedReinforced(IEntityDataSaver player) {
        return player.bslfw$isLimitedReinforced();
    }
}
