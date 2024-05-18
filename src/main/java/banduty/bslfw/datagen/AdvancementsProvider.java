package banduty.bslfw.datagen;

import banduty.bslfw.BsLFW;
import banduty.bslfw.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class AdvancementsProvider extends FabricAdvancementProvider {
    public AdvancementsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<AdvancementEntry> consumer) {
        AdvancementEntry heartOfHungerAdvancement = Advancement.Builder.create()
                .display(
                        ModItems.HEART_OF_HUNGER,
                        Text.translatable("advancement.heart_of_hunger.title"),
                        Text.translatable("advancement.heart_of_hunger.desc"),
                        new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .criterion("get_heart_of_hunger", InventoryChangedCriterion.Conditions.items(ModItems.HEART_OF_HUNGER))
                .criterion("get_reinforced_heart", InventoryChangedCriterion.Conditions.items(ModItems.REINFORCED_HEART))
                .build(consumer, BsLFW.MOD_ID + "/get_heart_of_hunger");
    }
}
