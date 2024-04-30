package banduty.bslfw;

import banduty.bslfw.client.HungerHudOverlay;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class BsLFWClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new HungerHudOverlay());
    }
}
