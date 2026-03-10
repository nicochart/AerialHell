package fr.factionbedrock.aerialhell.Entity.Bosses;

import java.util.List;

import fr.factionbedrock.aerialhell.Block.*;
import fr.factionbedrock.aerialhell.Block.DirtAndVariants.StellarGrassBlock;
import fr.factionbedrock.aerialhell.Block.StandingAndWall.AerialHellTorchBlock;
import fr.factionbedrock.aerialhell.Block.StandingAndWall.AerialHellWallTorchBlock;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.AI.GhastLike.ShootProjectileGoal;
import fr.factionbedrock.aerialhell.Entity.Projectile.ShadowProjectileEntity;
import fr.factionbedrock.aerialhell.Entity.StagedActivableEntity;
import fr.factionbedrock.aerialhell.Entity.Util.PlaySoundHelper;
import fr.factionbedrock.aerialhell.Registry.*;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.Vec3i;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
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

public class LilithEntity extends AbstractBossEntity implements StagedActivableEntity
{
	public int attackTimer;
	private int transitionTicks;
	private final int transitionTicksTreshold = 10;

	/* --- StagedActivableEntity fields --- */
	private static final EntityDataAccessor<Boolean> TRANSFORMING = SynchedEntityData.defineId(LilithEntity.class, EntityDataSerializers.BOOLEAN);
	StagedActivableEntityInfo.ActivatingPhaseParameters LILITH_TRANSFORMING_PARAMETERS = PLAY_ACTIVATING_PHASE_ONLY_ONCE.copy().activatingThreshold(160).activatingStartSoundHelper(new PlaySoundHelper(AerialHellSoundEvents.ENTITY_LILITH_TRANSFORMATION.get(), 5.0F, 1.0F));
	public final StagedActivableEntityInfo STAGED_ACTIVABLE_INFO = new StagedActivableEntityInfo(this.ACTIVABLE_INFO, TRANSFORMING, LILITH_TRANSFORMING_PARAMETERS);
	/* -------------------------------------- */

	public LilithEntity(EntityType<? extends Monster> type, Level world)
	{
		super(type, world);
		attackTimer = 0;
		transitionTicks = 0; this.hurtTime = 0;
		bossInfo.setColor(BossEvent.BossBarColor.PURPLE);
		bossInfo.setOverlay(BossEvent.BossBarOverlay.NOTCHED_6);
	}

	@Override protected void defineSynchedData(SynchedEntityData.Builder builder)
	{
		super.defineSynchedData(builder);
		builder.define(TRANSFORMING, false);
	}

	@Override public boolean canUseGoalsAdditionalCondition() {return super.canUseGoalsAdditionalCondition() && !this.isTransforming();}

	/* ------- StagedActivableEntity : Interface method implementation ------- */
	@Override public StagedActivableEntityInfo getActivableInfo() {return STAGED_ACTIVABLE_INFO;}
	/* ----------------------------------------------------------------------- */

	/* ------- StagedActivableEntity : overriden methods pour specific behavior ------- */
	@Override public void onActivatingPhaseTick()
	{
		StagedActivableEntity.super.onActivatingPhaseTick();
		this.tickTransformingPhase();
	}

	@Override public void onFinishActivating() //server-side
	{
		StagedActivableEntity.super.onFinishActivating();
		this.level().broadcastEntityEvent(this, (byte) 76); //particles display needs broadcast
		if (this.level().dimension() == AerialHellDimensions.AERIAL_HELL_DIMENSION) {this.transformAllBlocks();}
	}

	@Override public boolean needsActivatingTicksSyncClientSide() {return true;} //for particles in
	/* -------------------------------------------------------------------------------- */

	/* ------- StagedActivableEntity : alias method to clarity lilith's behavior in code ------- */
	public boolean isTransformed() {return this.alreadyActivatedOnce();}
	public boolean isTransforming() {return this.isActivating();}
	/* ---------------------------------------------------------------------------------------------- */

