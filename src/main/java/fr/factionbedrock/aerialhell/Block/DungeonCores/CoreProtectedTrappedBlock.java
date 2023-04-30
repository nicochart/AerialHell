package fr.factionbedrock.aerialhell.Block.DungeonCores;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CoreProtectedTrappedBlock extends CoreProtectedBlock
{
	private static final Random rand = new Random();
	
	public CoreProtectedTrappedBlock(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(CORE_PROTECTED, false));
	}

	@Override
	public void stepOn(Level world, BlockPos pos, BlockState state, Entity entityIn)
	{
		if (entityIn instanceof Player)
		{
			boolean protect = this.isProtected(world.getBlockState(pos));
			world.setBlockAndUpdate(pos, this.getUntrappedBlock(this).defaultBlockState().setValue(CoreProtectedBlock.CORE_PROTECTED, protect));
			if (!world.isClientSide())
			{
				EntityType<?> entityType = getEntity(this);
				Entity entity = entityType.create(world);
				entity.absMoveTo(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, (rand.nextFloat() - 0.5F) * 180.0F, 0.0F);
				if (this == AerialHellBlocksAndItems.TRAPPED_MUD_BRICKS.get() || this == AerialHellBlocksAndItems.TRAPPED_LIGHT_MUD_BRICKS.get())
				{
					entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
				}
				world.addFreshEntity(entity);
			}
			world.playSound(null, pos, AerialHellSoundEvents.TRAPPED_BLOCK_STEP.get(), SoundSource.BLOCKS, 1.0F, world.random.nextFloat() * 0.1F + 0.9F);
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