package fr.factionbedrock.aerialhell.Client.BlockEntityRenderer;

import fr.factionbedrock.aerialhell.BlockEntity.ChestMimicBlockEntity;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellChestVariants;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.state.ChestBlockEntityRenderState;

public class AerialHellChestMimicBlockEntityRenderer extends AerialHellChestBlockEntityRenderer<ChestMimicBlockEntity>
{
	public AerialHellChestMimicBlockEntityRenderer(BlockEntityRendererFactory.Context context) {super(context);}

	@Override protected ChestBlockEntityRenderState.Variant getVariant(ChestMimicBlockEntity blockEntity)
	{
		Block block = blockEntity.getCachedState().getBlock();
		if (block == AerialHellBlocks.AERIAL_TREE_CHEST_MIMIC) {return AerialHellChestVariants.AERIAL_TREE;}
		else if (block == AerialHellBlocks.COPPER_PINE_CHEST_MIMIC) {return AerialHellChestVariants.COPPER_PINE;}
		else if (block == AerialHellBlocks.GOLDEN_BEECH_CHEST_MIMIC) {return AerialHellChestVariants.LAPIS_ROBINIA;}
		else if (block == AerialHellBlocks.SKY_CACTUS_FIBER_CHEST_MIMIC) {return AerialHellChestVariants.STELLAR_JUNGLE_TREE;}
		else {return ChestBlockEntityRenderState.Variant.REGULAR;} //default variant (should never happen)
	}
}
