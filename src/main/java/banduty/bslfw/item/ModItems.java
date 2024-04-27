package banduty.bslfw.item;

import banduty.bslfw.BsLFW;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item HEART_OF_HUNGER = registerItem("heart_of_hunger",
            new HeartOfHunger(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1)));

    private static void addItemsToFoodAndDrinkItemGroup(FabricItemGroupEntries entries) {
        entries.add(HEART_OF_HUNGER);
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BsLFW.MOD_ID, name), item);
    }
    public static void registerModItems() {
        BsLFW.LOGGER.info("Registering Mod Items for " + BsLFW.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addItemsToFoodAndDrinkItemGroup);
    }
}
