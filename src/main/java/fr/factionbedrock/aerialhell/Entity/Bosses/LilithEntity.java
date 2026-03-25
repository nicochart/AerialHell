package fr.factionbedrock.aerialhell.Entity.Bosses;

import java.util.List;

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
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class LilithEntity extends AbstractBossEntity implements StagedActivableEntity
{
	public int attackTimer;
	private int transitionTicks;
	private final int transitionTicksTreshold = 20;

	private LilithSummonShadowFlyingSkullGoal SUMMON_FLYING_SKULL_GOAL;
	private ShadowProjectileAttackGoal SHADOW_PROJECTILE_ATTACK_GOAL;

	/* --- StagedActivableEntity fields --- */
	private static final TrackedData<Boolean> TRANSFORMING = DataTracker.registerData(LilithEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	private static final TrackedData<Boolean> TRANSFORMED = DataTracker.registerData(LilithEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	StagedActivableEntityInfo.ActivatingPhaseParameters LILITH_TRANSFORMING_PARAMETERS = PLAY_ACTIVATING_PHASE_ONLY_ONCE.copy().activatingThreshold(160).activatingStartSoundHelper(new PlaySoundHelper(AerialHellSoundEvents.ENTITY_LILITH_TRANSFORMATION, 5.0F, 1.0F));
	public final StagedActivableEntityInfo STAGED_ACTIVABLE_INFO = new StagedActivableEntityInfo(this.ACTIVABLE_INFO, TRANSFORMING, TRANSFORMED, LILITH_TRANSFORMING_PARAMETERS);
	/* -------------------------------------- */
	
	public LilithEntity(EntityType<? extends HostileEntity> type, World world)
	{
		super(type, world);
		this.attackTimer = 0;
		this.transitionTicks = 0; this.hurtTime = 0;
		bossInfo.setColor(BossBar.Color.PURPLE);
		bossInfo.setStyle(BossBar.Style.NOTCHED_6);
	}

	@Override protected void initDataTracker(DataTracker.Builder builder)
	{
		super.initDataTracker(builder);
		builder.add(TRANSFORMING, false);
		builder.add(TRANSFORMED, false);
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
		this.getEntityWorld().sendEntityStatus(this, (byte) 76); //particles display needs broadcast
		if (this.getEntityWorld().getRegistryKey() == AerialHellDimensions.AERIAL_HELL_DIMENSION) {this.transformAllBlocks();}
	}

	@Override public boolean needsActivatingTicksSyncClientSide() {return true;} //for particles in
	/* -------------------------------------------------------------------------------- */

	/* ------- StagedActivableEntity : alias method to clarity lilith's behavior in code ------- */
	public boolean isTransformed() {return this.alreadyActivatedOnce();}
	public boolean isTransforming() {return this.isActivating();}
	/* ---------------------------------------------------------------------------------------------- */

	@Override protected void initGoals()
	{
		this.SUMMON_FLYING_SKULL_GOAL = new LilithSummonShadowFlyingSkullGoal(this);
		this.SHADOW_PROJECTILE_ATTACK_GOAL = new ShadowProjectileAttackGoal(this);
		this.targetSelector.add(2, new ConditionalGoal(this, new ActiveTargetGoal<>(this, PlayerEntity.class, true)));
		this.targetSelector.add(1, new RevengeGoal(this));
		this.goalSelector.add(3, new ConditionalGoal(this, new MeleeAttackGoal(this, 1.25D, false)));
		this.goalSelector.add(2, this.SUMMON_FLYING_SKULL_GOAL);
		this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(5, new ConditionalGoal(this, new WanderAroundFarGoal(this, 0.6D)));
		this.targetSelector.add(3, new ActiveTargetGoal<>(this, MudCycleMageEntity.class, true));
		this.goalSelector.add(2, this.SHADOW_PROJECTILE_ATTACK_GOAL);
	}
	
	public static DefaultAttributeContainer.Builder registerAttributes()
    {
		return HostileEntity.createHostileAttributes()
				.add(EntityAttributes.MAX_HEALTH, 600.0D)
				.add(EntityAttributes.FOLLOW_RANGE, 32.0D)
				.add(EntityAttributes.MOVEMENT_SPEED, 0.3D)
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.1D)
				.add(EntityAttributes.ATTACK_KNOCKBACK, 1.0D)
				.add(EntityAttributes.ATTACK_DAMAGE, 20.0D);
    }
	
	@Override public boolean damage(ServerWorld serverWorld, DamageSource source, float amount)
	{
		Entity immediateSourceEntity = source.getSource();
		Entity trueSourceEntity = source.getAttacker();
		if (this.isTransforming() && !source.isSourceCreativePlayer() && !source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)) {return false;}
		if (this.getMaxHealth() < 2.5 * this.getHealth() && immediateSourceEntity instanceof PersistentProjectileEntity) {return false;}
		boolean flag = super.damage(serverWorld, source, amount);
		if (flag)
		{
			if (trueSourceEntity instanceof LivingEntity && !(immediateSourceEntity instanceof PersistentProjectileEntity))
			{
				if (!EntityHelper.isCreativePlayer(trueSourceEntity))
				{
					this.setTarget((LivingEntity) trueSourceEntity);
				}
			}
		}
		return flag;
	}

	@Override public int getPhaseIdToSkipToDyingPhase() {return BossPhase.SECOND_TO_THIRD_TRANSITION.getPhaseId();}

	@Override public boolean shouldUpdateToPhase(BossPhase phase)
	{
		if (phase == BossPhase.FIRST_TO_SECOND_TRANSITION) {return this.isMidLife() && this.isActive() && this.isTransformed();}
		else if (phase == BossPhase.SECOND_PHASE) {return this.transitionTicks++ >= this.transitionTicksTreshold;}
		else {return false;}
	}

	@Override public void applyPhaseUpdateEffect(BossPhase nextPhase)
	{
		if (nextPhase == BossPhase.FIRST_TO_SECOND_TRANSITION)
		{
			this.playSound(SoundEvents.ENTITY_RAVAGER_HURT, 1.0F, 0.1F);
			if (!this.getEntityWorld().isClient()) {this.SHADOW_PROJECTILE_ATTACK_GOAL.triggerShootAllNow();}
		}
		else if (nextPhase == BossPhase.SECOND_PHASE)
		{
			if (!this.getEntityWorld().isClient()) {this.SUMMON_FLYING_SKULL_GOAL.triggerNow();}
		}
	}

	@Override public boolean isFireImmune() {return true;}
	@Override public boolean doesRenderOnFire() {return false;}
	
	@Override public boolean handleFallDamage(double distance, float damageMultiplier, DamageSource source) {return false;}

	@Override public Item getTrophy() {return AerialHellItems.LILITH_TROPHY;}

	@Override public void tickTransitionPhase()
	{
		this.runTransitionEffect();

		if (!this.getEntityWorld().isClient())
		{
			this.addStatusEffect(new StatusEffectInstance(new StatusEffectInstance(StatusEffects.SLOWNESS, 20, 10, true, false)));
			this.addStatusEffect(new StatusEffectInstance(new StatusEffectInstance(StatusEffects.RESISTANCE, 1, 10, true, false)));
		}
	}

	protected void runTransitionEffect()
	{
		if (this.getEntityWorld().isClient()) {this.spawnTransformationParticle( 10, 2.0D);}
		this.dragOrRepulseEntities(NearbyEntitiesInteractionInfo.REPULSE_NEAR, 120.0F);
	}

	public void tickTransformingPhase()
	{
		int transformingTicks = this.getActivatingTicks();
		for (int i = 0; i<10 + transformingTicks /1.5; i++)
		{
			if (this.getEntityWorld().getRegistryKey() == AerialHellDimensions.AERIAL_HELL_DIMENSION) {this.transformRandomBlock();}
		}

		if (transformingTicks > 12)
		{
			int range = 15;
			List<LivingEntity> nearbyEntities = EntityHelper.getTargetableLivingEntitiesInInflatedBoundingBox(this, 20, EntityPredicates.maxDistance(this.getX(), this.getY(), this.getZ(), range));
			this.dragOrRepulseEntities(nearbyEntities, NearbyEntitiesInteractionInfo.DRAG_NEAR, 4.0F, range);

			for (Entity entity : nearbyEntities)
			{
				if (entity instanceof LivingEntity livingEntity && !EntityHelper.isCreaOrSpecPlayer(entity))
				{
					livingEntity.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.VULNERABILITY, 40, 0));
				}
			}

			if (this.getEntityWorld().isClient()) {this.spawnTransformationParticle(5, 1.0D);}
		}
	}

	private void transformRandomBlock()
	{
		int maxHorizontalDistance = 14;
		int maxVerticalDistance = 10;
		int x = random.nextInt(2*maxHorizontalDistance) - maxHorizontalDistance;
		int y = random.nextInt(2*maxVerticalDistance) - maxVerticalDistance;
		int z = random.nextInt(2*maxHorizontalDistance) - maxHorizontalDistance;
		BlockPos transformationPos = new BlockPos(this.getBlockPos().add(new Vec3i(x, y, z)));
		if (this.getEntityWorld().getBlockState(transformationPos).isIn(AerialHellTags.Blocks.LILITH_TRANSFORMABLE))
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
					BlockPos transformationPos = new BlockPos(this.getBlockPos().add(new Vec3i(x, y, z)));
					if (this.getEntityWorld().getBlockState(transformationPos).isIn(AerialHellTags.Blocks.LILITH_TRANSFORMABLE))
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

		if (this.getEntityWorld().getBlockState(pos).getBlock() instanceof DoorBlock)
		{
			DoubleBlockHalf half = this.getEntityWorld().getBlockState(pos).get(DoorBlock.HALF);
			if (half == DoubleBlockHalf.LOWER)
			{
				this.getEntityWorld().breakBlock(pos, false);
				this.getEntityWorld().breakBlock(pos.up(), false);
			}
			else
			{
				this.getEntityWorld().breakBlock(pos.down(), false);
				this.getEntityWorld().breakBlock(pos, false);
			}
		}
		else {this.getEntityWorld().setBlockState(pos, getEquivalentShadowBlockstate(this.getEntityWorld().getBlockState(pos)));}
	}

	private BlockState getEquivalentShadowBlockstate(BlockState blockState)
	{
		Block block = blockState.getBlock(), newBlock;
		if (block instanceof AerialHellTorchBlock)
		{
			if (block instanceof AerialHellWallTorchBlock)
			{
				return AerialHellBlocks.SHADOW_WALL_TORCH.getDefaultState().with(AerialHellWallTorchBlock.HORIZONTAL_FACING, blockState.get(AerialHellWallTorchBlock.HORIZONTAL_FACING));
			}
			else //not a wall torch
			{
				return AerialHellBlocks.SHADOW_TORCH.getDefaultState();
			}
		}
		else if (blockState.isIn(AerialHellTags.Blocks.SOLID_ETHER))
		{
			return AerialHellBlocks.PURPLE_SOLID_ETHER.getDefaultState();
		}
		else if (block == AerialHellBlocks.CRYSTAL_BLOCK)
		{
			return AerialHellBlocks.SHADOW_CRYSTAL_BLOCK.getDefaultState();
		}
		else if (block == AerialHellBlocks.VIBRANT_SKY_CACTUS_FIBER_LANTERN)
		{
			return AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_LIGHT.getDefaultState();
		}
		else if (block instanceof StellarGrassBlock)
		{
			return AerialHellBlocks.SHADOW_GRASS_BLOCK.getDefaultState();
		}
		else if (block == AerialHellBlocks.STELLAR_GRASS)
		{
			return AerialHellBlocks.SHADOW_GRASS.getDefaultState();
		}
		else if (block == AerialHellBlocks.STELLAR_GRASS_BALL)
		{
			return AerialHellBlocks.SHADOW_GRASS_BALL.getDefaultState();
		}
		else if (block instanceof LanternBlock)
		{
			return AerialHellBlocks.SHADOW_LANTERN.getDefaultState().with(LanternBlock.HANGING, blockState.get(LanternBlock.HANGING));
		}
		else if (block instanceof ChainBlock)
		{
			return AerialHellBlocks.SHADOW_CHAIN.getDefaultState().with(ChainBlock.AXIS, blockState.get(ChainBlock.AXIS));
		}
		else if (block instanceof PaneBlock)
		{
			return AerialHellBlocks.SHADOW_BARS.getDefaultState().with(PaneBlock.NORTH, blockState.get(PaneBlock.NORTH)).with(PaneBlock.SOUTH, blockState.get(PaneBlock.SOUTH)).with(PaneBlock.WEST, blockState.get(PaneBlock.WEST)).with(PaneBlock.EAST, blockState.get(PaneBlock.EAST));
		}
		else if (block == AerialHellBlocks.LAPIS_ROBINIA_PLANKS || block == AerialHellBlocks.AERIAL_TREE_PLANKS || block == AerialHellBlocks.CHISELED_AERIAL_TREE_PLANKS)
		{
			return AerialHellBlocks.GRAY_SHROOM_PLANKS.getDefaultState();
		}
		else if (block == AerialHellBlocks.GOLDEN_BEECH_PLANKS || block == AerialHellBlocks.CHISELED_GOLDEN_BEECH_PLANKS || block == Blocks.DARK_OAK_PLANKS || block == AerialHellBlocks.CHISELED_AERIAL_TREE_PLANKS)
		{
			return AerialHellBlocks.SHADOW_PINE_PLANKS.getDefaultState();
		}
		else if (block instanceof SaplingBlock)
		{
			return AerialHellBlocks.SHADOW_PINE_SAPLING.getDefaultState();
		}
		else if (block instanceof LeavesBlock)
		{
			if (block == AerialHellBlocks.GOLDEN_BEECH_LEAVES || block == Blocks.DARK_OAK_LEAVES) {newBlock = AerialHellBlocks.SHADOW_PINE_LEAVES;}
			else {newBlock = AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES;}
			return newBlock.getDefaultState().with(LeavesBlock.PERSISTENT, blockState.get(LeavesBlock.PERSISTENT)).with(LeavesBlock.DISTANCE, blockState.get(LeavesBlock.DISTANCE));
		}
		else if (block instanceof CraftingTableBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_CRAFTING_TABLE || block == AerialHellBlocks.AERIAL_TREE_CRAFTING_TABLE) {return AerialHellBlocks.GRAY_SHROOM_CRAFTING_TABLE.getDefaultState();}
			else {return AerialHellBlocks.SHADOW_PINE_CRAFTING_TABLE.getDefaultState();}
		}
		else if (block instanceof StairsBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_STAIRS || block == AerialHellBlocks.AERIAL_TREE_STAIRS) {newBlock = AerialHellBlocks.GRAY_SHROOM_STAIRS;}
			else if (block == Blocks.QUARTZ_STAIRS) {newBlock = AerialHellBlocks.SMOKY_QUARTZ_STAIRS;}
			else if (block == Blocks.SMOOTH_QUARTZ_STAIRS) {newBlock = AerialHellBlocks.SMOOTH_SMOKY_QUARTZ_STAIRS;}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocks.SHADOW_PINE_STAIRS;}
			return newBlock.getDefaultState().with(StairsBlock.FACING, blockState.get(StairsBlock.FACING)).with(StairsBlock.HALF, blockState.get(StairsBlock.HALF)).with(StairsBlock.SHAPE, blockState.get(StairsBlock.SHAPE));
		}
		else if (block instanceof SlabBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_SLAB || block == AerialHellBlocks.AERIAL_TREE_SLAB) {newBlock = AerialHellBlocks.GRAY_SHROOM_SLAB;}
			else if (block == Blocks.QUARTZ_SLAB) {newBlock = AerialHellBlocks.SMOKY_QUARTZ_SLAB;}
			else if (block == Blocks.SMOOTH_QUARTZ_SLAB) {newBlock = AerialHellBlocks.SMOOTH_SMOKY_QUARTZ_SLAB;}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocks.SHADOW_PINE_SLAB;}
			return newBlock.getDefaultState().with(SlabBlock.TYPE, blockState.get(SlabBlock.TYPE));
		}
		else if (block instanceof FenceBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_FENCE || block == AerialHellBlocks.AERIAL_TREE_FENCE) {newBlock = AerialHellBlocks.GRAY_SHROOM_FENCE;}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocks.SHADOW_PINE_FENCE;}
			return newBlock.getDefaultState().with(FenceBlock.NORTH, blockState.get(FenceBlock.NORTH)).with(FenceBlock.EAST, blockState.get(FenceBlock.EAST)).with(FenceBlock.WEST, blockState.get(FenceBlock.WEST)).with(FenceBlock.SOUTH, blockState.get(FenceBlock.SOUTH));
		}
		else if (block instanceof FenceGateBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_GATE || block == AerialHellBlocks.AERIAL_TREE_GATE) {newBlock = AerialHellBlocks.GRAY_SHROOM_GATE;}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocks.SHADOW_PINE_GATE;}
			return newBlock.getDefaultState().with(FenceGateBlock.FACING, blockState.get(FenceGateBlock.FACING)).with(FenceGateBlock.OPEN, blockState.get(FenceGateBlock.OPEN)).with(FenceGateBlock.POWERED, blockState.get(FenceGateBlock.POWERED)).with(FenceGateBlock.IN_WALL, blockState.get(FenceGateBlock.IN_WALL));
		}
		else if (block instanceof PillarBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_LOG || block == AerialHellBlocks.ENCHANTED_LAPIS_ROBINIA_LOG || block == AerialHellBlocks.AERIAL_TREE_LOG) {newBlock = AerialHellBlocks.GIANT_VERDIGRIS_AGARIC_STEM;}
			else if (block == AerialHellBlocks.STRIPPED_LAPIS_ROBINIA_LOG || block == AerialHellBlocks.STRIPPED_AERIAL_TREE_LOG) {newBlock = AerialHellBlocks.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM;}
			else if (block == Blocks.QUARTZ_PILLAR) {newBlock = AerialHellBlocks.SMOKY_QUARTZ_PILLAR;}
			else if (block == AerialHellBlocks.STRIPPED_GOLDEN_BEECH_LOG || block == Blocks.STRIPPED_DARK_OAK_LOG || block == Blocks.STRIPPED_OAK_LOG) {newBlock = AerialHellBlocks.STRIPPED_SHADOW_PINE_LOG;}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_LOG;}
			return newBlock.getDefaultState().with(PillarBlock.AXIS, blockState.get(PillarBlock.AXIS));
		}
		else if (block == Blocks.CHISELED_QUARTZ_BLOCK) {return AerialHellBlocks.CHISELED_SMOKY_QUARTZ_BLOCK.getDefaultState();}
		else if (block == Blocks.QUARTZ_BLOCK) {return AerialHellBlocks.SMOKY_QUARTZ_BLOCK.getDefaultState();}
		else if (block == Blocks.QUARTZ_BRICKS) {return AerialHellBlocks.SMOKY_QUARTZ_BRICKS.getDefaultState();}
		else if (block == Blocks.SMOOTH_QUARTZ) {return AerialHellBlocks.SMOOTH_SMOKY_QUARTZ.getDefaultState();}
		else if (block == AerialHellBlocks.GIANT_GANODERMA_APPLANATUM_BLOCK) {return AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.getDefaultState();}
		else if (blockState.isIn(AerialHellTags.Blocks.ENCHANTMENT_POWER_PROVIDER) || block == Blocks.BOOKSHELF)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_BOOKSHELF || block == AerialHellBlocks.AERIAL_TREE_BOOKSHELF) {return AerialHellBlocks.GRAY_SHROOM_BOOKSHELF.getDefaultState();}
			else {return AerialHellBlocks.SHADOW_PINE_BOOKSHELF.getDefaultState();}
		}
		else if (block instanceof TrapdoorBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_TRAPDOOR || block == AerialHellBlocks.AERIAL_TREE_TRAPDOOR) {newBlock = AerialHellBlocks.GRAY_SHROOM_TRAPDOOR;}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_TRAPDOOR;}
			return newBlock.getDefaultState().with(TrapdoorBlock.OPEN, blockState.get(TrapdoorBlock.OPEN)).with(TrapdoorBlock.HALF, blockState.get(TrapdoorBlock.HALF)).with(TrapdoorBlock.POWERED, blockState.get(TrapdoorBlock.POWERED)).with(TrapdoorBlock.FACING, blockState.get(TrapdoorBlock.FACING));
		}
		else if (block instanceof DoorBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_DOOR || block == AerialHellBlocks.AERIAL_TREE_DOOR) {newBlock = AerialHellBlocks.GRAY_SHROOM_DOOR;}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_DOOR;}
			return newBlock.getDefaultState().with(DoorBlock.FACING, blockState.get(DoorBlock.FACING)).with(DoorBlock.OPEN, blockState.get(DoorBlock.OPEN)).with(DoorBlock.HINGE, blockState.get(DoorBlock.HINGE)).with(DoorBlock.POWERED, blockState.get(DoorBlock.POWERED)).with(DoorBlock.HALF, blockState.get(DoorBlock.HALF));
		}
		else if (block instanceof ButtonBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_BUTTON || block == AerialHellBlocks.AERIAL_TREE_BUTTON) {newBlock = AerialHellBlocks.GRAY_SHROOM_BUTTON;}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_BUTTON;}
			return newBlock.getDefaultState().with(ButtonBlock.POWERED, blockState.get(ButtonBlock.POWERED)).with(ButtonBlock.FACE, blockState.get(ButtonBlock.FACE)).with(ButtonBlock.FACING, blockState.get(ButtonBlock.FACING));
		}
		else if (block instanceof PressurePlateBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_PRESSURE_PLATE || block == AerialHellBlocks.AERIAL_TREE_PRESSURE_PLATE) {newBlock = AerialHellBlocks.GRAY_SHROOM_PRESSURE_PLATE;}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_PRESSURE_PLATE;}
			return newBlock.getDefaultState().with(PressurePlateBlock.POWERED, blockState.get(PressurePlateBlock.POWERED));
		}
		else if (block instanceof ComposterBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_COMPOSTER || block == AerialHellBlocks.AERIAL_TREE_COMPOSTER) {newBlock = AerialHellBlocks.GRAY_SHROOM_COMPOSTER;}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_COMPOSTER;}
			return newBlock.getDefaultState().with(ComposterBlock.LEVEL, blockState.get(ComposterBlock.LEVEL));
		}
		else if (block instanceof AbstractSignBlock)
		{
			if (block == AerialHellBlocks.LAPIS_ROBINIA_STANDING_SIGN || block == AerialHellBlocks.AERIAL_TREE_STANDING_SIGN) {newBlock = AerialHellBlocks.GRAY_SHROOM_STANDING_SIGN;}
			else {newBlock = AerialHellBlocks.SHADOW_PINE_STANDING_SIGN;}
			if (block instanceof SignBlock)
			{
				return newBlock.getDefaultState().with(SignBlock.ROTATION, blockState.get(SignBlock.ROTATION)).with(SignBlock.WATERLOGGED, blockState.get(SignBlock.WATERLOGGED));
			}
			else if (block instanceof WallSignBlock)
			{
				return newBlock.getDefaultState().with(WallSignBlock.FACING, blockState.get(WallSignBlock.FACING)).with(WallSignBlock.WATERLOGGED, blockState.get(WallSignBlock.WATERLOGGED));
			}
			return newBlock.getDefaultState();
		}
		return AerialHellBlocks.SHADOW_CATACOMBS_BRICKS.getDefaultState();
	}
	
	@Override public void tickMovement()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		super.tickMovement();
    }
	
	@Override public boolean isPushable() {return false;}
	
	@Override public boolean tryAttack(ServerWorld serverWorld, Entity target)
	{
		this.getEntityWorld().sendEntityStatus(this, (byte)4);
		boolean flag = super.tryAttack(serverWorld, target);
		if (flag && target instanceof LivingEntity && !EntityHelper.isLivingEntityShadowImmune((LivingEntity) target))
		{
			((LivingEntity) target).addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.VULNERABILITY, 40, 0));
		}
		this.playSound(SoundEvents.ENTITY_RAVAGER_STEP, 1.0F, 0.5F);
		return flag;
	}
	
	@Override public void handleStatus(byte id)
	{
		if (id == 4) {this.attackTimer = 10;}
		else if (id == 76) {this.spawnTransformationParticle(120, 10.0D);}
		else {super.handleStatus(id);}
	}
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_LILITH_HURT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_LILITH_HURT;}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_LILITH_DEATH;}
	@Override public void playAmbientSound() {if (this.isTransforming()) {} else {super.playAmbientSound();}}

	public void spawnTransformationParticle(int number, double areaScale)
	{
		if (this.getEntityWorld().isClient())
		{
			for(int i = 0; i < number; ++i)
			{
				double xSpeed = this.random.nextGaussian() * 0.02D;
				double ySpeed = this.random.nextGaussian() * 0.02D;
				double zSpeed = this.random.nextGaussian() * 0.02D;
				double randomY = this.getY() + (this.random.nextDouble() - 0.30D) * areaScale;
				this.getEntityWorld().addParticleClient(AerialHellParticleTypes.SHADOW_PARTICLE, this.getParticleX(areaScale), randomY, this.getParticleZ(areaScale), 2 * xSpeed, ySpeed, 2 * zSpeed);
			}
		}
		else
		{
			this.getEntityWorld().sendEntityStatus(this, (byte)20);
		}
	}
	
	/* Lilith Goals */

	public boolean isMidLife() {return this.getHealth() * 2 < this.getMaxHealth();}
	public boolean isHealthMatchToShootShadowProjectile() {return this.isMidLife();}
	public boolean isHealthMatchToSummonFlyingSkulls() {return  this.getMaxHealth() > (2.5F - this.getDifficulty() / 5.0F) * this.getHealth();}

	public static class ShadowProjectileAttackGoal extends ShootProjectileGoal
	{
		public ShadowProjectileAttackGoal(LilithEntity entity) {super(entity);}

		public void triggerShootAllNow()
		{
			this.shootAll(this.getParentEntity().getTarget(), (potentialTarget) -> this.getDefaultTargetPredicate().test(potentialTarget) && !potentialTarget.getType().isIn(AerialHellTags.Entities.SHADOW));
			this.resetTask();
		}

		@Override public boolean canStart()
		{
			LilithEntity lilith = (LilithEntity)this.getParentEntity();
			if (!lilith.isActive()) {return false;}
			LivingEntity target = lilith.getTarget();
			return super.canStart() && lilith.isHealthMatchToShootShadowProjectile() && target.isAlive() && lilith.canTarget(target);
		}

		@Override public ProjectileEntity createProjectile(World world, LivingEntity shooter, double accX, double accY, double accZ)
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

	public static class LilithSummonShadowFlyingSkullGoal extends SummonThreeEntitiesGoal
	{
		public LilithSummonShadowFlyingSkullGoal(LilithEntity entity) {super(entity, 0.2D);}

		public LilithEntity getLilithGoalOwner() {return (LilithEntity) this.getGoalOwner();}

		public void triggerNow()
		{
			this.summonEntities();
			this.playEffect();
			this.resetTask();
		}

		@Override public boolean canStart()
		{
			LilithEntity lilith = this.getLilithGoalOwner();
			return super.canStart() && lilith.isHealthMatchToSummonFlyingSkulls() && lilith.isActive();
		}

		@Override public Entity createEntity()
		{
			return AerialHellEntities.SHADOW_FLYING_SKULL.create(this.getGoalOwner().getEntityWorld(), SpawnReason.MOB_SUMMONED);
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
