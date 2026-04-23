package fr.factionbedrock.aerialhell.Block.DungeonCores;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSoldierEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;

import static fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties.CORE_PROTECTED;

public class CoreProtectedTrappedBlock extends CoreProtectedBlock
{
	private static final Random rand = new Random();
	
	public CoreProtectedTrappedBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
		this.registerDefaultState(this.defaultBlockState().setValue(CORE_PROTECTED, false));
	}

	@Override public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity)
	{
		if (entity instanceof Player)
		{
			boolean protect = this.isProtected(world.getBlockState(pos));
			world.setBlockAndUpdate(pos, this.getUntrappedBlock(this).defaultBlockState().setValue(CORE_PROTECTED, protect));
			if (!world.isClientSide())
			{
				EntityType<?> entityType = getEntity(this);
				Entity creature = entityType.create(world, EntitySpawnReason.MOB_SUMMONED);
				creature.absSnapTo(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, (rand.nextFloat() - 0.5F) * 180.0F, 0.0F);
				if (this == AerialHellBlocks.TRAPPED_MUD_BRICKS || this == AerialHellBlocks.TRAPPED_LIGHT_MUD_BRICKS && entity instanceof MudSoldierEntity)
				{
					((MudSoldierEntity) creature).setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
				}
				world.addFreshEntity(creature);
			}
			world.playSound(null, pos, AerialHellSoundEvents.TRAPPED_BLOCK_STEP, SoundSource.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.1F + 0.9F);
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