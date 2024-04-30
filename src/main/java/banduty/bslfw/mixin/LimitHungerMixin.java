package banduty.bslfw.mixin;

import banduty.bslfw.BsLFW;
import banduty.bslfw.util.IEntityDataSaver;
import banduty.bslfw.util.LimitReinforced;
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
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return x;
        boolean shouldLimitHunger = ((IEntityDataSaver) player).bslfw$isLimitedHunger();
        boolean shouldLimitReinforced = ((IEntityDataSaver) player).bslfw$isLimitedReinforced();
        if (BsLFW.CONFIG.common.modifyHungerLimitHeartOfHunger || shouldLimitHunger || shouldLimitReinforced) {
            return BsLFW.CONFIG.common.getHungerLimit();
        } else return x;
    }

    @Inject(method = "update", at = @At("HEAD"))
    private void bslfw$deleteHungerEffect (PlayerEntity player, CallbackInfo ci) {
        if (foodLevel <= 10 && LimitReinforced.isLimitedReinforced((IEntityDataSaver) player)) {
            player.removeStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER).getEffectType());
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,
                    2, 0, false, false, false));
        }

        if (foodLevel <= BsLFW.CONFIG.common.getHungerLimit() && BsLFW.CONFIG.common.modifyHungerLimitHeartOfHunger) {
            player.removeStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER).getEffectType());
        }
    }
}