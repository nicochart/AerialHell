package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Inventory.Container.AerialHellCraftingContainer;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class AerialHellCraftingTableBlock extends CraftingTableBlock
{
	private static final ITextComponent CONTAINER_NAME = new TranslationTextComponent("container.crafting");

	public AerialHellCraftingTableBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		if (worldIn.isRemote)
		{
			return ActionResultType.SUCCESS;
		}
		else
		{
			SimpleNamedContainerProvider provider = new SimpleNamedContainerProvider((id, inventory,p) -> new AerialHellCraftingContainer(id, inventory, IWorldPosCallable.of(worldIn, pos)),CONTAINER_NAME);
			NetworkHooks.openGui((ServerPlayerEntity) player, provider);
			player.addStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
			return ActionResultType.CONSUME;
		}
	}
}