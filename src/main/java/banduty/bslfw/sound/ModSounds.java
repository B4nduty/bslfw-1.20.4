package banduty.bslfw.sound;

import banduty.bslfw.BsLFW;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent HEART_BEAT = registerSoundEvent("heart_beat");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(BsLFW.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    public static void registerSounds() {
        BsLFW.LOGGER.info("Registering Sounds for " + BsLFW.MOD_ID);
    }
}
