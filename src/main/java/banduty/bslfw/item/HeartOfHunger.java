package banduty.bslfw.item;

import banduty.bslfw.util.IEntityDataSaver;
import banduty.bslfw.util.LimitHunger;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class HeartOfHunger extends Item {
    public HeartOfHunger(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 100;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 0));
        if (!world.isClient() && user instanceof PlayerEntity player && !LimitHunger.isLimitedHunger((IEntityDataSaver) user)) {
            if (!player.isCreative()) stack.decrement(1);
            LimitHunger.setLimitHunger(((IEntityDataSaver) user), true);
            user.sendMessage(Text.translatable("message.bslfw.heart_of_hunger.limited").formatted(Formatting.LIGHT_PURPLE));
        } else {
            if (!world.isClient() && user instanceof PlayerEntity player && LimitHunger.isLimitedHunger((IEntityDataSaver) user)) {
                if (!player.isCreative()) stack.decrement(1);
                LimitHunger.setLimitHunger(((IEntityDataSaver) user), false);
                user.sendMessage(Text.translatable("message.bslfw.heart_of_hunger.unlimited").formatted(Formatting.RED));
            }
        }
        return stack;
    }
}
