package fr.factionbedrock.aerialhell.Block.DungeonCores;

import java.util.Random;

import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSoldierEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CoreProtectedTrappedBlock extends CoreProtectedBlock
{
	private static final Random rand = new Random();
	
	public CoreProtectedTrappedBlock(AbstractBlock.Settings settings)
	{
		super(settings);
		this.setDefaultState(this.getDefaultState().with(CORE_PROTECTED, false));
	}

	@Override
	public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity)
	{
		if (entity instanceof PlayerEntity)
		{
			boolean protect = this.isProtected(world.getBlockState(pos));
			world.setBlockState(pos, this.getUntrappedBlock(this).getDefaultState().with(CoreProtectedBlock.CORE_PROTECTED, protect));
			if (!world.isClient())
			{
				EntityType<?> entityType = getEntity(this);
				Entity creature = entityType.create(world, SpawnReason.MOB_SUMMONED);
				creature.updatePositionAndAngles(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, (rand.nextFloat() - 0.5F) * 180.0F, 0.0F);
				if (this == AerialHellBlocks.TRAPPED_MUD_BRICKS || this == AerialHellBlocks.TRAPPED_LIGHT_MUD_BRICKS && entity instanceof MudSoldierEntity)
				{
					((MudSoldierEntity) creature).equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
				}
				world.spawnEntity(creature);
			}
			world.playSound(null, pos, AerialHellSoundEvents.TRAPPED_BLOCK_STEP, SoundCategory.BLOCKS, 1.0F, world.random.nextFloat() * 0.1F + 0.9F);
		}
	}
	
	private Block getUntrappedBlock(Block trappedBlock)
	{
		if (trappedBlock == AerialHellBlocks.TRAPPED_MUD_BRICKS)
		{
			return AerialHellBlocks.MUD_BRICKS;
		}
		else if (trappedBlock == AerialHellBlocks.TRAPPED_LIGHT_MUD_BRICKS)
		{
			return AerialHellBlocks.LIGHT_MUD_BRICKS;
		}
		else if (trappedBlock == AerialHellBlocks.TRAPPED_LUNATIC_STONE)
		{
			return AerialHellBlocks.LUNATIC_STONE;
		}
		else if (trappedBlock == AerialHellBlocks.TRAPPED_LIGHT_LUNATIC_STONE)
		{
			return AerialHellBlocks.LIGHT_LUNATIC_STONE;
		}
		else if (trappedBlock == AerialHellBlocks.TRAPPED_GOLDEN_NETHER_BRICKS)
		{
			return AerialHellBlocks.GOLDEN_NETHER_BRICKS;
		}
		else if (trappedBlock == AerialHellBlocks.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS)
		{
			return AerialHellBlocks.LIGHT_GOLDEN_NETHER_BRICKS;
		}
		else
		{
			return AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK;
		}
	}
	
	private EntityType<?> getEntity(Block trappedBlock)
	{
		if (trappedBlock == AerialHellBlocks.TRAPPED_MUD_BRICKS || trappedBlock == AerialHellBlocks.TRAPPED_LIGHT_MUD_BRICKS)
		{
			return AerialHellEntities.MUD_SOLDIER;
		}
		else if (trappedBlock == AerialHellBlocks.TRAPPED_LUNATIC_STONE || trappedBlock == AerialHellBlocks.TRAPPED_LIGHT_LUNATIC_STONE)
		{
			return AerialHellEntities.CRYSTAL_GOLEM;
		}
		else if (trappedBlock == AerialHellBlocks.TRAPPED_GOLDEN_NETHER_BRICKS || trappedBlock == AerialHellBlocks.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS)
		{
			return AerialHellEntities.TORN_SPIRIT;
		}
		else
		{
			return AerialHellEntities.EVIL_COW;
		}
	}
}