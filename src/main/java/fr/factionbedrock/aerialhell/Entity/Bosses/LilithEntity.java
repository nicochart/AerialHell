package fr.factionbedrock.aerialhell.Entity.Bosses;

import java.util.List;

import fr.factionbedrock.aerialhell.Block.*;
import fr.factionbedrock.aerialhell.Block.DirtAndVariants.StellarGrassBlock;
import fr.factionbedrock.aerialhell.Block.StandingAndWall.AerialHellStandingSignBlock;
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
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.BossEvent;
import net.minecraft.world.level.Level;
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
	
	@Override public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putShort("timeTransforming", (short)this.timeSinceTransforming);
	}
	
	@Override public void readAdditionalSaveData(CompoundTag compound)
	{
	    super.readAdditionalSaveData(compound);
	    if (compound.contains("timeTransforming", 99))
	    {
	    	this.timeSinceTransforming = compound.getShort("timeTransforming");
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
			this.playSound(AerialHellSoundEvents.ENTITY_LILITH_TRANSFORMATION.get(), 5.0F, 1.0F);
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

	@Override public Item getTrophy() {return AerialHellBlocksAndItems.LILITH_TROPHY_ITEM.get();}

	@Override public void tickTransitionPhase()
	{
		if (this.isTransforming()) {this.tickTransformingPhase();}

		if (!level().isClientSide())
		{
			this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 10, true, false));
			this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1, 10, true, false));
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
					((LivingEntity) entity).addEffect(new MobEffectInstance(AerialHellMobEffects.VULNERABILITY.getDelegate(), 40, 0));
				}
			}

			if (this.level().isClientSide())
			{
				for (int i=0; i<5; i++)
				{
					double rand = random.nextFloat() * 2;
					double x = getX() + (random.nextFloat() - 0.5F) * rand, y = (this.getBoundingBox().minY + rand) + 0.5D, z = getZ() + (random.nextFloat() - 0.5F) * rand;
					double dx = (random.nextFloat() - 0.5F)/10, dz = (random.nextFloat() - 0.5F)/10;
					this.level().addParticle(AerialHellParticleTypes.SHADOW_PARTICLE.get(), x, y, z, dx, 0.0D, dz);
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
		if (level().getBlockState(transformationPos).is(AerialHellTags.Blocks.LILITH_TRANSFORMABLE))
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
					if (level().getBlockState(transformationPos).is(AerialHellTags.Blocks.LILITH_TRANSFORMABLE))
					{
						transformBlock(transformationPos);
					}
				}
			}
		}
	}

	private void transformBlock(BlockPos pos)
	{
		if (level().getBlockState(pos).getBlock() instanceof DoorBlock)
		{
			DoubleBlockHalf half = level().getBlockState(pos).getValue(DoorBlock.HALF);
			if (half == DoubleBlockHalf.LOWER)
			{
				level().destroyBlock(pos, false);
				level().destroyBlock(pos.above(), false);
			}
			else
			{
				level().destroyBlock(pos.below(), false);
				level().destroyBlock(pos, false);
			}
		}
		else {level().setBlockAndUpdate(pos, getEquivalentShadowBlockstate(level().getBlockState(pos)));}
	}

	private BlockState getEquivalentShadowBlockstate(BlockState blockState)
	{
		Block block = blockState.getBlock(), newBlock;
		if (block instanceof AerialHellTorchBlock)
		{
			if (block instanceof AerialHellWallTorchBlock)
			{
				return AerialHellBlocksAndItems.SHADOW_WALL_TORCH.get().defaultBlockState().setValue(AerialHellWallTorchBlock.HORIZONTAL_FACING, blockState.getValue(AerialHellWallTorchBlock.HORIZONTAL_FACING));
			}
			else //not a wall torch
			{
				return AerialHellBlocksAndItems.SHADOW_TORCH.get().defaultBlockState();
			}
		}
		else if (blockState.is(AerialHellTags.Blocks.SOLID_ETHER))
		{
			return AerialHellBlocksAndItems.PURPLE_SOLID_ETHER.get().defaultBlockState();
		}
		else if (block == AerialHellBlocksAndItems.CRYSTAL_BLOCK.get())
		{
			return AerialHellBlocksAndItems.SHADOW_CRYSTAL_BLOCK.get().defaultBlockState();
		}
		else if (block == AerialHellBlocksAndItems.VIBRANT_SKY_CACTUS_FIBER_LANTERN.get())
		{
			return AerialHellBlocksAndItems.GIANT_CORTINARIUS_VIOLACEUS_LIGHT.get().defaultBlockState();
		}
		else if (block instanceof StellarGrassBlock)
		{
			return AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get().defaultBlockState();
		}
		else if (block == AerialHellBlocksAndItems.STELLAR_GRASS.get())
		{
			return AerialHellBlocksAndItems.SHADOW_GRASS.get().defaultBlockState();
		}
		else if (block == AerialHellBlocksAndItems.STELLAR_GRASS_BALL.get())
		{
			return AerialHellBlocksAndItems.SHADOW_GRASS_BALL.get().defaultBlockState();
		}
		else if (block instanceof LanternBlock)
		{
			return AerialHellBlocksAndItems.SHADOW_LANTERN.get().defaultBlockState().setValue(LanternBlock.HANGING, blockState.getValue(LanternBlock.HANGING));
		}
		else if (block instanceof ChainBlock)
		{
			return AerialHellBlocksAndItems.SHADOW_CHAIN.get().defaultBlockState().setValue(ChainBlock.AXIS, blockState.getValue(ChainBlock.AXIS));
		}
		else if (block instanceof IronBarsBlock)
		{
			return AerialHellBlocksAndItems.SHADOW_BARS.get().defaultBlockState().setValue(IronBarsBlock.NORTH, blockState.getValue(IronBarsBlock.NORTH)).setValue(IronBarsBlock.SOUTH, blockState.getValue(IronBarsBlock.SOUTH)).setValue(IronBarsBlock.WEST, blockState.getValue(IronBarsBlock.WEST)).setValue(IronBarsBlock.EAST, blockState.getValue(IronBarsBlock.EAST));
		}
		else if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_PLANKS.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_PLANKS.get() || block == AerialHellBlocksAndItems.CHISELED_AERIAL_TREE_PLANKS.get())
		{
			return AerialHellBlocksAndItems.GRAY_SHROOM_PLANKS.get().defaultBlockState();
		}
		else if (block == AerialHellBlocksAndItems.GOLDEN_BEECH_PLANKS.get() || block == AerialHellBlocksAndItems.CHISELED_GOLDEN_BEECH_PLANKS.get() || block == Blocks.DARK_OAK_PLANKS || block == AerialHellBlocksAndItems.CHISELED_AERIAL_TREE_PLANKS.get())
		{
			return AerialHellBlocksAndItems.SHADOW_PINE_PLANKS.get().defaultBlockState();
		}
		else if (block instanceof SaplingBlock)
		{
			return AerialHellBlocksAndItems.SHADOW_PINE_SAPLING.get().defaultBlockState();
		}
		else if (block instanceof LeavesBlock)
		{
			if (block == AerialHellBlocksAndItems.GOLDEN_BEECH_LEAVES.get() || block == Blocks.DARK_OAK_LEAVES) {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_LEAVES.get();}
			else {newBlock = AerialHellBlocksAndItems.PURPLE_SHADOW_PINE_LEAVES.get();}
			return newBlock.defaultBlockState().setValue(LeavesBlock.PERSISTENT, blockState.getValue(LeavesBlock.PERSISTENT)).setValue(LeavesBlock.DISTANCE, blockState.getValue(LeavesBlock.DISTANCE));
		}
		else if (block instanceof CraftingTableBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_CRAFTING_TABLE.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_CRAFTING_TABLE.get()) {return AerialHellBlocksAndItems.GRAY_SHROOM_CRAFTING_TABLE.get().defaultBlockState();}
			else {return AerialHellBlocksAndItems.SHADOW_PINE_CRAFTING_TABLE.get().defaultBlockState();}
		}
		else if (block instanceof StairBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_STAIRS.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_STAIRS.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_STAIRS.get();}
			else if (block == Blocks.QUARTZ_STAIRS) {newBlock = AerialHellBlocksAndItems.SMOKY_QUARTZ_STAIRS.get();}
			else if (block == Blocks.SMOOTH_QUARTZ_STAIRS) {newBlock = AerialHellBlocksAndItems.SMOOTH_SMOKY_QUARTZ_STAIRS.get();}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_STAIRS.get();}
			return newBlock.defaultBlockState().setValue(StairBlock.FACING, blockState.getValue(StairBlock.FACING)).setValue(StairBlock.HALF, blockState.getValue(StairBlock.HALF)).setValue(StairBlock.SHAPE, blockState.getValue(StairBlock.SHAPE));
		}
		else if (block instanceof SlabBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_SLAB.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_SLAB.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_SLAB.get();}
			else if (block == Blocks.QUARTZ_SLAB) {newBlock = AerialHellBlocksAndItems.SMOKY_QUARTZ_SLAB.get();}
			else if (block == Blocks.SMOOTH_QUARTZ_SLAB) {newBlock = AerialHellBlocksAndItems.SMOOTH_SMOKY_QUARTZ_SLAB.get();}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_SLAB.get();}
			return newBlock.defaultBlockState().setValue(SlabBlock.TYPE, blockState.getValue(SlabBlock.TYPE));
		}
		else if (block instanceof FenceBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_FENCE.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_FENCE.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_FENCE.get();}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_FENCE.get();}
			return newBlock.defaultBlockState().setValue(FenceBlock.NORTH, blockState.getValue(FenceBlock.NORTH)).setValue(FenceBlock.EAST, blockState.getValue(FenceBlock.EAST)).setValue(FenceBlock.WEST, blockState.getValue(FenceBlock.WEST)).setValue(FenceBlock.SOUTH, blockState.getValue(FenceBlock.SOUTH));
		}
		else if (block instanceof FenceGateBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_GATE.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_GATE.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_GATE.get();}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_GATE.get();}
			return newBlock.defaultBlockState().setValue(FenceGateBlock.FACING, blockState.getValue(FenceGateBlock.FACING)).setValue(FenceGateBlock.OPEN, blockState.getValue(FenceGateBlock.OPEN)).setValue(FenceGateBlock.POWERED, blockState.getValue(FenceGateBlock.POWERED)).setValue(FenceGateBlock.IN_WALL, blockState.getValue(FenceGateBlock.IN_WALL));
		}
		else if (block instanceof RotatedPillarBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_LOG.get() || block == AerialHellBlocksAndItems.ENCHANTED_LAPIS_ROBINIA_LOG.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_LOG.get()) {newBlock = AerialHellBlocksAndItems.GIANT_VERDIGRIS_AGARIC_STEM.get();}
			else if (block == AerialHellBlocksAndItems.STRIPPED_LAPIS_ROBINIA_LOG.get() || block == AerialHellBlocksAndItems.STRIPPED_AERIAL_TREE_LOG.get()) {newBlock = AerialHellBlocksAndItems.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM.get();}
			else if (block == Blocks.QUARTZ_PILLAR) {newBlock = AerialHellBlocksAndItems.SMOKY_QUARTZ_PILLAR.get();}
			else if (block == AerialHellBlocksAndItems.STRIPPED_GOLDEN_BEECH_LOG.get() || block == Blocks.STRIPPED_DARK_OAK_LOG || block == Blocks.STRIPPED_OAK_LOG) {newBlock = AerialHellBlocksAndItems.STRIPPED_SHADOW_PINE_LOG.get();}
			else {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_LOG.get();}
			return newBlock.defaultBlockState().setValue(RotatedPillarBlock.AXIS, blockState.getValue(RotatedPillarBlock.AXIS));
		}
		else if (block == Blocks.CHISELED_QUARTZ_BLOCK) {return AerialHellBlocksAndItems.CHISELED_SMOKY_QUARTZ_BLOCK.get().defaultBlockState();}
		else if (block == Blocks.QUARTZ_BLOCK) {return AerialHellBlocksAndItems.SMOKY_QUARTZ_BLOCK.get().defaultBlockState();}
		else if (block == Blocks.QUARTZ_BRICKS) {return AerialHellBlocksAndItems.SMOKY_QUARTZ_BRICKS.get().defaultBlockState();}
		else if (block == Blocks.SMOOTH_QUARTZ) {return AerialHellBlocksAndItems.SMOOTH_SMOKY_QUARTZ.get().defaultBlockState();}
		else if (block == AerialHellBlocksAndItems.GIANT_GANODERMA_APPLANATUM_BLOCK.get()) {return AerialHellBlocksAndItems.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.get().defaultBlockState();}
		else if (block instanceof AerialHellBookshelfBlock || block == Blocks.BOOKSHELF)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_BOOKSHELF.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_BOOKSHELF.get()) {return AerialHellBlocksAndItems.GRAY_SHROOM_BOOKSHELF.get().defaultBlockState();}
			else {return AerialHellBlocksAndItems.SHADOW_PINE_BOOKSHELF.get().defaultBlockState();}
		}
		else if (block instanceof TrapDoorBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_TRAPDOOR.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_TRAPDOOR.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_TRAPDOOR.get();}
			else {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_TRAPDOOR.get();}
			return newBlock.defaultBlockState().setValue(TrapDoorBlock.OPEN, blockState.getValue(TrapDoorBlock.OPEN)).setValue(TrapDoorBlock.HALF, blockState.getValue(TrapDoorBlock.HALF)).setValue(TrapDoorBlock.POWERED, blockState.getValue(TrapDoorBlock.POWERED)).setValue(TrapDoorBlock.FACING, blockState.getValue(TrapDoorBlock.FACING));
		}
		else if (block instanceof DoorBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_DOOR.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_DOOR.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_DOOR.get();}
			else {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_DOOR.get();}
			return newBlock.defaultBlockState().setValue(DoorBlock.FACING, blockState.getValue(DoorBlock.FACING)).setValue(DoorBlock.OPEN, blockState.getValue(DoorBlock.OPEN)).setValue(DoorBlock.HINGE, blockState.getValue(DoorBlock.HINGE)).setValue(DoorBlock.POWERED, blockState.getValue(DoorBlock.POWERED)).setValue(DoorBlock.HALF, blockState.getValue(DoorBlock.HALF));
		}
		else if (block instanceof ButtonBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_BUTTON.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_BUTTON.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_BUTTON.get();}
			else {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_BUTTON.get();}
			return newBlock.defaultBlockState().setValue(ButtonBlock.POWERED, blockState.getValue(ButtonBlock.POWERED)).setValue(ButtonBlock.FACE, blockState.getValue(ButtonBlock.FACE)).setValue(ButtonBlock.FACING, blockState.getValue(ButtonBlock.FACING));
		}
		else if (block instanceof PressurePlateBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_PRESSURE_PLATE.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_PRESSURE_PLATE.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_PRESSURE_PLATE.get();}
			else {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_PRESSURE_PLATE.get();}
			return newBlock.defaultBlockState().setValue(PressurePlateBlock.POWERED, blockState.getValue(PressurePlateBlock.POWERED));
		}
		else if (block instanceof ComposterBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_COMPOSTER.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_COMPOSTER.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_COMPOSTER.get();}
			else {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_COMPOSTER.get();}
			return newBlock.defaultBlockState().setValue(ComposterBlock.LEVEL, blockState.getValue(ComposterBlock.LEVEL));
		}
		else if (block instanceof SignBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_STANDING_SIGN.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_STANDING_SIGN.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_STANDING_SIGN.get();}
			else {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_STANDING_SIGN.get();}
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
		return AerialHellBlocksAndItems.SHADOW_CATACOMBS_BRICKS.get().defaultBlockState();
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
			((LivingEntity) target).addEffect(new MobEffectInstance(AerialHellMobEffects.VULNERABILITY.getDelegate(), 40, 0));
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
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_LILITH_HURT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_LILITH_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_LILITH_DEATH.get();}
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
            	this.level().addParticle(AerialHellParticleTypes.SHADOW_PARTICLE.get(), this.getRandomX(1.0D) - d0 * 10.0D, this.getRandomY() - d1 * 10.0D, this.getRandomZ(1.0D) - d2 * 10.0D, 2 * d0, d1, 2 * d2);
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

		@Override public Projectile createProjectile(Level level, LivingEntity shooter, double accX, double accY, double accZ)
		{
			return new ShadowProjectileEntity(level, shooter, accX, accY, accZ, 0.25f + shooter.getRandom().nextFloat(), 0.0f);
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
			return AerialHellEntities.SHADOW_FLYING_SKULL.get().create(this.getGoalOwner().level());
		}

		@Override protected void setEntityPosToSummonPos(Entity entity) {entity.setPos(this.getGoalOwner().getX(), this.getGoalOwner().getY() + 1.0, this.getGoalOwner().getZ());}

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
