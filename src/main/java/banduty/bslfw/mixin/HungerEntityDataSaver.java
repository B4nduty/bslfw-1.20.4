package banduty.bslfw.mixin;

import banduty.bslfw.util.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.jetbrains.annotations.Nullable;

@Mixin(PlayerEntity.class)
public abstract class HungerEntityDataSaver extends Entity implements IEntityDataSaver {

    public HungerEntityDataSaver(EntityType<?> type, World world) {
        super(type, world);
    }

    @Unique
    private boolean limitHunger = false;
    @Unique
    private boolean limitReinforced = false;

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfo ci) {
        if (limitHunger) {
            nbt.putBoolean("limitHunger", true);
        }
        if (limitReinforced) {
            nbt.putBoolean("limitReinforced", true);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("limitHunger")) {
            limitHunger = nbt.getBoolean("limitHunger");
        }
        if (nbt.contains("limitReinforced")) {
            limitReinforced = nbt.getBoolean("limitReinforced");
        }
    }

    @Unique
    private static final TrackedData<Boolean> HUNGER_LIMIT_BOOLEAN = DataTracker.registerData(HungerEntityDataSaver.class,
            TrackedDataHandlerRegistry.BOOLEAN);

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void bslfw$injectDataTracker(CallbackInfo ci) {
        this.dataTracker.startTracking(HUNGER_LIMIT_BOOLEAN, false);
    }

    @Override
    public void bslfw$setLimitHunger(@Nullable Boolean hungerLimitBoolean) {
        this.dataTracker.set(HUNGER_LIMIT_BOOLEAN, hungerLimitBoolean);
    }

    @Override
    public boolean bslfw$isLimitedHunger() {
        return this.dataTracker.get(HUNGER_LIMIT_BOOLEAN);
    }

    @Override
    public void bslfw$setLimitReinforced(@Nullable Boolean hungerLimitBoolean) {
        this.dataTracker.set(HUNGER_LIMIT_BOOLEAN, hungerLimitBoolean);
    }

    @Override
    public boolean bslfw$isLimitedReinforced() {
        return this.dataTracker.get(HUNGER_LIMIT_BOOLEAN);
    }
}