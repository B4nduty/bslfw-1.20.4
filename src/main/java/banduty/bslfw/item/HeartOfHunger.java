package banduty.bslfw.item;

import banduty.bslfw.BsLFW;
import banduty.bslfw.sound.ModSounds;
import banduty.bslfw.util.IEntityDataSaver;
import banduty.bslfw.util.LimitReinforced;
import banduty.bslfw.util.LimitHunger;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HeartOfHunger extends Item {
    public HeartOfHunger(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!BsLFW.CONFIG.common.modifyHungerLimitHeartOfHunger) return ItemUsage.consumeHeldItem(world, user, hand); return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 40;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) user.getWorld();
            BlockPos blockPos = user.getBlockPos();
            serverWorld.playSound(null, blockPos, ModSounds.HEART_BEAT, SoundCategory.PLAYERS, 1f, 1f);
        }
        if (!world.isClient() && user instanceof PlayerEntity player) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 0));
            if (!player.isCreative()) stack.decrement(1);
            if (!LimitHunger.isLimitedHunger((IEntityDataSaver) player)) {
                if (!LimitReinforced.isLimitedReinforced((IEntityDataSaver) player)) {
                    if (!player.isCreative()) stack.decrement(1);
                    LimitHunger.setLimitHunger(((IEntityDataSaver) player), true);
                    player.sendMessage(Text.translatable("message.bslfw.heart.limited").formatted(Formatting.LIGHT_PURPLE));
                    return stack;
                }
            }
            if (LimitHunger.isLimitedHunger((IEntityDataSaver) player) || LimitReinforced.isLimitedReinforced((IEntityDataSaver) player)) player.sendMessage(Text.translatable("message.bslfw.heart.unlimited").formatted(Formatting.RED));
            if (LimitHunger.isLimitedHunger((IEntityDataSaver) player)) LimitHunger.setLimitHunger(((IEntityDataSaver) player), false);
            if (LimitReinforced.isLimitedReinforced((IEntityDataSaver) player)) LimitReinforced.setLimitReinforced(((IEntityDataSaver) player), false);
        }
        return stack;
    }
}