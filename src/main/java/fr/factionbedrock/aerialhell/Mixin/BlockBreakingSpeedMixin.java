package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class BlockBreakingSpeedMixin
{
    @Inject(method = "getBlockBreakingSpeed", at = @At("RETURN"), cancellable = true)
    private void onBlockBreaking(BlockState blockstate, CallbackInfoReturnable<Float> callbackInfo)
    {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack selectedItemStack = player.getInventory().getSelectedStack();
        float speed = callbackInfo.getReturnValue();

        //player on fire and mining with any arsonist item
        if (blockstate != null && selectedItemStack.isIn(AerialHellTags.Items.ARSONIST) && player.getFireTicks() > 0) {speed *= 2.0F;}

        //player mining stellar stone with stellar stone breaker
        else if (selectedItemStack.getItem() == AerialHellItems.STELLAR_STONE_BREAKER && blockstate.getBlock() == AerialHellBlocks.STELLAR_STONE) {speed *= 2.0F;}

        //player mining a block that needs lunar tool
        if (blockstate != null && blockstate.isIn(AerialHellTags.Blocks.NEEDS_LUNAR_TOOL))
        {
            if (!BlockHelper.isItemCorrectForHarvesting(blockstate, selectedItemStack.getItem()))
            {
                if (blockstate.isOf(AerialHellBlocks.VOLUCITE_ORE) && !player.getWorld().isClient())
                {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 40, 0));
                }
            }
        }

        if (blockstate != null && blockstate.isOf(AerialHellBlocks.EYE_SHADOW_PINE_LOG) && !EntityHelper.isLivingEntityShadowImmune(player) && !player.isCreative())
        {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 30, 0));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 30, 0));
        }

        if (blockstate != null && blockstate.isIn(AerialHellTags.Blocks.GHOST_BLOCK))
        {
            if (EntityHelper.isImmuneToGhostBlockCollision(player)) {speed = Math.min(speed, 0.1F);}
        }
        callbackInfo.setReturnValue(speed);
    }
}
