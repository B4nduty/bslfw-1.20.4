package banduty.bslfw.util;

import org.jetbrains.annotations.Nullable;

public interface IEntityDataSaver {
    void bslfw$setLimitHunger(@Nullable Boolean hungerLimitBoolean);

    boolean bslfw$isLimitedHunger();

    void bslfw$setLimitReinforced(@Nullable Boolean hungerLimitBoolean);

    boolean bslfw$isLimitedReinforced();
}
