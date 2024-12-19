package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Block.DungeonCores.DungeonCoreBlock;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class OnBlockPlacedMixin
{
	//net.minecraft.block.Block.onPlaced
	@Inject(method = "onPlaced", at = @At("HEAD"), cancellable = true)
	private void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack, CallbackInfo callbackInfo)
	{
		if (world.isClient) {return;}

		BlockState blockstate = world.getBlockState(pos);
		if (blockstate.isIn(AerialHellTags.Blocks.DUNGEON_CORES))
		{
			((DungeonCoreBlock) blockstate.getBlock()).setAreaProtected(world, pos, true);
			world.playSound(null, pos, SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 1.0F, 1.0F);
		}
	}
}