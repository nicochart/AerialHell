package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class BlockBreakingSpeedMixin
{
    @Inject(method = "getDestroySpeed", at = @At("RETURN"), cancellable = true)
    private void onBlockBreaking(BlockState blockstate, CallbackInfoReturnable<Float> callbackInfo)
    {
        Player player = (Player) (Object) this;
        ItemStack selectedItemStack = player.getInventory().getSelectedItem();
        float speed = callbackInfo.getReturnValue();

        //player on fire and mining with any arsonist item
        if (blockstate != null && selectedItemStack.is(AerialHellTags.Items.ARSONIST) && player.getRemainingFireTicks() > 0) {speed *= 2.0F;}

        //player mining stellar stone with stellar stone breaker
        else if (selectedItemStack.getItem() == AerialHellItems.STELLAR_STONE_BREAKER && blockstate.getBlock() == AerialHellBlocks.STELLAR_STONE) {speed *= 2.0F;}

        //player mining a block that needs lunar tool
        if (blockstate != null && blockstate.is(AerialHellTags.Blocks.NEEDS_LUNAR_TOOL))
        {
            if (!BlockHelper.isItemCorrectForHarvesting(blockstate, selectedItemStack.getItem()))
            {
                if (blockstate.is(AerialHellBlocks.VOLUCITE_ORE) && !player.level().isClientSide())
                {
                    player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 40, 0));
                }
            }
        }

        if (blockstate != null && blockstate.is(AerialHellBlocks.EYE_SHADOW_PINE_LOG) && !EntityHelper.isLivingEntityShadowImmune(player) && !player.isCreative())
        {
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 30, 0));
            player.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 30, 0));
        }

        if (blockstate != null && blockstate.is(AerialHellTags.Blocks.GHOST_BLOCK))
        {
            if (EntityHelper.isImmuneToGhostBlockCollision(player)) {speed = Math.min(speed, 0.1F);}
        }
        callbackInfo.setReturnValue(speed);
    }
}
