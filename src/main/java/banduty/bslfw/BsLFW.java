package banduty.bslfw;

import banduty.bslfw.config.ModConfigs;
import banduty.bslfw.item.ModItems;
import banduty.bslfw.util.LootTableModifier;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BsLFW implements ModInitializer {
	public static final String MOD_ID = "bslfw";
	public static final Logger LOGGER = LoggerFactory.getLogger("bslfw");
	public static ModConfigs CONFIG;

	@Override
	public void onInitialize() {
		AutoConfig.register(ModConfigs.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
		CONFIG = AutoConfig.getConfigHolder(ModConfigs.class).getConfig();

		ModItems.registerModItems();
		LootTableModifier.modifyLootTables();
	}
}