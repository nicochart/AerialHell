package fr.factionbedrock.aerialhell.Block.DungeonCores;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CoreProtectedTrappedBlock extends CoreProtectedBlock
{
	private static final Random rand = new Random();
	
	public CoreProtectedTrappedBlock(Properties properties)
	{
		super(properties);
		this.setDefaultState(this.getDefaultState().with(CORE_PROTECTED, false));
	}

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entityIn)
	{
		if (entityIn instanceof PlayerEntity)
		{
			boolean protect = this.isProtected(world.getBlockState(pos));
			world.setBlockState(pos, this.getUntrappedBlock(this).getDefaultState().with(CoreProtectedBlock.CORE_PROTECTED, protect));
			if (!world.isRemote)
			{
				EntityType<?> entityType = getEntity(this);
				Entity entity = entityType.create(world);
				entity.setPositionAndRotation(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, (rand.nextFloat() - 0.5F) * 180.0F, 0.0F);
				if (this == AerialHellBlocksAndItems.TRAPPED_MUD_BRICKS.get() || this == AerialHellBlocksAndItems.TRAPPED_LIGHT_MUD_BRICKS.get())
				{
					entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.STONE_SWORD));
				}
				world.addEntity(entity);
			}
			world.playSound(null, pos, AerialHellSoundEvents.TRAPPED_BLOCK_STEP.get(), SoundCategory.BLOCKS, 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
		}
	}
	
	private Block getUntrappedBlock(Block trappedBlock)
	{
		if (trappedBlock == AerialHellBlocksAndItems.TRAPPED_MUD_BRICKS.get())
		{
			return AerialHellBlocksAndItems.MUD_BRICKS.get();
		}
		else if (trappedBlock == AerialHellBlocksAndItems.TRAPPED_LIGHT_MUD_BRICKS.get())
		{
			return AerialHellBlocksAndItems.LIGHT_MUD_BRICKS.get();
		}
		else if (trappedBlock == AerialHellBlocksAndItems.TRAPPED_LUNATIC_STONE.get())
		{
			return AerialHellBlocksAndItems.LUNATIC_STONE.get();
		}
		else if (trappedBlock == AerialHellBlocksAndItems.TRAPPED_LIGHT_LUNATIC_STONE.get())
		{
			return AerialHellBlocksAndItems.LIGHT_LUNATIC_STONE.get();
		}
		else if (trappedBlock == AerialHellBlocksAndItems.TRAPPED_GOLDEN_NETHER_BRICKS.get())
		{
			return AerialHellBlocksAndItems.GOLDEN_NETHER_BRICKS.get();
		}
		else if (trappedBlock == AerialHellBlocksAndItems.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS.get())
		{
			return AerialHellBlocksAndItems.LIGHT_GOLDEN_NETHER_BRICKS.get();
		}
		else
		{
			return AerialHellBlocksAndItems.STELLAR_PORTAL_FRAME_BLOCK.get();
		}
	}
	
	private EntityType<?> getEntity(Block trappedBlock)
	{
		if (trappedBlock == AerialHellBlocksAndItems.TRAPPED_MUD_BRICKS.get() || trappedBlock == AerialHellBlocksAndItems.TRAPPED_LIGHT_MUD_BRICKS.get())
		{
			return AerialHellEntities.MUD_SOLDIER.get();
		}
		else if (trappedBlock == AerialHellBlocksAndItems.TRAPPED_LUNATIC_STONE.get() || trappedBlock == AerialHellBlocksAndItems.TRAPPED_LIGHT_LUNATIC_STONE.get())
		{
			return AerialHellEntities.CRYSTAL_GOLEM.get();
		}
		else if (trappedBlock == AerialHellBlocksAndItems.TRAPPED_GOLDEN_NETHER_BRICKS.get() || trappedBlock == AerialHellBlocksAndItems.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS.get())
		{
			return AerialHellEntities.TORN_SPIRIT.get();
		}
		else
		{
			return AerialHellEntities.EVIL_COW.get();
		}
	}
}