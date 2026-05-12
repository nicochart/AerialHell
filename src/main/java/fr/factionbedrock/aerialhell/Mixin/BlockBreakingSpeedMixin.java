package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Item.Ability.MiningUseSituationInfo;
import fr.factionbedrock.aerialhell.Item.AerialHellItem;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.*;
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
        Player itemOwner = (Player) (Object) this;
        ItemStack miningItemStack = itemOwner.getInventory().getSelectedItem();
        float speed = callbackInfo.getReturnValue();

        MutableFloat miningSpeedMultiplier = new MutableFloat(1.0F);
        if (miningItemStack.getItem() instanceof AerialHellItem ahItem)
        {
            ahItem.onMining(miningItemStack, itemOwner, new MiningUseSituationInfo(blockstate, new FieldAccessor<>(miningSpeedMultiplier::get, miningSpeedMultiplier::set)));
        }

        //mining speed multiplier value is changed internally in item abilities (onMining)
        speed = speed * miningSpeedMultiplier.get();

        //player mining a lunar dungeon core as a lunar misleader OR mining a shadow dungeon core as a shadow misleader
        if (blockstate != null && blockstate.is(AerialHellBlocks.LUNATIC_DUNGEON_CORE) && EntityHelper.isLivingEntityMisleadingLunar(itemOwner) || (blockstate.is(AerialHellBlocks.SHADOW_CATACOMBS_DUNGEON_CORE) && EntityHelper.isLivingEntityMisleadingShadow(itemOwner)))
        {
            EntityHelper.applyTraitorEffectTo(itemOwner);
        }

        //player mining a block that needs lunar tool
        if (blockstate != null && blockstate.is(AerialHellTags.Blocks.NEEDS_LUNAR_TOOL))
        {
            if (!BlockHelper.isItemCorrectForHarvesting(blockstate, miningItemStack.getItem()))
            {
                if (blockstate.is(AerialHellBlocks.VOLUCITE_ORE) && !itemOwner.level().isClientSide())
                {
                    itemOwner.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 40, 0));
                }
            }
        }

        if (blockstate != null && blockstate.is(AerialHellBlocks.EYE_SHADOW_PINE_LOG) && !EntityHelper.isLivingEntityShadowImmune(itemOwner) && !itemOwner.isCreative())
        {
            itemOwner.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 30, 0));
            itemOwner.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 30, 0));
        }

        if (blockstate != null && blockstate.is(AerialHellTags.Blocks.GHOST_BLOCK))
        {
            if (EntityHelper.isImmuneToGhostBlockCollision(itemOwner)) {speed = Math.min(speed, 0.1F);}
        }
        callbackInfo.setReturnValue(speed);
    }
}
