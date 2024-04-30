package banduty.bslfw.client;

import banduty.bslfw.BsLFW;
import banduty.bslfw.util.IEntityDataSaver;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class HungerHudOverlay implements HudRenderCallback {
    private static final Identifier HUNGER_NO_LIMITED = new Identifier(BsLFW.MOD_ID, "textures/hunger/hunger_no_limited.png");
    private static final Identifier HUNGER_LIMITED = new Identifier(BsLFW.MOD_ID, "textures/hunger/hunger_limited.png");

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        int x = 0;
        int y = 0;
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;
        boolean shouldLimitHunger = ((IEntityDataSaver) player).bslfw$isLimitedHunger();
        boolean shouldLimitReinforced = ((IEntityDataSaver) player).bslfw$isLimitedReinforced();
        if (BsLFW.CONFIG.common.modifyHungerLimitHeartOfHunger || shouldLimitHunger ||
                shouldLimitReinforced) {
            if (!player.isCreative()) {
                MinecraftClient client = MinecraftClient.getInstance();
                if (client != null) {
                    int width = client.getWindow().getScaledWidth();
                    int height = client.getWindow().getScaledHeight();

                    x = width / 2;
                    y = height;
                }

                RenderSystem.setShader(GameRenderer::getPositionTexProgram);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.setShaderTexture(0, HUNGER_NO_LIMITED);
                for(int i = 0; i < 10; i++) {
                    drawContext.drawTexture(HUNGER_NO_LIMITED,x + 82 - (i * 8),y - 39,0,0,9,9,
                            9,9);
                }

                RenderSystem.setShaderTexture(0, HUNGER_LIMITED);
                for(int i = 0; i < 10; i++) {
                    if(BsLFW.CONFIG.common.getHungerLimit()/2 > i) {
                        drawContext.drawTexture(HUNGER_LIMITED,x + 82 - (i * 8),y - 39,0,0,9,9,
                                9,9);
                    } else {
                        break;
                    }
                }
            }
        }
    }
}