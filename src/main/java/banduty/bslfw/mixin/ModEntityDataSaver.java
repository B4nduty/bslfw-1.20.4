package banduty.bslfw.mixin;

import banduty.bslfw.util.IEntityDataSaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class ModEntityDataSaver implements IEntityDataSaver {
    @Unique
    private boolean limitHunger = false;
    @Unique
    private boolean limitCorrupted = false;
    @Override
    public boolean bslfw$isLimitedHunger() {
        return limitHunger;
    }
    @Override
    public boolean bslfw$isLimitedCorrupted() {
        return limitCorrupted;
    }

    @Override
    public void bslfw$setLimitHunger(boolean limitHunger) {
        this.limitHunger = limitHunger;
    }

    @Override
    public void bslfw$setLimitCorrupted(boolean limitCorrupted) {
        this.limitCorrupted = limitCorrupted;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfo ci) {
        if (limitHunger) {
            nbt.putBoolean("LimitHunger", true);
        }
        if (limitCorrupted) {
            nbt.putBoolean("limitCorrupted", true);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("LimitHunger")) {
            limitHunger = nbt.getBoolean("LimitHunger");
        }
        if (nbt.contains("limitCorrupted")) {
            limitCorrupted = nbt.getBoolean("limitCorrupted");
        }
    }
}
