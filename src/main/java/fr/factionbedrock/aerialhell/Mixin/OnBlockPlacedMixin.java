package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Block.DungeonCores.DungeonCoreBlock;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class OnBlockPlacedMixin
{
	//net.minecraft.block.Block.onPlaced
	@Inject(method = "setPlacedBy", at = @At("HEAD"), cancellable = true)
	private void onBlockPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack, CallbackInfo callbackInfo)
	{
		if (world.isClientSide()) {return;}

		BlockState blockstate = world.getBlockState(pos);
		if (blockstate.is(AerialHellTags.Blocks.DUNGEON_CORES))
		{
			((DungeonCoreBlock) blockstate.getBlock()).setAreaProtected(world, pos, true);
			world.playSound(null, pos, SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 1.0F, 1.0F);
		}
	}
}