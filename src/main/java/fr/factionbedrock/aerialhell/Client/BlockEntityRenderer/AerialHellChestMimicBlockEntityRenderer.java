package fr.factionbedrock.aerialhell.Client.BlockEntityRenderer;

import fr.factionbedrock.aerialhell.BlockEntity.ChestMimicBlockEntity;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellChestVariants;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.ChestRenderState;
import net.minecraft.world.level.block.Block;

public class AerialHellChestMimicBlockEntityRenderer extends AerialHellChestBlockEntityRenderer<ChestMimicBlockEntity>
{
	public AerialHellChestMimicBlockEntityRenderer(BlockEntityRendererProvider.Context context) {super(context);}

	@Override protected ChestRenderState.ChestMaterialType getVariant(ChestMimicBlockEntity blockEntity)
	{
		Block block = blockEntity.getBlockState().getBlock();
		if (block == AerialHellBlocks.AERIAL_TREE_CHEST_MIMIC) {return AerialHellChestVariants.AERIAL_TREE;}
		else if (block == AerialHellBlocks.COPPER_PINE_CHEST_MIMIC) {return AerialHellChestVariants.COPPER_PINE;}
		else if (block == AerialHellBlocks.GOLDEN_BEECH_CHEST_MIMIC) {return AerialHellChestVariants.LAPIS_ROBINIA;}
		else if (block == AerialHellBlocks.SKY_CACTUS_FIBER_CHEST_MIMIC) {return AerialHellChestVariants.STELLAR_JUNGLE_TREE;}
		else {return ChestRenderState.ChestMaterialType.REGULAR;} //default variant (should never happen)
	}
}
