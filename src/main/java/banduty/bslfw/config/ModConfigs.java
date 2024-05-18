package banduty.bslfw.config;

import banduty.bslfw.BsLFW;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = BsLFW.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/oak_planks.png")
public class ModConfigs extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Category("common")
    @ConfigEntry.Gui.TransitiveObject()
    public Common common = new Common();

    @Config(name = BsLFW.MOD_ID + "-common")
    public static final class Common implements ConfigData {
        @ConfigEntry.Gui.Tooltip()
        @Comment("Allow Hunger Limit | Default: false" +
                "If set True, Heart Of Hunger won't Spawn")
        public boolean modifyHungerLimitHeartOfHunger = false;

        @ConfigEntry.Gui.Tooltip()
        @Comment("Allow Reinforced Heart | Default: true" +
                "If modifyHungerLimitHeartOfHunger is True, Corrupted Heart won't Spawn")
        public boolean modifyCorruptedHeart = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""
               Hunger Limit | Default: 6
               """)
        int hungerLimit = 6;
        public int getHungerLimit() {
            return Math.min(20, Math.max(0, hungerLimit));
        }

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""
                Heart Of Hunger Chance of Spawn | Default: 0.0002f
                """)
        float heartOfHungerChanceSpawn = 0.0002f;
        public float getHeartOfHungerChanceSpawn() {
            return Math.max(0, heartOfHungerChanceSpawn);
        }

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""
                Reinforced Heart Chance of Spawn | Default: 0.00002f
                """)
        float reinforcedHeartChanceSpawn = 0.00002f;
        public float getReinforcedHeartChanceSpawn() {
            return Math.max(0, reinforcedHeartChanceSpawn);
        }
    }
}