package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import com.google.common.collect.Maps;
import fr.factionbedrock.aerialhell.Entity.AI.ConditionalGoal;
import fr.factionbedrock.aerialhell.Entity.AI.StrikeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.*;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack.StrikeAttackInactivePhase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack.StrikeAttackPhase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.StrikeAttack.StrikeAttackPhaseType;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.MasterPartEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartInfo;
import fr.factionbedrock.aerialhell.Entity.StagedActivableEntity;
import fr.factionbedrock.aerialhell.Entity.StrikeAttackEntity;
import fr.factionbedrock.aerialhell.Entity.Util.ActivableEntityInfo;
import fr.factionbedrock.aerialhell.Entity.Util.PlaySoundHelper;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class VoluciteWardenEntity extends AbstractBossEntity implements MasterPartEntity, StagedActivableEntity, StrikeAttackEntity
{
	public static float EYE_RELATIVE_HEIGHT = 34.50F;
	public static float CORE_RELATIVE_HEIGHT = 20.50F;

	private StrikeAttackGoal STRIKE_ATTACK_GOAL;

	/* -- MasterPartEntity fields -- */
	private static final EntityDataAccessor<Integer> RIGHT_ARM_SEGMENT_1_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> RIGHT_ARM_SEGMENT_2_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> RIGHT_ARM_SEGMENT_3_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> RIGHT_ARM_SEGMENT_4_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> RIGHT_ARM_SEGMENT_5_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> RIGHT_ARM_SEGMENT_6_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> RIGHT_ARM_SEGMENT_7_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> LEFT_ARM_SEGMENT_1_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> LEFT_ARM_SEGMENT_2_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> LEFT_ARM_SEGMENT_3_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> LEFT_ARM_SEGMENT_4_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> LEFT_ARM_SEGMENT_5_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> LEFT_ARM_SEGMENT_6_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> LEFT_ARM_SEGMENT_7_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> RIGHT_LEG_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> LEFT_LEG_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> PELVIS_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> ABDOMEN_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> LOWER_CHEST_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> UPPER_CHEST_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> CORE_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> FRONT_RIGHT_CORE_RIB_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> FRONT_LEFT_CORE_RIB_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> BACK_RIGHT_CORE_RIB_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> BACK_LEFT_CORE_RIB_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> NECK_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> HEAD_ID = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT);
	private final Map<String, PartInfo> PARTS_MAP = Maps.newHashMap();
	private final PartInfo RIGHT_ARM_SEGMENT_1 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "right_arm_segment_1", 1, RIGHT_ARM_SEGMENT_1_ID, new Vec3(6.5F, 23.5F, 0.0F), true, PARTS_MAP);
	private final PartInfo RIGHT_ARM_SEGMENT_2 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "right_arm_segment_2", 2, RIGHT_ARM_SEGMENT_2_ID, new Vec3(7.5F, 20.5F, 0.0F), true, PARTS_MAP);
	private final PartInfo RIGHT_ARM_SEGMENT_3 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "right_arm_segment_3", 3, RIGHT_ARM_SEGMENT_3_ID, new Vec3(8.5F, 17.5F, 0.0F), true, PARTS_MAP);
	private final PartInfo RIGHT_ARM_SEGMENT_4 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "right_arm_segment_4", 4, RIGHT_ARM_SEGMENT_4_ID, new Vec3(8.5F, 14.5F, 0.0F), true, PARTS_MAP);
	private final PartInfo RIGHT_ARM_SEGMENT_5 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "right_arm_segment_5", 5, RIGHT_ARM_SEGMENT_5_ID, new Vec3(9.5F, 11.5F, 0.0F), true, PARTS_MAP);
	private final PartInfo RIGHT_ARM_SEGMENT_6 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "right_arm_segment_6", 6, RIGHT_ARM_SEGMENT_6_ID, new Vec3(9.5F, 8.5F, 0.0F), true, PARTS_MAP);
	private final PartInfo RIGHT_ARM_SEGMENT_7 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "right_arm_segment_7", 7, RIGHT_ARM_SEGMENT_7_ID, new Vec3(9.5F, 5.5F, 0.0F), true, PARTS_MAP);
	private final PartInfo LEFT_ARM_SEGMENT_1 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "left_arm_segment_1", 1, LEFT_ARM_SEGMENT_1_ID, new Vec3(-6.5F, 23.5F, 0.0F), false, PARTS_MAP);
	private final PartInfo LEFT_ARM_SEGMENT_2 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "left_arm_segment_2", 2, LEFT_ARM_SEGMENT_2_ID, new Vec3(-7.5F, 20.5F, 0.0F), false, PARTS_MAP);
	private final PartInfo LEFT_ARM_SEGMENT_3 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "left_arm_segment_3", 3, LEFT_ARM_SEGMENT_3_ID, new Vec3(-8.5F, 17.5F, 0.0F), false, PARTS_MAP);
	private final PartInfo LEFT_ARM_SEGMENT_4 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "left_arm_segment_4", 4, LEFT_ARM_SEGMENT_4_ID, new Vec3(-8.5F, 14.5F, 0.0F), false, PARTS_MAP);
	private final PartInfo LEFT_ARM_SEGMENT_5 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "left_arm_segment_5", 5, LEFT_ARM_SEGMENT_5_ID, new Vec3(-9.5F, 11.5F, 0.0F), false, PARTS_MAP);
	private final PartInfo LEFT_ARM_SEGMENT_6 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "left_arm_segment_6", 6, LEFT_ARM_SEGMENT_6_ID, new Vec3(-9.5F, 8.5F, 0.0F), false, PARTS_MAP);
	private final PartInfo LEFT_ARM_SEGMENT_7 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "left_arm_segment_7", 7, LEFT_ARM_SEGMENT_7_ID, new Vec3(-9.5F, 5.5F, 0.0F), false, PARTS_MAP);
	private final PartInfo RIGHT_LEG = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_LEG.get(), "right_leg", RIGHT_LEG_ID, new Vec3(2.5F, 0.0F, 0.0F), PARTS_MAP);
	private final PartInfo LEFT_LEG = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_LEG.get(), "left_leg", LEFT_LEG_ID, new Vec3(-2.5F, 0.0F, 0.0F), PARTS_MAP);
	private final PartInfo PELVIS = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_PELVIS.get(), "pelvis", PELVIS_ID, new Vec3(0.0F, 9.5F, 0.0F), PARTS_MAP);
	private final PartInfo ABDOMEN = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_ABDOMEN.get(), "abdomen", ABDOMEN_ID, new Vec3(0.0F, 12.5F, 0.0F), PARTS_MAP);
	private final PartInfo LOWER_CHEST = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_CHEST.get(), "lower_chest", LOWER_CHEST_ID, new Vec3(0.0F, 16.5F, 0.0F), PARTS_MAP);
	private final PartInfo UPPER_CHEST = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_CHEST.get(), "upper_chest", UPPER_CHEST_ID, new Vec3(0.0F, 24.5F, 0.0F), PARTS_MAP);
	private final PartInfo CORE = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_CORE.get(), "core", CORE_ID, new Vec3(0.0F, 18.5F, 0.0F), PARTS_MAP);
	private final PartInfo FRONT_RIGHT_CORE_RIB = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_CORE_RIB.get(), "front_right_core_rib", FRONT_RIGHT_CORE_RIB_ID, new Vec3(4.0F, 18.5F, 4.0F), PARTS_MAP);
	private final PartInfo FRONT_LEFT_CORE_RIB = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_CORE_RIB.get(), "front_left_core_rib", FRONT_LEFT_CORE_RIB_ID, new Vec3(-4.0F, 18.5F, 4.0F), PARTS_MAP);
	private final PartInfo BACK_RIGHT_CORE_RIB = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_CORE_RIB.get(), "back_right_core_rib", BACK_RIGHT_CORE_RIB_ID, new Vec3(4.0F, 18.5F, -4.0F), PARTS_MAP);
	private final PartInfo BACK_LEFT_CORE_RIB = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_CORE_RIB.get(), "back_left_core_rib", BACK_LEFT_CORE_RIB_ID, new Vec3(-4.0F, 18.5F, -4.0F), PARTS_MAP);
	private final PartInfo NECK = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_NECK.get(), "neck", NECK_ID, new Vec3(0.0F, 26.5F, 0.0F), PARTS_MAP);
	private final PartInfo HEAD = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_HEAD.get(), "head", HEAD_ID, new Vec3(0.0F, 30.5F, 0.0F), true, PARTS_MAP);
	/* ----------------------------- */
	/* --- StagedActivableEntity fields --- */
	private static final EntityDataAccessor<Boolean> AWAKENING = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> AWAKENED = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.BOOLEAN);
	StagedActivableEntityInfo.ActivatingPhaseParameters VOLUCITE_WARDEN_AWAKENING = PLAY_ACTIVATING_PHASE_ONLY_ONCE.copy().activatingThreshold(120).activatingStartSoundHelper(new PlaySoundHelper(AerialHellSoundEvents.ENTITY_WARDEN_VOLUCITE_GOLEM_ACTIVATION.get(), 5.0F, 1.6F));
	public final ActivableEntityInfo.ActivationMethod VOLUCITE_WARDEN_ACTIVATION_METHOD = this.AERIAL_HELL_ACTIVABLE_ACTIVATION_METHOD.copy().activateOnlyOnHitCondition((entity) -> entity instanceof VoluciteWardenEntity voluciteWarden && !voluciteWarden.alreadyActivatedOnce()); //only on hit for first activation
	public final ActivableEntityInfo VOLUCITE_WARDEN_ACTIVABLE_INFO = new ActivableEntityInfo(ACTIVE, VOLUCITE_WARDEN_ACTIVATION_METHOD);
	public final StagedActivableEntityInfo STAGED_ACTIVABLE_INFO = new StagedActivableEntityInfo(this.VOLUCITE_WARDEN_ACTIVABLE_INFO, AWAKENING, AWAKENED, VOLUCITE_WARDEN_AWAKENING);
	/* -------------------------------------- */

	public int timeDying;

	public VoluciteWardenEntity(EntityType<? extends Monster> type, Level world)
	{
		super(type, world);

		this.timeDying = 0;
		this.hurtTime = 0;
		bossInfo.setColor(BossEvent.BossBarColor.BLUE);
		bossInfo.setOverlay(BossEvent.BossBarOverlay.NOTCHED_6);
	}

	@Override protected void defineSynchedData(SynchedEntityData.Builder builder)
	{
		super.defineSynchedData(builder);

		/* -- MasterPartEntity synched data -- */
		defineAll(builder, RIGHT_ARM_SEGMENT_1_ID, RIGHT_ARM_SEGMENT_2_ID, RIGHT_ARM_SEGMENT_3_ID, RIGHT_ARM_SEGMENT_4_ID, RIGHT_ARM_SEGMENT_5_ID, RIGHT_ARM_SEGMENT_6_ID, RIGHT_ARM_SEGMENT_7_ID, LEFT_ARM_SEGMENT_1_ID, LEFT_ARM_SEGMENT_2_ID, LEFT_ARM_SEGMENT_3_ID, LEFT_ARM_SEGMENT_4_ID, LEFT_ARM_SEGMENT_5_ID, LEFT_ARM_SEGMENT_6_ID, LEFT_ARM_SEGMENT_7_ID, RIGHT_LEG_ID, LEFT_LEG_ID, PELVIS_ID, ABDOMEN_ID, LOWER_CHEST_ID, UPPER_CHEST_ID, CORE_ID, FRONT_RIGHT_CORE_RIB_ID, FRONT_LEFT_CORE_RIB_ID, BACK_RIGHT_CORE_RIB_ID, BACK_LEFT_CORE_RIB_ID, NECK_ID, HEAD_ID);
		/* ----------------------------------- */
		builder.define(AWAKENING, false);
		builder.define(AWAKENED, false);
	}

	@SafeVarargs private static void defineAll(SynchedEntityData.Builder builder, EntityDataAccessor<Integer>... dataAccessors)
	{
		for (EntityDataAccessor<Integer> dataAccessor : dataAccessors) {builder.define(dataAccessor, 0);}
	}

	/* ------- StagedActivableEntity : Interface method implementation ------- */
	@Override public StagedActivableEntityInfo getActivableInfo() {return STAGED_ACTIVABLE_INFO;}
	/* ----------------------------------------------------------------------- */

	/* ------- StagedActivableEntity : overriden methods pour specific behavior ------- */
	@Override public void onActivatingPhaseTick()
	{
		StagedActivableEntity.super.onActivatingPhaseTick();
		this.dragOrRepulseEntities(NearbyEntitiesInteractionInfo.REPULSE_UNIFORM, 500.0F, 30, 30);
	}
	/* -------------------------------------------------------------------------------- */

	/* ------------------------------------------------------------------------- */
	/* ---------- MasterPartEntity : Interface methods implementation ---------- */
	/* ------------------------------------------------------------------------- */
	@Override public Map<String, PartInfo> getPartInfoMap() {return this.PARTS_MAP;}
	/* ------------------------------------------------------------------------- */
	/* ------------------------------------------------------------------------- */
	/* ------------------------------------------------------------------------- */

	/* ----------------------------------------------------------------------------------------------- */
	/* ---------- MasterPartEntity : Superclass methods Overridden to delegate to interface ---------- */
	/* ----------------------------------------------------------------------------------------------- */
	@Override @Nullable public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficultyIn, EntitySpawnReason reason, @Nullable SpawnGroupData spawnDataIn)
	{
		return this.finalizePartSpawn(level, difficultyIn, reason, spawnDataIn);
	}

	@Override public final boolean hurtServer(ServerLevel level, DamageSource source, float amount)
	{
		boolean superDamaged = super.hurtServer(level, source, amount);
		this.partHurtServer(superDamaged, level, source, amount);
		return superDamaged;
	}

	@Override public void tick()
	{
		super.tick();
		this.partEntityTick();
		this.tickStrikeAttack();

		//additional things (specific to the volucite warden)
		if (!this.isInDeadOrDyingPhase()) {this.timeDying = 0;}
	}

	@Override public void aiStep()
	{
		super.aiStep();
		this.partAiStep();

		//additional things (specific to the volucite warden)
		if (!this.level().isClientSide())
		{
			//the update is done client side by vanilla (in LivingEntity aiStep), but not server-side.
			//in the volucite warden, walkAnimation can be used to adjust arm segment position while moving
			this.calculateEntityAnimation(false);
		}
	}

	@Override public void addAdditionalSaveData(ValueOutput valueOutput)
	{
		super.addAdditionalSaveData(valueOutput);
		this.partAddAdditionalSaveData(valueOutput);
	}

	@Override public void readAdditionalSaveData(ValueInput valueInput)
	{
		super.readAdditionalSaveData(valueInput);
		this.partReadAdditionalSaveData(valueInput);
	}

	@Override public void push(Entity other)
	{
		if (!this.partCanBePushedBy(other)) {return;}
		super.push(other);
	}

	@Override public boolean is(Entity other) {return super.is(other) || this.recognizesChildPart(other);}
	/* ----------------------------------------------------------------------------------------------- */
	/* ----------------------------------------------------------------------------------------------- */
	/* ----------------------------------------------------------------------------------------------- */

	/* ------------------------------------------------------------------------------------------- */
	/* ---------- MasterPartEntity : Interface methods Overridden for specific behavior ---------- */
	/* ------------------------------------------------------------------------------------------- */
	@Override public void onPartSummoned(PartInfo partInfo)
	{
		MasterPartEntity.super.onPartSummoned(partInfo);
		if (partInfo == this.UPPER_CHEST && partInfo.getPart() != null && partInfo.getPart().getSelf() instanceof VoluciteWardenChestPartEntity chestPartEntity)
		{
			chestPartEntity.setRenderOff();
		}
	}
	/* ------------------------------------------------------------------------------------------- */
	/* ------------------------------------------------------------------------------------------- */
	/* ------------------------------------------------------------------------------------------- */

	/* --------------------------------------------------------------------------------------------------- */
	/* ----------- MasterPartEntity : Superclass methods Overridden for part-specific behavior ----------- */
	/* --------------------------------------------------------------------------------------------------- */
	@Override public double getEyeY() {return this.position().y + EYE_RELATIVE_HEIGHT;}

	@Override public boolean isAttackable() {return false;} //makes damage is not called when a player left-clicks on the hitbox, but the left-click hitbox collision still happen
	@Override public boolean isPickable() {return false;} //disables left-click and right-click hitbox collision

	@Override protected void doPush(Entity entity) {}
	@Override public void push(Vec3 vector) {}

	@Override public boolean removeWhenFarAway(double distanceToClosestPlayer) {return false;}
	/* --------------------------------------------------------------------------------------------------- */
	/* --------------------------------------------------------------------------------------------------- */
	/* --------------------------------------------------------------------------------------------------- */

	/* --------------------------------------------------------------------- */
	/* ------- Other methods to override for specific part behaviors ------- */
	/* --------------------------------------------------------------------- */
	@Override public Vec3 adjustPartOffset(PartInfo partInfo, PartEntity partEntity, Vec3 masterPos, Vec3 unadjustedPosOffset)
	{
		if (partInfo instanceof ArmPartInfo armPartInfo)
		{
			boolean useArmFalloff = false;
			Vec3 shoulderPosOffset = armPartInfo.getPosOffsetFromShoulder(unadjustedPosOffset);
			float swing = this.walkAnimation.position();
			float swingAmount = this.walkAnimation.speed() * 0.5F;
			float direction = armPartInfo.isRightArm() ? 1.0F : -1.0F;

			float shoulderAngle = direction * Mth.triangleWave(swing, 42.0F) * swingAmount;
			if (useArmFalloff) {shoulderAngle *= armPartInfo.getFalloff();}

			double cos = Math.cos(shoulderAngle);
			double sin = Math.sin(shoulderAngle);

			double x = shoulderPosOffset.x;
			double y = shoulderPosOffset.y * cos - shoulderPosOffset.z * sin;
			double z = shoulderPosOffset.y * sin + shoulderPosOffset.z * cos;

			shoulderPosOffset = new Vec3(x, y, z);
			return armPartInfo.getPosOffsetFromMaster(shoulderPosOffset);
		}
		else {return MasterPartEntity.super.adjustPartOffset(partInfo, partEntity, masterPos, unadjustedPosOffset);}
	}
	/* --------------------------------------------------------------------- */
	/* --------------------------------------------------------------------- */
	/* --------------------------------------------------------------------- */

	/* ----------------------------------------------------------------------------------- */
	/* ----------- MasterPartEntity : Other methods for part-specific behavior ----------- */
	/* ----------------------------------------------------------------------------------- */
	public boolean recognizesLeftLegPart(@Nullable Entity potentialLeg) {return potentialLeg != null && potentialLeg == this.LEFT_LEG.getPart();}
	public boolean recognizesRightLegPart(@Nullable Entity potentialLeg) {return potentialLeg != null && potentialLeg == this.RIGHT_LEG.getPart();}
	public boolean recognizesLeftArmSegmentPart(@Nullable Entity potentialArmSegment) {return potentialArmSegment != null && potentialArmSegment == this.LEFT_ARM_SEGMENT_1.getPart() || potentialArmSegment == this.LEFT_ARM_SEGMENT_2.getPart() || potentialArmSegment == this.LEFT_ARM_SEGMENT_3.getPart() || potentialArmSegment == this.LEFT_ARM_SEGMENT_4.getPart() || potentialArmSegment == this.LEFT_ARM_SEGMENT_5.getPart() || potentialArmSegment == this.LEFT_ARM_SEGMENT_6.getPart() || potentialArmSegment == this.LEFT_ARM_SEGMENT_7.getPart();}
	public boolean recognizesRightArmSegmentPart(@Nullable Entity potentialArmSegment) {return potentialArmSegment != null && potentialArmSegment == this.RIGHT_ARM_SEGMENT_1.getPart() || potentialArmSegment == this.RIGHT_ARM_SEGMENT_2.getPart() || potentialArmSegment == this.RIGHT_ARM_SEGMENT_3.getPart() || potentialArmSegment == this.RIGHT_ARM_SEGMENT_4.getPart() || potentialArmSegment == this.RIGHT_ARM_SEGMENT_5.getPart() || potentialArmSegment == this.RIGHT_ARM_SEGMENT_6.getPart() || potentialArmSegment == this.RIGHT_ARM_SEGMENT_7.getPart();}
	/* ----------------------------------------------------------------------------------- */
	/* ----------------------------------------------------------------------------------- */
	/* ----------------------------------------------------------------------------------- */

	@Override protected void registerGoals()
    {
		this.STRIKE_ATTACK_GOAL = new StrikeAttackGoal(this, 0.2F);
		this.targetSelector.addGoal(2, new ConditionalGoal(this, new NearestAttackableTargetGoal<>(this, Player.class, true)));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 16.0F));
		this.goalSelector.addGoal(4, STRIKE_ATTACK_GOAL);
		//this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		//this.goalSelector.addGoal(4, new ConditionalGoal(this, new MeleeAttackGoal(this, 1.25D, false)));
		//this.goalSelector.addGoal(4, new ConditionalGoal(this, new DirectMeleeAttackGoal(this, 1.25D, 4.0D)));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MudCycleMageEntity.class, true));
    }

	public static AttributeSupplier.Builder registerAttributes()
    {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 1400.0D)
				.add(Attributes.FOLLOW_RANGE, 32.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 5.0D)
				.add(Attributes.ATTACK_DAMAGE, 25.0D);
    }

	@Override public int getPhaseIdToSkipToDyingPhase() {return BossPhase.SECOND_TO_THIRD_TRANSITION.getPhaseId();}
	@Override public boolean enableTickPhaseUpdate(BossPhaseTickType type) {return false;}
	@Override public boolean enableTryDyingPhaseUpdate() {return true;}

	@Override public void applyPhaseUpdateEffect(BossPhase nextPhase)
	{
		if (nextPhase == BossPhase.FIRST_TO_SECOND_TRANSITION) {this.playTransitionSound();}
		if (nextPhase == BossPhase.SECOND_PHASE) {this.playTransitionSound();}
		if (nextPhase == BossPhase.DYING) {this.playDeathSound();}
	}

	@Override public boolean fireImmune() {return true;}
	@Override public boolean displayFireAnimation() {return false;}

	@Override public boolean causeFallDamage(double distance, float damageMultiplier, DamageSource source) {return false;}

	@Override public void tickTransitionPhase()
	{
		this.runTransitionEffect();
		float newHealth = this.getHealth() + 7.5F;
		if (newHealth < this.getMaxHealth()) {this.setHealth(newHealth);}
		else {this.updateToNextPhase();}
	}

	@Override public void tickDyingPhase()
	{
		this.dragOrRepulseEntities(NearbyEntitiesInteractionInfo.REPULSE_FAR, 40.0F);
		this.timeDying++;
		if (this.timeDying > 140) {this.tryDying(this.lastDamageSource == null ? this.damageSources().generic() : this.lastDamageSource);}
	}

	@Override public void tickDeadPhase() {}

	@Override public Item getTrophy() {return AerialHellItems.VOLUCITE_ORE.get();}

	protected void runTransitionEffect()
	{
		this.dragOrRepulseEntities(NearbyEntitiesInteractionInfo.REPULSE_UNIFORM, 120.0F);
	}
	
	@Override public boolean isPushable() {return false;}
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_WARDEN_VOLUCITE_GOLEM_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_WARDEN_VOLUCITE_GOLEM_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_WARDEN_VOLUCITE_GOLEM_DEATH.get();}
    protected SoundEvent getFastDeathSound() {return AerialHellSoundEvents.ENTITY_WARDEN_VOLUCITE_GOLEM_DEATH.get();}

	public void playDeathSound() {this.playSound(this.getDeathSound(), 5.0F, 1.0F);}
	@Override public void playFastDeathSound() {this.playSound(getFastDeathSound(), this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);}
	public void playTransitionSound() {this.playSound(AerialHellSoundEvents.ENTITY_WARDEN_VOLUCITE_GOLEM_ACTIVATION.get(), 5.0F, 1.0F);}

	@Override protected void playHurtSound(DamageSource damageSource, boolean died)
	{
		if (damageSource.is(DamageTypes.GENERIC_KILL) && this.isDeadOrDying()) {return;} //tryDying method is already playing death sound

		if (died)
		{
			SoundEvent soundevent = this.getDeathSound();
			if (soundevent != null) {this.playSound(soundevent, this.getSoundVolume(), this.getVoicePitch());}
		}
		else {this.playHurtSound(damageSource);}
	}

	private static class ArmPartInfo extends PartInfo
	{
		private final int segmentIndex;
		private final boolean isRightArm;

		public ArmPartInfo(EntityType<?> type, String name, int segmentIndex, EntityDataAccessor<Integer> entityIdDataAccessor, Vec3 relativePositionOffset, boolean isRightArm, Map<String, PartInfo> partsMap)
		{
			super(type, name, entityIdDataAccessor, relativePositionOffset, false, partsMap);
			this.segmentIndex = segmentIndex;
			this.isRightArm = isRightArm;
		}

		public int getSegmentIndex() {return segmentIndex;}
		public boolean isRightArm() {return this.isRightArm;}
		public boolean isLeftArm() {return !this.isRightArm;}

		public Vec3 getShoulderPosOffsetFromMaster()
		{
			return new Vec3(this.isRightArm() ? 5.0F : -5.0F, 24.5F, 0.0F);
		}

		//relative positioning transformation from master to shoulder
		public Vec3 getPosOffsetFromShoulder(Vec3 posOffsetFromMaster)
		{
			return this.getUnrotatedRelativePositionOffset().subtract(this.getShoulderPosOffsetFromMaster());
		}

		//relative positioning transformation from shoulder to master
		public Vec3 getPosOffsetFromMaster(Vec3 posOffsetFromShoulder)
		{
			return posOffsetFromShoulder.add(this.getShoulderPosOffsetFromMaster());
		}

		public float getFalloff()
		{
			switch (this.segmentIndex)
			{
				case 1 -> {return 1.0F;}
				case 2 -> {return 0.85F;}
				case 3 -> {return 0.7F;}
				case 4 -> {return 0.55F;}
				case 5 -> {return 0.4F;}
				case 6 -> {return 0.25F;}
				case 7 -> {return 0.1F;}
				default -> {return 1.0F;}
            }
		}
	}

	@Override protected float getDragOrRepulseSourcePosRelativeY() {return CORE_RELATIVE_HEIGHT;}

	private int inactiveStrikeAttackTicks;

	private void tickStrikeAttack()
	{
		if (!this.level().isClientSide())
		{
			PartEntity hand = this.RIGHT_ARM_SEGMENT_7.getPart();
			if (hand == null) {return;}
			if (this.STRIKE_ATTACK_GOAL.isActive())
			{
				this.inactiveStrikeAttackTicks = 0;
			}
			else
			{
				this.inactiveStrikeAttackTicks++;
				if (this.inactiveStrikeAttackTicks > 80 && this.getTarget() != null)
				{
					this.STRIKE_ATTACK_GOAL.trigger();
				}
			}
		}
	}

	@Override @Nullable public Vec3 calculatePartPos(PartInfo partInfo, double masterX, double masterY, double masterZ)
	{
		if (partInfo instanceof ArmPartInfo armPartinfo && armPartinfo.isRightArm() && this.STRIKE_ATTACK_GOAL.isActive())
		{
			Vec3 armStartPos = this.RIGHT_ARM_SEGMENT_1.getUnrotatedRelativePositionOffset();
			Vec3 armEndPos = this.STRIKE_ATTACK_GOAL.getCachedUnrotatedRelativePos();
			double curveStrengthFactor = this.STRIKE_ATTACK_GOAL.getPhaseType() == StrikeAttackPhaseType.RECOVERY ? this.calculateRecoveryCurveStrengthFactor(this.STRIKE_ATTACK_GOAL.getDistanceToTarget()) : 1.0D;
			Vec3 armPos = this.interpolateArmPos(armStartPos, armEndPos, curveStrengthFactor, armPartinfo.segmentIndex, 7);
			return this.fromUnrotatedRelativeToLevelPos(armPos);
		}
		return MasterPartEntity.super.calculatePartPos(partInfo, masterX, masterY, masterZ);
	}

	/* --------------------------------------------------------------------------- */
	/* ---------- StrikeAttackEntity : Interface methods implementation ---------- */
	/* --------------------------------------------------------------------------- */

	private final List<StrikeAttackPhase> strikeAttackSequence = List.of(
		new StrikeAttackPhase(StrikeAttackPhaseType.WINDUP, this::getRelativeWindupPos0, 1.0D, 1),
		new StrikeAttackPhase(StrikeAttackPhaseType.WINDUP, this::getRelativeWindupPos1, 1.0D, 1),
		new StrikeAttackPhase(StrikeAttackPhaseType.WINDUP, this::getRelativeWindupPos2, 1.0D, 1),
		new StrikeAttackPhase(StrikeAttackPhaseType.WINDUP, this::getRelativeWindupPos3, 1.0D, 40),
		new StrikeAttackPhase(StrikeAttackPhaseType.STRIKE, this::getRelativeStrikePos, 2.0D, 5),
		new StrikeAttackPhase(StrikeAttackPhaseType.RECOVERY, this::getRelativeRecoveryPos, 0.4D, 1),
		new StrikeAttackInactivePhase()
	);

	@Override public List<StrikeAttackPhase> getStrikeAttackSequence() {return this.strikeAttackSequence;}

	@Override public boolean canUseStrikeAttack() {return this.getTarget() != null;}

	@Override public boolean shouldTrigger() {return false;}

	@Override public void strike()
	{
		//strike effect (sound effect, particles ?)
	}

	/* --------------------------------------------------------------------------- */
	/* --------------------------------------------------------------------------- */
	/* --------------------------------------------------------------------------- */

	private double calculateRecoveryCurveStrengthFactor(double distanceToTarget)
	{
		int maxFactorDistance = 4;
		return Mth.clamp(distanceToTarget / maxFactorDistance, 0.0F, 1.0F);
	}

	private Vec3 interpolateArmPos(Vec3 start, Vec3 end, double curveStrengthFactor, int index, int totalSegments)
	{
		double progress = (double)(index - 1) / (totalSegments - 1);

		Vec3 armMiddle = start.add(end).scale(0.5);

		Vec3 armDir = end.subtract(start).normalize();

		double heightDiff = end.y - start.y;
		float heightDiffMaxThreshold = 14.0F;
		double factor = Mth.clamp(heightDiff / heightDiffMaxThreshold, -1.0, 1.0); // negative if arm down, positive if arm up. 0 if arm is horizontal. (absolute) starting to decrease if diff is <= heightDiffMaxThreshold
		Vec3 controlDir = new Vec3(armDir.z, 0, factor * armDir.x).normalize(); //orthogonal direction

		double curveStrength = curveStrengthFactor * switch (this.STRIKE_ATTACK_GOAL.getCurrentPhase().getType())
		{
			case INACTIVE -> 0.0D;
			case WINDUP -> 8.0D * Mth.abs((float)factor);
			case STRIKE -> 2.0D;
			case RECOVERY -> 5.0D;
		};

		Vec3 control = armMiddle.add(controlDir.scale(curveStrength));

		return quadraticBezier(start, control, end, progress);
	}

	private Vec3 quadraticBezier(Vec3 start, Vec3 control, Vec3 end, double progress)
	{
		double remainingProgress = 1.0 - progress;

		Vec3 startWeight = start.scale(remainingProgress * remainingProgress);
		Vec3 controlWeight = control.scale(2 * remainingProgress * progress);
		Vec3 endWeight = end.scale(progress * progress);

		return startWeight.add(controlWeight).add(endWeight);
	}

	private Vec3 getRelativeWindupPos0() {return new Vec3(12.0F, 8.5F, 4.0F);}
	private Vec3 getRelativeWindupPos1() {return new Vec3(20.0F, 18.0F, 8.0F);}
	private Vec3 getRelativeWindupPos2() {return new Vec3(18.0F, 31.0F, 4.0F);}
	private Vec3 getRelativeWindupPos3() {return new Vec3(10.0F, 37.0F, 0.0F);}

	private Vec3 getRelativeStrikePos()
	{
		if (this.getTarget() == null) {return new Vec3(0.0F, 9.0F, 10.0F);}//default strike pos
		else {return this.toUnrotatedRelativePos(this.getTarget().position());}
	}

	private Vec3 getRelativeRecoveryPos() {return new Vec3(9.5F, 5.5F, 0.0F);}
}
