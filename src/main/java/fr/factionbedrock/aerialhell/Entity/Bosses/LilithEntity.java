package fr.factionbedrock.aerialhell.Entity.Bosses;

import java.util.List;

import fr.factionbedrock.aerialhell.Block.DirtAndVariants.StellarGrassBlock;
import fr.factionbedrock.aerialhell.Block.StandingAndWall.AerialHellTorchBlock;
import fr.factionbedrock.aerialhell.Block.StandingAndWall.AerialHellWallTorchBlock;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.Projectile.ShadowProjectileEntity;
import fr.factionbedrock.aerialhell.Registry.*;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;

public class LilithEntity extends AbstractBossEntity
{
	public int attackTimer;
	private int timeSinceTransforming;
	private final int transformationTime = 160; //8 seconds

	public LilithEntity(EntityType<? extends Monster> type, Level world)
	{
		super(type, world);
		attackTimer = 0;
		timeSinceTransforming = 0; this.hurtTime = 0;
		bossInfo.setColor(BossEvent.BossBarColor.PURPLE);
		bossInfo.setOverlay(BossEvent.BossBarOverlay.NOTCHED_6);
	}

	@Override protected void registerGoals()
    {
		this.targetSelector.addGoal(2, new ActiveNearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(3, new LilithMeleeAttackGoal(this, 1.25D, false));
		this.goalSelector.addGoal(2, new LilithSummonShadowFlyingSkullGoal(this));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new LilithWaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MudCycleMageEntity.class, true));
		this.goalSelector.addGoal(2, new ShadowProjectileAttackGoal(this));
    }
	
	public static AttributeSupplier.Builder registerAttributes()
    {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 600.0D)
				.add(Attributes.FOLLOW_RANGE, 32.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.3D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.1D)
				.add(Attributes.ATTACK_KNOCKBACK, 1.0D)
				.add(Attributes.ATTACK_DAMAGE, 20.0D);
    }
	
	@Override public boolean hurt(DamageSource source, float amount)
	{
		Entity immediateSourceEntity = source.getDirectEntity();
		Entity trueSourceEntity = source.getEntity();
		if (this.isTransforming() && !source.isCreativePlayer() && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {return false;}
		if (this.getMaxHealth() < 2.5 * this.getHealth() && immediateSourceEntity instanceof AbstractArrow) {return false;}
		boolean flag = super.hurt(source, amount);
		if (flag)
		{
			if (trueSourceEntity instanceof LivingEntity && !(immediateSourceEntity instanceof AbstractArrow))
			{
				if (!EntityHelper.isCreativePlayer(trueSourceEntity))
				{
					this.setTarget((LivingEntity) trueSourceEntity);
				}
			}
		}
		return flag;
	}
	
	@Override public void addAdditionalSaveData(CompoundTag nbt)
	{
		super.addAdditionalSaveData(nbt);
		nbt.putShort("timeTransforming", (short)this.timeSinceTransforming);
	}
	
	@Override public void readAdditionalSaveData(CompoundTag nbt)
	{
	    super.readAdditionalSaveData(nbt);
	    if (nbt.contains("timeTransforming", 99))
	    {
	    	this.timeSinceTransforming = nbt.getShort("timeTransforming");
	    }
	}

	public BossPhase getTransformingPhase() {return BossPhase.FIRST_TO_SECOND_TRANSITION;}
	@Override public int getPhaseIdToSkipToDyingPhase() {return BossPhase.SECOND_TO_THIRD_TRANSITION.getPhaseId();}
	@Override public boolean enableTickPhaseUpdate(BossPhaseTickType type) {return true;}
	@Override public boolean enableTryDyingPhaseUpdate() {return getPhase() == BossPhase.FIRST_PHASE;}

	public boolean isTransformed() {return this.getPhase() != BossPhase.FIRST_PHASE && !this.isTransforming();}
	public boolean isTransforming() {return this.getPhase() == BossPhase.FIRST_TO_SECOND_TRANSITION;}

	@Override public boolean shouldUpdateToPhase(BossPhase phase)
	{
		if (phase == this.getTransformingPhase()) {return this.isActive() && !this.isTransformed() && !this.isTransforming();}
		else if (phase == BossPhase.SECOND_PHASE) {return this.timeSinceTransforming >= transformationTime;}
		else {return false;}
	}

	@Override public void applyPhaseUpdateEffect(BossPhase nextPhase)
	{
		if (nextPhase == getTransformingPhase())
		{
			this.timeSinceTransforming = 0;
			this.playSound(AerialHellSoundEvents.ENTITY_LILITH_TRANSFORMATION, 5.0F, 1.0F);
		}
		else if (nextPhase == BossPhase.SECOND_PHASE)
		{
			this.spawnTransformationParticle();
			if (this.level().dimension() == AerialHellDimensions.AERIAL_HELL_DIMENSION) {this.transformAllBlocks();}
			this.timeSinceTransforming = 0;
		}
	}

	@Override public boolean fireImmune() {return true;}
	@Override public boolean displayFireAnimation() {return false;}
	
	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {return false;}

	@Override public Item getTrophy() {return AerialHellItems.LILITH_TROPHY;}

	@Override public void tickTransitionPhase()
	{
		if (this.isTransforming()) {this.tickTransformingPhase();}

		if (!this.level().isClientSide())
		{
			this.addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 10, true, false)));
			this.addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1, 10, true, false)));
		}
	}

	public void tickTransformingPhase()
	{
		this.timeSinceTransforming++;
		for (int i=0; i<10 + timeSinceTransforming/1.5; i++)
		{
			if (this.level().dimension() == AerialHellDimensions.AERIAL_HELL_DIMENSION) {this.transformRandomBlock();}
		}

		if (this.timeSinceTransforming > 12)
		{
			List<Entity> nearbyEntities = this.level().getEntities(this, this.getBoundingBox().inflate(20), EntitySelector.withinDistance(this.getX(), this.getY(), this.getZ(), 15));
			for (Entity entity : nearbyEntities)
			{
				if (entity instanceof LivingEntity && !EntityHelper.isCreaOrSpecPlayer(entity))
				{
					dragEntity(entity);
					((LivingEntity) entity).addEffect(new MobEffectInstance(AerialHellMobEffects.VULNERABILITY, 40, 0));
				}
			}

			if (this.level().isClientSide())
			{
				for (int i=0; i<5; i++)
				{
					double rand = random.nextFloat() * 2;
					double x = getX() + (random.nextFloat() - 0.5F) * rand, y = (this.getBoundingBox().minY + rand) + 0.5D, z = getZ() + (random.nextFloat() - 0.5F) * rand;
					double dx = (random.nextFloat() - 0.5F)/10, dz = (random.nextFloat() - 0.5F)/10;
					this.level().addParticle(AerialHellParticleTypes.SHADOW_PARTICLE, x, y, z, dx, 0.0D, dz);
				}
			}
		}
	}

	private void transformRandomBlock()
	{
		int maxHorizontalDistance = 14;
		int maxVerticalDistance = 10;
		int x = random.nextInt(2*maxHorizontalDistance) - maxHorizontalDistance;
		int y = random.nextInt(2*maxVerticalDistance) - maxVerticalDistance;
		int z = random.nextInt(2*maxHorizontalDistance) - maxHorizontalDistance;
		BlockPos transformationPos = new BlockPos(this.blockPosition().offset(new Vec3i(x, y, z)));
		if (this.level().getBlockState(transformationPos).is(AerialHellTags.Blocks.LILITH_TRANSFORMABLE))
		{
			transformBlock(transformationPos);
		}
	}

	private void transformAllBlocks()
	{
		int maxHorizontalDistance = 12;
		int maxVerticalDistance = 10;
		int x,y,z;
		for (x=-maxHorizontalDistance; x<maxHorizontalDistance; x++)
		{
			for (y=-maxVerticalDistance; y<maxVerticalDistance; y++)
			{
				for (z=-maxHorizontalDistance; z<maxHorizontalDistance; z++)
				{
					BlockPos transformationPos = new BlockPos(this.blockPosition().offset(new Vec3i(x, y, z)));
					if (this.level().getBlockState(transformationPos).is(AerialHellTags.Blocks.LILITH_TRANSFORMABLE))
					{
						transformBlock(transformationPos);
					}
				}
			}
		}
	}

	private void transformBlock(BlockPos pos)
	{
		if (this.level().getBlockState(pos).getBlock() instanceof DoorBlock)
		{
			DoubleBlockHalf half = this.level().getBlockState(pos).getValue(DoorBlock.HALF);
			if (half == DoubleBlockHalf.LOWER)
			{
				this.level().destroyBlock(pos, false);
				this.level().destroyBlock(pos.above(), false);
			}
			else
			{
				this.level().destroyBlock(pos.below(), false);
				this.level().destroyBlock(pos, false);
			}
		}
		else {this.level().setBlockAndUpdate(pos, getEquivalentShadowBlockstate(this.level().getBlockState(pos)));}
	}

	private BlockState getEquivalentShadowBlockstate(BlockState blockState)
	{
		Block block = blockState.getBlock(), newBlock;
		if (block instanceof AerialHellTorchBlock)
		{
			if (block instanceof AerialHellWallTorchBlock)
			{
				return AerialHellBlocks.SHADOW_WALL_TORCH.defaultBlockState().setValue(AerialHellWallTorchBlock.HORIZONTAL_FACING, blockState.getValue(AerialHellWallTorchBlock.HORIZONTAL_FACING));
			}
			else //not a wall torch
			{
				return AerialHellBlocks.SHADOW_TORCH.defaultBlockState();
			}
		}
		else if (blockState.is(AerialHellTags.Blocks.SOLID_ETHER))
		{
			return AerialHellBlocks.PURPLE_SOLID_ETHER.defaultBlockState();
		}
		else if (block == AerialHellBlocks.CRYSTAL_BLOCK)
		{
			return AerialHellBlocks.SHADOW_CRYSTAL_BLOCK.defaultBlockState();
		}
		else if (block == AerialHellBlocks.VIBRANT_SKY_CACTUS_FIBER_LANTERN)
		{
			return AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_LIGHT.defaultBlockState();
		}
		else if (block instanceof StellarGrassBlock)
		{
			return AerialHellBlocks.SHADOW_GRASS_BLOCK.defaultBlockState();
		}
		else if (block == AerialHellBlocks.STELLAR_GRASS)
		{
			return AerialHellBlocks.SHADOW_GRASS.defaultBlockState();
		}
		else if (block == AerialHellBlocks.STELLAR_GRASS_BALL)
		{
			return AerialHellBlocks.SHADOW_GRASS_BALL.defaultBlockState();
		}
		else if (block instanceof LanternBlock)
		{
			return AerialHellBlocks.SHADOW_LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, blockState.getValue(LanternBlock.HANGING));
		}
		else if (block instanceof ChainBlock)
		{
			return AerialHellBlocks.SHADOW_CHAIN.defaultBlockState().setValue(ChainBlock.AXIS, blockState.getValue(ChainBlock.AXIS));
		}
		else if (block instanceof IronBarsBlock)
		{
			return AerialHellBlocks.SHADOW_BARS.defaultBlockState().setValue(IronBarsBlock.NORTH, blockState.getValue(IronBarsBlock.NORTH)).setValue(IronBarsBlock.SOUTH, blockState.getValue(IronBarsBlock.SOUTH)).setValue(IronBarsBlock.WEST, blockState.getValue(IronBarsBlock.WEST)).setValue(IronBarsBlock.EAST, blockState.getValue(IronBarsBlock.EAST));
		}
		else if (block == AerialHellBlocks.LAPIS_ROBINIA_PLANKS || block == AerialHellBlocks.AERIAL_TREE_PLANKS || block == AerialHellBlocks.CHISELED_AERIAL_TREE_PLANKS)
		{
			return AerialHellBlocks.GRAY_SHROOM_PLANKS.defaultBlockState();
		}
		else if (block == AerialHellBlocks.GOLDEN_BEECH_PLANKS || block == AerialHellBlocks.CHISELED_GOLDEN_BEECH_PLANKS || block == Blocks.DARK_OAK_PLANKS || block == AerialHellBlocks.CHISELED_AERIAL_TREE_PLANKS)
		{
			return AerialHellBlocks.SHADOW_PINE_PLANKS.defaultBlockState();
		}
		else if (block instanceof SaplingBlock)
		{
			return AerialHellBlocks.SHADOW_PINE_SAPLING.defaultBlockState();
		}
		else if (block instanceof LeavesBlock)
		{
			if (block == AerialHellBlocks.GOLDEN_BEECH_LEAVES || block == Blocks.DARK_OAK_LEAVES) {newBlock = AerialHellBlocks.SHADOW_PINE_LEAVES;}
			else {newBlock = AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES;}
			return newBlock.defaultBlockState().setValue(LeavesBlock.PERSISTENT, blockState.getValue(LeavesBlock.PERSISTENT)).setValue(LeavesBlock.DISTANCE, blockState.getValue(LeavesBlock.DISTANCE));
		}
		else if (block instanceof CraftingTableBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_CRAFTING_TABLE || block == AerialHellBlocks.AERIAL_TREE_CRAFTING_TABLE) {return AerialHellBlocks.GRAY_SHROOM_CRAFTING_TABLE.defaultBlockState();}
			else {return AerialHellBlocks.SHADOW_PINE_CRAFTING_TABLE.defaultBlockState();}
		}
		else if (block instanceof StairBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_STAIRS || block == AerialHellBlocks.AERIAL_TREE_STAIRS) {newBlock = AerialHellBlocks.GRAY_SHROOM_STAIRS;}
			else if (block == Blocks.QUARTZ_STAIRS) {newBlock = AerialHellBlocks.SMOKY_QUARTZ_STAIRS;}
			else if (block == Blocks.SMOOTH_QUARTZ_STAIRS) {newBlock = AerialHellBlocks.SMOOTH_SMOKY_QUARTZ_STAIRS;}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocks.SHADOW_PINE_STAIRS;}
			return newBlock.defaultBlockState().setValue(StairBlock.FACING, blockState.getValue(StairBlock.FACING)).setValue(StairBlock.HALF, blockState.getValue(StairBlock.HALF)).setValue(StairBlock.SHAPE, blockState.getValue(StairBlock.SHAPE));
		}
		else if (block instanceof SlabBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_SLAB || block == AerialHellBlocks.AERIAL_TREE_SLAB) {newBlock = AerialHellBlocks.GRAY_SHROOM_SLAB;}
			else if (block == Blocks.QUARTZ_SLAB) {newBlock = AerialHellBlocks.SMOKY_QUARTZ_SLAB;}
			else if (block == Blocks.SMOOTH_QUARTZ_SLAB) {newBlock = AerialHellBlocks.SMOOTH_SMOKY_QUARTZ_SLAB;}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocks.SHADOW_PINE_SLAB;}
			return newBlock.defaultBlockState().setValue(SlabBlock.TYPE, blockState.getValue(SlabBlock.TYPE));
		}
		else if (block instanceof FenceBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_FENCE || block == AerialHellBlocks.AERIAL_TREE_FENCE) {newBlock = AerialHellBlocks.GRAY_SHROOM_FENCE;}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocks.SHADOW_PINE_FENCE;}
			return newBlock.defaultBlockState().setValue(FenceBlock.NORTH, blockState.getValue(FenceBlock.NORTH)).setValue(FenceBlock.EAST, blockState.getValue(FenceBlock.EAST)).setValue(FenceBlock.WEST, blockState.getValue(FenceBlock.WEST)).setValue(FenceBlock.SOUTH, blockState.getValue(FenceBlock.SOUTH));
		}
		else if (block instanceof FenceGateBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_GATE || block == AerialHellBlocks.AERIAL_TREE_GATE) {newBlock = AerialHellBlocks.GRAY_SHROOM_GATE;}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocks.SHADOW_PINE_GATE;}
			return newBlock.defaultBlockState().setValue(FenceGateBlock.FACING, blockState.getValue(FenceGateBlock.FACING)).setValue(FenceGateBlock.OPEN, blockState.getValue(FenceGateBlock.OPEN)).setValue(FenceGateBlock.POWERED, blockState.getValue(FenceGateBlock.POWERED)).setValue(FenceGateBlock.IN_WALL, blockState.getValue(FenceGateBlock.IN_WALL));
		}
		else if (block instanceof RotatedPillarBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_LOG || block == AerialHellBlocks.ENCHANTED_LAPIS_ROBINIA_LOG || block == AerialHellBlocks.AERIAL_TREE_LOG) {newBlock = AerialHellBlocks.GIANT_VERDIGRIS_AGARIC_STEM;}
			else if (block == AerialHellBlocks.STRIPPED_LAPIS_ROBINIA_LOG || block == AerialHellBlocks.STRIPPED_AERIAL_TREE_LOG) {newBlock = AerialHellBlocks.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM;}
			else if (block == Blocks.QUARTZ_PILLAR) {newBlock = AerialHellBlocks.SMOKY_QUARTZ_PILLAR;}
			else if (block == AerialHellBlocks.STRIPPED_GOLDEN_BEECH_LOG || block == Blocks.STRIPPED_DARK_OAK_LOG || block == Blocks.STRIPPED_OAK_LOG) {newBlock = AerialHellBlocks.STRIPPED_SHADOW_PINE_LOG;}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_LOG;}
			return newBlock.defaultBlockState().setValue(RotatedPillarBlock.AXIS, blockState.getValue(RotatedPillarBlock.AXIS));
		}
		else if (block == Blocks.CHISELED_QUARTZ_BLOCK) {return AerialHellBlocks.CHISELED_SMOKY_QUARTZ_BLOCK.defaultBlockState();}
		else if (block == Blocks.QUARTZ_BLOCK) {return AerialHellBlocks.SMOKY_QUARTZ_BLOCK.defaultBlockState();}
		else if (block == Blocks.QUARTZ_BRICKS) {return AerialHellBlocks.SMOKY_QUARTZ_BRICKS.defaultBlockState();}
		else if (block == Blocks.SMOOTH_QUARTZ) {return AerialHellBlocks.SMOOTH_SMOKY_QUARTZ.defaultBlockState();}
		else if (block == AerialHellBlocks.GIANT_GANODERMA_APPLANATUM_BLOCK) {return AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.defaultBlockState();}
		else if (blockState.is(AerialHellTags.Blocks.ENCHANTMENT_POWER_PROVIDER) || block == Blocks.BOOKSHELF)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_BOOKSHELF || block == AerialHellBlocks.AERIAL_TREE_BOOKSHELF) {return AerialHellBlocks.GRAY_SHROOM_BOOKSHELF.defaultBlockState();}
			else {return AerialHellBlocks.SHADOW_PINE_BOOKSHELF.defaultBlockState();}
		}
		else if (block instanceof TrapDoorBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_TRAPDOOR || block == AerialHellBlocks.AERIAL_TREE_TRAPDOOR) {newBlock = AerialHellBlocks.GRAY_SHROOM_TRAPDOOR;}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_TRAPDOOR;}
			return newBlock.defaultBlockState().setValue(TrapDoorBlock.OPEN, blockState.getValue(TrapDoorBlock.OPEN)).setValue(TrapDoorBlock.HALF, blockState.getValue(TrapDoorBlock.HALF)).setValue(TrapDoorBlock.POWERED, blockState.getValue(TrapDoorBlock.POWERED)).setValue(TrapDoorBlock.FACING, blockState.getValue(TrapDoorBlock.FACING));
		}
		else if (block instanceof DoorBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_DOOR || block == AerialHellBlocks.AERIAL_TREE_DOOR) {newBlock = AerialHellBlocks.GRAY_SHROOM_DOOR;}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_DOOR;}
			return newBlock.defaultBlockState().setValue(DoorBlock.FACING, blockState.getValue(DoorBlock.FACING)).setValue(DoorBlock.OPEN, blockState.getValue(DoorBlock.OPEN)).setValue(DoorBlock.HINGE, blockState.getValue(DoorBlock.HINGE)).setValue(DoorBlock.POWERED, blockState.getValue(DoorBlock.POWERED)).setValue(DoorBlock.HALF, blockState.getValue(DoorBlock.HALF));
		}
		else if (block instanceof ButtonBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_BUTTON || block == AerialHellBlocks.AERIAL_TREE_BUTTON) {newBlock = AerialHellBlocks.GRAY_SHROOM_BUTTON;}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_BUTTON;}
			return newBlock.defaultBlockState().setValue(ButtonBlock.POWERED, blockState.getValue(ButtonBlock.POWERED)).setValue(ButtonBlock.FACE, blockState.getValue(ButtonBlock.FACE)).setValue(ButtonBlock.FACING, blockState.getValue(ButtonBlock.FACING));
		}
		else if (block instanceof PressurePlateBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_PRESSURE_PLATE || block == AerialHellBlocks.AERIAL_TREE_PRESSURE_PLATE) {newBlock = AerialHellBlocks.GRAY_SHROOM_PRESSURE_PLATE;}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_PRESSURE_PLATE;}
			return newBlock.defaultBlockState().setValue(PressurePlateBlock.POWERED, blockState.getValue(PressurePlateBlock.POWERED));
		}
		else if (block instanceof ComposterBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_COMPOSTER || block == AerialHellBlocks.AERIAL_TREE_COMPOSTER) {newBlock = AerialHellBlocks.GRAY_SHROOM_COMPOSTER;}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_COMPOSTER;}
			return newBlock.defaultBlockState().setValue(ComposterBlock.LEVEL, blockState.getValue(ComposterBlock.LEVEL));
		}
		else if (block instanceof SignBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_STANDING_SIGN || block == AerialHellBlocks.AERIAL_TREE_STANDING_SIGN) {newBlock = AerialHellBlocks.GRAY_SHROOM_STANDING_SIGN;}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_STANDING_SIGN;}
			if (block instanceof StandingSignBlock)
			{
				return newBlock.defaultBlockState().setValue(StandingSignBlock.ROTATION, blockState.getValue(StandingSignBlock.ROTATION)).setValue(StandingSignBlock.WATERLOGGED, blockState.getValue(StandingSignBlock.WATERLOGGED));
			}
			else if (block instanceof WallSignBlock)
			{
				return newBlock.defaultBlockState().setValue(WallSignBlock.FACING, blockState.getValue(WallSignBlock.FACING)).setValue(WallSignBlock.WATERLOGGED, blockState.getValue(WallSignBlock.WATERLOGGED));
			}
			return newBlock.defaultBlockState();
		}
		return AerialHellBlocks.SHADOW_CATACOMBS_BRICKS.defaultBlockState();
	}
	
	@Override public void aiStep()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		super.aiStep();
    }
	
	@Override public boolean isPushable() {return false;}
	
	@Override public boolean doHurtTarget(Entity target)
	{
		this.level().broadcastEntityEvent(this, (byte)4);
		boolean flag = super.doHurtTarget(target);
		if (flag && target instanceof LivingEntity && !EntityHelper.isLivingEntityShadowImmune((LivingEntity) target))
		{
			((LivingEntity) target).addEffect(new MobEffectInstance(AerialHellMobEffects.VULNERABILITY, 40, 0));
		}
		this.playSound(SoundEvents.RAVAGER_STEP, 1.0F, 0.5F);
		return flag;
	}
	
	@Override
	public void handleEntityEvent(byte id)
	{
		if (id == 4) {this.attackTimer = 10;}
		else {super.handleEntityEvent(id);}
	}
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_LILITH_HURT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_LILITH_HURT;}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_LILITH_DEATH;}
	@Override public void playAmbientSound() {if (this.isTransforming()) {} else {super.playAmbientSound();}}

	private void dragEntity(Entity entityIn)
	{
		double factor = 0.2 / Math.max(5, this.distanceTo(entityIn)); //0.04 / Math.max(1, this.getDistance(entityIn)); and multiply only one time, to get uniform dragging
		Vec3 toGod = new Vec3(this.getX() - entityIn.getX(), this.getY() - entityIn.getY(), this.getZ() - entityIn.getZ()).multiply(factor, factor, factor);
		entityIn.setDeltaMovement(entityIn.getDeltaMovement().add(toGod.multiply(factor,factor,factor)));
	}
	
	public void spawnTransformationParticle()
	{
		if (this.level().isClientSide())
        {
        	for(int i = 0; i < 30; ++i)
            {
            	double d0 = this.random.nextGaussian() * 0.02D;
            	double d1 = this.random.nextGaussian() * 0.02D;
            	double d2 = this.random.nextGaussian() * 0.02D;
            	this.level().addParticle(AerialHellParticleTypes.SHADOW_PARTICLE, this.getRandomX(1.0D) - d0 * 10.0D, this.getRandomY() - d1 * 10.0D, this.getRandomZ(1.0D) - d2 * 10.0D, 2 * d0, d1, 2 * d2);
            }
        }
        else
        {
           this.level().broadcastEntityEvent(this, (byte)20);
        }
	}
	
	/* Lilith Goals */

	public boolean isHealthMatchToShootShadowProjectile() {return this.getHealth() * 2 < this.getMaxHealth();}
	public boolean isHealthMatchToSummonFlyingSkulls() {return  this.getMaxHealth() > (2.5 - this.getDifficulty() / 6.0) * this.getHealth();}

	public static class ShadowProjectileAttackGoal extends GhastLikeGoals.ShootProjectileGoal
	{
		public ShadowProjectileAttackGoal(LilithEntity entity) {super(entity);}

		@Override public boolean canUse()
		{
			LilithEntity lilith = (LilithEntity)this.getParentEntity();
			if (!lilith.isActive()) {return false;}
			LivingEntity target = lilith.getTarget();
			return super.canUse() && lilith.isHealthMatchToShootShadowProjectile() && target.isAlive() && lilith.canAttack(target);
		}

		@Override public Projectile createProjectile(Level world, LivingEntity shooter, double accX, double accY, double accZ)
		{
			return new ShadowProjectileEntity(world, shooter, accX, accY, accZ, 0.25f + shooter.getRandom().nextFloat(), 0.0f);
		}

		@Override public int getShootTimeInterval()
		{
			int difficulty = ((LilithEntity)this.getParentEntity()).getDifficulty();
			if (difficulty == 0) {difficulty = 1;}
			return 90 / difficulty + (int) (this.getParentEntity().getRandom().nextFloat() * 40);
		}

		@Override public int getShootDelay() {return 0;}
		@Override public boolean doesShootTimeDecreaseWhenTargetOutOfSight() {return false;}
		@Override public double getYProjectileOffset() {return 0.5D;}
		@Override protected void setAttacking(boolean bool) {}
		@Override public SoundEvent getShootSound() {return null;}
	}

	public static class LilithMeleeAttackGoal extends ActiveMeleeAttackGoal
	{
		public LilithMeleeAttackGoal(LilithEntity godIn, double speedIn, boolean useLongMemory) {super(godIn, speedIn, useLongMemory);}
		@Override public boolean additionalConditionMet() {return super.additionalConditionMet() && !((LilithEntity) this.goalOwner).isTransforming();}
	}
	
	public static class LilithWaterAvoidingRandomWalkingGoal extends ActiveWaterAvoidingRandomWalkingGoal
	{
		public LilithWaterAvoidingRandomWalkingGoal(LilithEntity god, double speedIn) {super(god, speedIn);}
		@Override public boolean additionalConditionMet() {return super.additionalConditionMet() && !((LilithEntity) this.getGoalOwner()).isTransforming();}
	}

	public static class LilithSummonShadowFlyingSkullGoal extends SummonThreeEntitiesGoal
	{
		public LilithSummonShadowFlyingSkullGoal(LilithEntity entity) {super(entity, 0.2D);}

		public LilithEntity getLilithGoalOwner() {return (LilithEntity) this.getGoalOwner();}

		@Override public boolean canUse()
		{
			LilithEntity lilith = this.getLilithGoalOwner();
			return super.canUse() && lilith.isHealthMatchToSummonFlyingSkulls() && lilith.isActive();
		}

		@Override public Entity createEntity()
		{
			return AerialHellEntities.SHADOW_FLYING_SKULL.create(this.getGoalOwner().level());
		}

		@Override protected void setEntityPosToSummonPos(Entity entity) {entity.setPosRaw(this.getGoalOwner().getX(), this.getGoalOwner().getY() + 1.0, this.getGoalOwner().getZ());}

		@Override protected int getSummonTimerTargetValue()
		{
			int difficulty = this.getLilithGoalOwner().getDifficulty();
			return switch (difficulty)
			{
				default-> 180; //never happens theorically. 0 is when there is no player nearby
				case 1 -> 130;
				case 2 -> 115;
				case 3 -> 100;
				case 4 -> 80;
				case 5 -> 60;
				case 6 -> 50;
			};
		}
	}
}