	@Override protected void registerGoals()
    {
		this.targetSelector.addGoal(2, new ConditionalGoal(this, new NearestAttackableTargetGoal<>(this, Player.class, true)));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(3, new ConditionalGoal(this, new MeleeAttackGoal(this, 1.25D, false)));
		this.goalSelector.addGoal(2, new LilithSummonShadowFlyingSkullGoal(this));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new ConditionalGoal(this, new WaterAvoidingRandomStrollGoal(this, 0.6D)));
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
	
	@Override public boolean hurtServer(ServerLevel level, DamageSource source, float amount)
	{
		Entity immediateSourceEntity = source.getDirectEntity();
		Entity trueSourceEntity = source.getEntity();
		if (this.isTransforming() && !source.isCreativePlayer() && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {return false;}
		if (this.getMaxHealth() < 2.5 * this.getHealth() && immediateSourceEntity instanceof AbstractArrow) {return false;}
		boolean flag = super.hurtServer(level, source, amount);
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

	@Override public int getPhaseIdToSkipToDyingPhase() {return BossPhase.FIRST_TO_SECOND_TRANSITION.getPhaseId();}
	@Override public boolean enableTickPhaseUpdate(BossPhaseTickType type) {return true;}
	@Override public boolean enableTryDyingPhaseUpdate() {return getPhase() == BossPhase.FIRST_PHASE;}

	@Override public boolean shouldUpdateToPhase(BossPhase phase)
	{
		if (phase == BossPhase.FIRST_TO_SECOND_TRANSITION) {return this.isMidLife() && this.isActive() && this.isTransformed();}
		else if (phase == BossPhase.SECOND_PHASE) {return this.transitionTicks++ >= transitionTicksTreshold;}
		else {return false;}
	}

	@Override public void applyPhaseUpdateEffect(BossPhase nextPhase)
	{
		if (nextPhase == BossPhase.FIRST_TO_SECOND_TRANSITION)
		{

		}
		else if (nextPhase == BossPhase.SECOND_PHASE)
		{

		}
	}

	@Override public boolean fireImmune() {return true;}
	@Override public boolean displayFireAnimation() {return false;}
	
	@Override
	public boolean causeFallDamage(double distance, float damageMultiplier, DamageSource source) {return false;}

	@Override public Item getTrophy() {return AerialHellItems.LILITH_TROPHY.get();}

	@Override public void tickTransitionPhase()
	{
		if (!level().isClientSide())
		{
			this.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20, 10, true, false));
			this.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 1, 10, true, false));
		}
	}

	public void tickTransformingPhase()
	{
		int transformingTicks = this.getActivatingTicks();
		for (int i = 0; i<10 + transformingTicks /1.5; i++)
		{
			if (this.level().dimension() == AerialHellDimensions.AERIAL_HELL_DIMENSION) {this.transformRandomBlock();}
		}

		if (transformingTicks > 12)
		{
			int range = 15;
			List<Entity> nearbyEntities = this.level().getEntities(this, this.getBoundingBox().inflate(20), EntitySelector.withinDistance(this.getX(), this.getY(), this.getZ(), range));
			this.dragOrRepulseEntities(nearbyEntities, NearbyEntitiesInteractionInfo.DRAG_NEAR, 4.0F, range);

			for (Entity entity : nearbyEntities)
			{
				if (entity instanceof LivingEntity livingEntity && !EntityHelper.isCreaOrSpecPlayer(entity))
				{
					livingEntity.addEffect(new MobEffectInstance(AerialHellMobEffects.VULNERABILITY.getDelegate(), 40, 0));
				}
			}

			if (this.level().isClientSide()) {this.spawnTransformationParticle(5, 1.0D);}
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
		if (!LoadedConfigParams.DO_BOSS_GRIEFING) {return;}

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
				return AerialHellBlocks.SHADOW_WALL_TORCH.get().defaultBlockState().setValue(AerialHellWallTorchBlock.HORIZONTAL_FACING, blockState.getValue(AerialHellWallTorchBlock.HORIZONTAL_FACING));
			}
			else //not a wall torch
			{
				return AerialHellBlocks.SHADOW_TORCH.get().defaultBlockState();
			}
		}
		else if (blockState.is(AerialHellTags.Blocks.SOLID_ETHER))
		{
			return AerialHellBlocks.PURPLE_SOLID_ETHER.get().defaultBlockState();
		}
		else if (block == AerialHellBlocks.CRYSTAL_BLOCK.get())
		{
			return AerialHellBlocks.SHADOW_CRYSTAL_BLOCK.get().defaultBlockState();
		}
		else if (block == AerialHellBlocks.VIBRANT_SKY_CACTUS_FIBER_LANTERN.get())
		{
			return AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_LIGHT.get().defaultBlockState();
		}
		else if (block instanceof StellarGrassBlock)
		{
			return AerialHellBlocks.SHADOW_GRASS_BLOCK.get().defaultBlockState();
		}
		else if (block == AerialHellBlocks.STELLAR_GRASS.get())
		{
			return AerialHellBlocks.SHADOW_GRASS.get().defaultBlockState();
		}
		else if (block == AerialHellBlocks.STELLAR_GRASS_BALL.get())
		{
			return AerialHellBlocks.SHADOW_GRASS_BALL.get().defaultBlockState();
		}
		else if (block instanceof LanternBlock)
		{
			return AerialHellBlocks.SHADOW_LANTERN.get().defaultBlockState().setValue(LanternBlock.HANGING, blockState.getValue(LanternBlock.HANGING));
		}
		else if (block instanceof ChainBlock)
		{
			return AerialHellBlocks.SHADOW_CHAIN.get().defaultBlockState().setValue(ChainBlock.AXIS, blockState.getValue(ChainBlock.AXIS));
		}
		else if (block instanceof IronBarsBlock)
		{
			return AerialHellBlocks.SHADOW_BARS.get().defaultBlockState().setValue(IronBarsBlock.NORTH, blockState.getValue(IronBarsBlock.NORTH)).setValue(IronBarsBlock.SOUTH, blockState.getValue(IronBarsBlock.SOUTH)).setValue(IronBarsBlock.WEST, blockState.getValue(IronBarsBlock.WEST)).setValue(IronBarsBlock.EAST, blockState.getValue(IronBarsBlock.EAST));
		}
		else if (block == AerialHellBlocks.LAPIS_ROBINIA_PLANKS.get() || block == AerialHellBlocks.AERIAL_TREE_PLANKS.get() || block == AerialHellBlocks.CHISELED_AERIAL_TREE_PLANKS.get())
		{
			return AerialHellBlocks.GRAY_SHROOM_PLANKS.get().defaultBlockState();
		}
		else if (block == AerialHellBlocks.GOLDEN_BEECH_PLANKS.get() || block == AerialHellBlocks.CHISELED_GOLDEN_BEECH_PLANKS.get() || block == Blocks.DARK_OAK_PLANKS || block == AerialHellBlocks.CHISELED_AERIAL_TREE_PLANKS.get())
		{
			return AerialHellBlocks.SHADOW_PINE_PLANKS.get().defaultBlockState();
		}
		else if (block instanceof SaplingBlock)
		{
			return AerialHellBlocks.SHADOW_PINE_SAPLING.get().defaultBlockState();
		}
		else if (block instanceof LeavesBlock)
		{
			if (block == AerialHellBlocks.GOLDEN_BEECH_LEAVES.get() || block == Blocks.DARK_OAK_LEAVES) {newBlock = AerialHellBlocks.SHADOW_PINE_LEAVES.get();}
			else {newBlock = AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES.get();}
			return newBlock.defaultBlockState().setValue(LeavesBlock.PERSISTENT, blockState.getValue(LeavesBlock.PERSISTENT)).setValue(LeavesBlock.DISTANCE, blockState.getValue(LeavesBlock.DISTANCE));
		}
		else if (block instanceof CraftingTableBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_CRAFTING_TABLE.get() || block == AerialHellBlocks.AERIAL_TREE_CRAFTING_TABLE.get()) {return AerialHellBlocks.GRAY_SHROOM_CRAFTING_TABLE.get().defaultBlockState();}
			else {return AerialHellBlocks.SHADOW_PINE_CRAFTING_TABLE.get().defaultBlockState();}
		}
		else if (block instanceof StairBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_STAIRS.get() || block == AerialHellBlocks.AERIAL_TREE_STAIRS.get()) {newBlock = AerialHellBlocks.GRAY_SHROOM_STAIRS.get();}
			else if (block == Blocks.QUARTZ_STAIRS) {newBlock = AerialHellBlocks.SMOKY_QUARTZ_STAIRS.get();}
			else if (block == Blocks.SMOOTH_QUARTZ_STAIRS) {newBlock = AerialHellBlocks.SMOOTH_SMOKY_QUARTZ_STAIRS.get();}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocks.SHADOW_PINE_STAIRS.get();}
			return newBlock.defaultBlockState().setValue(StairBlock.FACING, blockState.getValue(StairBlock.FACING)).setValue(StairBlock.HALF, blockState.getValue(StairBlock.HALF)).setValue(StairBlock.SHAPE, blockState.getValue(StairBlock.SHAPE));
		}
		else if (block instanceof SlabBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_SLAB.get() || block == AerialHellBlocks.AERIAL_TREE_SLAB.get()) {newBlock = AerialHellBlocks.GRAY_SHROOM_SLAB.get();}
			else if (block == Blocks.QUARTZ_SLAB) {newBlock = AerialHellBlocks.SMOKY_QUARTZ_SLAB.get();}
			else if (block == Blocks.SMOOTH_QUARTZ_SLAB) {newBlock = AerialHellBlocks.SMOOTH_SMOKY_QUARTZ_SLAB.get();}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocks.SHADOW_PINE_SLAB.get();}
			return newBlock.defaultBlockState().setValue(SlabBlock.TYPE, blockState.getValue(SlabBlock.TYPE));
		}
		else if (block instanceof FenceBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_FENCE.get() || block == AerialHellBlocks.AERIAL_TREE_FENCE.get()) {newBlock = AerialHellBlocks.GRAY_SHROOM_FENCE.get();}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocks.SHADOW_PINE_FENCE.get();}
			return newBlock.defaultBlockState().setValue(FenceBlock.NORTH, blockState.getValue(FenceBlock.NORTH)).setValue(FenceBlock.EAST, blockState.getValue(FenceBlock.EAST)).setValue(FenceBlock.WEST, blockState.getValue(FenceBlock.WEST)).setValue(FenceBlock.SOUTH, blockState.getValue(FenceBlock.SOUTH));
		}
		else if (block instanceof FenceGateBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_GATE.get() || block == AerialHellBlocks.AERIAL_TREE_GATE.get()) {newBlock = AerialHellBlocks.GRAY_SHROOM_GATE.get();}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocks.SHADOW_PINE_GATE.get();}
			return newBlock.defaultBlockState().setValue(FenceGateBlock.FACING, blockState.getValue(FenceGateBlock.FACING)).setValue(FenceGateBlock.OPEN, blockState.getValue(FenceGateBlock.OPEN)).setValue(FenceGateBlock.POWERED, blockState.getValue(FenceGateBlock.POWERED)).setValue(FenceGateBlock.IN_WALL, blockState.getValue(FenceGateBlock.IN_WALL));
		}
		else if (block instanceof RotatedPillarBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_LOG.get() || block == AerialHellBlocks.ENCHANTED_LAPIS_ROBINIA_LOG.get() || block == AerialHellBlocks.AERIAL_TREE_LOG.get()) {newBlock = AerialHellBlocks.GIANT_VERDIGRIS_AGARIC_STEM.get();}
			else if (block == AerialHellBlocks.STRIPPED_LAPIS_ROBINIA_LOG.get() || block == AerialHellBlocks.STRIPPED_AERIAL_TREE_LOG.get()) {newBlock = AerialHellBlocks.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM.get();}
			else if (block == Blocks.QUARTZ_PILLAR) {newBlock = AerialHellBlocks.SMOKY_QUARTZ_PILLAR.get();}
			else if (block == AerialHellBlocks.STRIPPED_GOLDEN_BEECH_LOG.get() || block == Blocks.STRIPPED_DARK_OAK_LOG || block == Blocks.STRIPPED_OAK_LOG) {newBlock = AerialHellBlocks.STRIPPED_SHADOW_PINE_LOG.get();}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_LOG.get();}
			return newBlock.defaultBlockState().setValue(RotatedPillarBlock.AXIS, blockState.getValue(RotatedPillarBlock.AXIS));
		}
		else if (block == Blocks.CHISELED_QUARTZ_BLOCK) {return AerialHellBlocks.CHISELED_SMOKY_QUARTZ_BLOCK.get().defaultBlockState();}
		else if (block == Blocks.QUARTZ_BLOCK) {return AerialHellBlocks.SMOKY_QUARTZ_BLOCK.get().defaultBlockState();}
		else if (block == Blocks.QUARTZ_BRICKS) {return AerialHellBlocks.SMOKY_QUARTZ_BRICKS.get().defaultBlockState();}
		else if (block == Blocks.SMOOTH_QUARTZ) {return AerialHellBlocks.SMOOTH_SMOKY_QUARTZ.get().defaultBlockState();}
		else if (block == AerialHellBlocks.GIANT_GANODERMA_APPLANATUM_BLOCK.get()) {return AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.get().defaultBlockState();}
		else if (block instanceof AerialHellBookshelfBlock || block == Blocks.BOOKSHELF)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_BOOKSHELF.get() || block == AerialHellBlocks.AERIAL_TREE_BOOKSHELF.get()) {return AerialHellBlocks.GRAY_SHROOM_BOOKSHELF.get().defaultBlockState();}
			else {return AerialHellBlocks.SHADOW_PINE_BOOKSHELF.get().defaultBlockState();}
		}
		else if (block instanceof TrapDoorBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_TRAPDOOR.get() || block == AerialHellBlocks.AERIAL_TREE_TRAPDOOR.get()) {newBlock = AerialHellBlocks.GRAY_SHROOM_TRAPDOOR.get();}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_TRAPDOOR.get();}
			return newBlock.defaultBlockState().setValue(TrapDoorBlock.OPEN, blockState.getValue(TrapDoorBlock.OPEN)).setValue(TrapDoorBlock.HALF, blockState.getValue(TrapDoorBlock.HALF)).setValue(TrapDoorBlock.POWERED, blockState.getValue(TrapDoorBlock.POWERED)).setValue(TrapDoorBlock.FACING, blockState.getValue(TrapDoorBlock.FACING));
		}
		else if (block instanceof DoorBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_DOOR.get() || block == AerialHellBlocks.AERIAL_TREE_DOOR.get()) {newBlock = AerialHellBlocks.GRAY_SHROOM_DOOR.get();}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_DOOR.get();}
			return newBlock.defaultBlockState().setValue(DoorBlock.FACING, blockState.getValue(DoorBlock.FACING)).setValue(DoorBlock.OPEN, blockState.getValue(DoorBlock.OPEN)).setValue(DoorBlock.HINGE, blockState.getValue(DoorBlock.HINGE)).setValue(DoorBlock.POWERED, blockState.getValue(DoorBlock.POWERED)).setValue(DoorBlock.HALF, blockState.getValue(DoorBlock.HALF));
		}
		else if (block instanceof ButtonBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_BUTTON.get() || block == AerialHellBlocks.AERIAL_TREE_BUTTON.get()) {newBlock = AerialHellBlocks.GRAY_SHROOM_BUTTON.get();}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_BUTTON.get();}
			return newBlock.defaultBlockState().setValue(ButtonBlock.POWERED, blockState.getValue(ButtonBlock.POWERED)).setValue(ButtonBlock.FACE, blockState.getValue(ButtonBlock.FACE)).setValue(ButtonBlock.FACING, blockState.getValue(ButtonBlock.FACING));
		}
		else if (block instanceof PressurePlateBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_PRESSURE_PLATE.get() || block == AerialHellBlocks.AERIAL_TREE_PRESSURE_PLATE.get()) {newBlock = AerialHellBlocks.GRAY_SHROOM_PRESSURE_PLATE.get();}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_PRESSURE_PLATE.get();}
			return newBlock.defaultBlockState().setValue(PressurePlateBlock.POWERED, blockState.getValue(PressurePlateBlock.POWERED));
		}
		else if (block instanceof ComposterBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_COMPOSTER.get() || block == AerialHellBlocks.AERIAL_TREE_COMPOSTER.get()) {newBlock = AerialHellBlocks.GRAY_SHROOM_COMPOSTER.get();}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_COMPOSTER.get();}
			return newBlock.defaultBlockState().setValue(ComposterBlock.LEVEL, blockState.getValue(ComposterBlock.LEVEL));
		}
		else if (block instanceof SignBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_STANDING_SIGN.get() || block == AerialHellBlocks.AERIAL_TREE_STANDING_SIGN.get()) {newBlock = AerialHellBlocks.GRAY_SHROOM_STANDING_SIGN.get();}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_STANDING_SIGN.get();}
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
		return AerialHellBlocks.SHADOW_CATACOMBS_BRICKS.get().defaultBlockState();
	}
	
	@Override public void aiStep()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		super.aiStep();
    }
	
	@Override public boolean isPushable() {return false;}
	
	@Override public boolean doHurtTarget(ServerLevel level, Entity target)
	{
		this.level().broadcastEntityEvent(this, (byte)4);
		boolean flag = super.doHurtTarget(level, target);
		if (flag && target instanceof LivingEntity && !EntityHelper.isLivingEntityShadowImmune((LivingEntity) target))
		{
			((LivingEntity) target).addEffect(new MobEffectInstance(AerialHellMobEffects.VULNERABILITY.getDelegate(), 40, 0));
		}
		this.playSound(SoundEvents.RAVAGER_STEP, 1.0F, 0.5F);
		return flag;
	}
	
	@Override public void handleEntityEvent(byte id)
	{
		if (id == 4) {this.attackTimer = 10;}
		else if (id == 76) {this.spawnTransformationParticle(120, 10.0D);}
		else {super.handleEntityEvent(id);}
	}
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_LILITH_HURT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_LILITH_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_LILITH_DEATH.get();}
	@Override public void playAmbientSound() {if (this.isTransforming()) {} else {super.playAmbientSound();}}
	
	public void spawnTransformationParticle(int number, double areaScale)
	{
		if (this.level().isClientSide())
        {
        	for(int i = 0; i < number; ++i)
            {
            	double xSpeed = this.random.nextGaussian() * 0.02D;
            	double ySpeed = this.random.nextGaussian() * 0.02D;
            	double zSpeed = this.random.nextGaussian() * 0.02D;
				double randomY = this.getY() + (this.random.nextDouble() - 0.30D) * areaScale;
            	this.level().addParticle(AerialHellParticleTypes.SHADOW_PARTICLE.get(), this.getRandomX(areaScale), randomY, this.getRandomZ(areaScale), 2 * xSpeed, ySpeed, 2 * zSpeed);
            }
        }
        else
        {
           this.level().broadcastEntityEvent(this, (byte)20);
        }
	}
	
	/* Lilith Goals */

	public boolean isMidLife() {return this.getHealth() * 2 < this.getMaxHealth();}
	public boolean isHealthMatchToShootShadowProjectile() {return this.isMidLife();}
	public boolean isHealthMatchToSummonFlyingSkulls() {return  this.getMaxHealth() > (2.5 - this.getDifficulty() / 6.0) * this.getHealth();}

	public static class ShadowProjectileAttackGoal extends ShootProjectileGoal
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
			return AerialHellEntities.SHADOW_FLYING_SKULL.get().create(this.getGoalOwner().level(), EntitySpawnReason.MOB_SUMMONED);
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
