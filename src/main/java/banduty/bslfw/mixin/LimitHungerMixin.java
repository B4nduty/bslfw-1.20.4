package banduty.bslfw.mixin;

import banduty.bslfw.BsLFW;
import banduty.bslfw.util.IEntityDataSaver;
import banduty.bslfw.util.LimitHunger;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HungerManager.class)
public class LimitHungerMixin {
    @Shadow private int foodLevel;

    @ModifyArg(method = "update", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(II)I"), index = 1)
    private int bslfw$limitFood(int x) {
        if (BsLFW.CONFIG.common.modifyHungerLimitHungerCookie || LimitHunger.isLimitedHunger((IEntityDataSaver) MinecraftClient.getInstance().player)) {
            return BsLFW.CONFIG.common.getHungerLimit();
        } else return x;
    }

    @Inject(method = "update", at = @At("HEAD"))
    private void bslfw$deleteHungerEffect (PlayerEntity player, CallbackInfo ci) {
        if (foodLevel <= BsLFW.CONFIG.common.getHungerLimit()) {
            if (BsLFW.CONFIG.common.modifyHungerLimitHungerCookie || LimitHunger.isLimitedHunger((IEntityDataSaver) player)) {
                player.removeStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER).getEffectType());
            }
        }
    }
}