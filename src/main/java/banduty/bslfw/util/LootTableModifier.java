package banduty.bslfw.util;

import banduty.bslfw.BsLFW;
import banduty.bslfw.config.ModConfigs;
import banduty.bslfw.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class LootTableModifier {
    private static final Identifier COW_ID
            = new Identifier("minecraft", "entities/cow");
    private static final Identifier PIG_ID
            = new Identifier("minecraft", "entities/pig");
    private static final Identifier CHICKEN_ID
            = new Identifier("minecraft", "entities/chicken");
    private static final Identifier SHEEP_ID
            = new Identifier("minecraft", "entities/sheep");

    private static final Identifier WHEAT_ID
            = new Identifier("minecraft", "blocks/wheat");
    private static final Identifier CARROTS_ID
            = new Identifier("minecraft", "blocks/carrots");
    private static final Identifier POTATOES_ID
            = new Identifier("minecraft", "blocks/potatoes");

    public static void modifyLootTables() {
        if (!BsLFW.CONFIG.common.modifyHungerLimitHungerCookie) {
            LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
                if (COW_ID.equals(id) || PIG_ID.equals(id) || CHICKEN_ID.equals(id) || SHEEP_ID.equals(id) || WHEAT_ID.equals(id) ||
                        CARROTS_ID.equals(id) || POTATOES_ID.equals(id)) {
                    LootPool.Builder hungerCookie = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .conditionally(RandomChanceLootCondition.builder(BsLFW.CONFIG.common.getHungerCookieChanceSpawn()))
                            .with(ItemEntry.builder(ModItems.HUNGER_COOKIE))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                    tableBuilder.pool(hungerCookie.build());
                }
            });
        }
    }
}
