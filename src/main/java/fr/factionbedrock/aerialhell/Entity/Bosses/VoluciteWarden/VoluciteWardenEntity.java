package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import com.google.common.collect.Maps;
import fr.factionbedrock.aerialhell.Entity.AI.ConditionalGoal;
import fr.factionbedrock.aerialhell.Entity.AI.PersistentHurtByTargetGoal;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmBeamAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmGoal;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmSummonHealBeaconGoal;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmLightningAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmStrikeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmSummonAllyGoal;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmSweepAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenCoreLightningGoal;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenCoreRepulsionGoal;
import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenMainBeamAttackGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.*;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmGoalType;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmsBeamAttackHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmsGoalHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmsSummonHealBeaconHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmsLightningAttackHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmsStrikeAttackHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmsSummonAllyHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmsSweepAttackHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.CoreRepulsionHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.MainBeamAttackHandler;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.MasterPartEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartInfo;
import fr.factionbedrock.aerialhell.Entity.StagedActivableEntity;
import fr.factionbedrock.aerialhell.Entity.StrikeAttackEntity;
import fr.factionbedrock.aerialhell.Entity.Util.ActivableEntityInfo;
import fr.factionbedrock.aerialhell.Entity.Util.DisplayParticlesHelper;
import fr.factionbedrock.aerialhell.Entity.Util.VoluciteGlyphParticles;
import fr.factionbedrock.aerialhell.Entity.Util.PlaySoundHelper;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class VoluciteWardenEntity extends AbstractBossEntity implements MasterPartEntity, StagedActivableEntity, StrikeAttackEntity
{
	public static float EYE_RELATIVE_HEIGHT = 34.50F;
	public static float CORE_BOTTOM_RELATIVE_HEIGHT = 18.50F;
	public static float CORE_CENTER_RELATIVE_HEIGHT = 21.50F; //used for particles display

	/* -- Main Beam Attack fields -- */
	private VoluciteWardenMainBeamAttackGoal MAIN_BEAM_ATTACK_GOAL;
	public final MainBeamAttackHandler mainBeamAttackHandler;
	/* ----------------------------- */

	/* -- Arms Strike Attack fields -- */
	private VoluciteWardenArmStrikeAttackGoal RIGHT_ARM_STRIKE_ATTACK_GOAL;
	private VoluciteWardenArmStrikeAttackGoal LEFT_ARM_STRIKE_ATTACK_GOAL;
	public static final EntityDataAccessor<Boolean> HAS_STRIKE_ACTIVE = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_STRIKING = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.BOOLEAN);
	public final ArmsStrikeAttackHandler armsStrikeAttackHandler;
	/* ------------------------------- */

	/* -- Arms Beam Attack fields -- */
	private VoluciteWardenArmBeamAttackGoal RIGHT_ARM_BEAM_ATTACK_GOAL;
	private VoluciteWardenArmBeamAttackGoal LEFT_ARM_BEAM_ATTACK_GOAL;
	public final ArmsBeamAttackHandler armsBeamAttackHandler;
	/* ----------------------------- */

	/* -- Arms Summon Ally fields -- */
	private VoluciteWardenArmSummonAllyGoal RIGHT_ARM_SUMMON_ALLY_GOAL;
	private VoluciteWardenArmSummonAllyGoal LEFT_ARM_SUMMON_ALLY_GOAL;
	public final ArmsSummonAllyHandler armsSummonAllyHandler;
	/* ----------------------------- */

	/* -- Arms Lightning Attack fields -- */
	private VoluciteWardenArmLightningAttackGoal RIGHT_ARM_LIGHTNING_ATTACK_GOAL;
	private VoluciteWardenArmLightningAttackGoal LEFT_ARM_LIGHTNING_ATTACK_GOAL;
	public final ArmsLightningAttackHandler armsLightningAttackHandler;
	/* ---------------------------------- */

	/* -- Arms Sweep Attack fields -- */
	private VoluciteWardenArmSweepAttackGoal RIGHT_ARM_SWEEP_ATTACK_GOAL;
	private VoluciteWardenArmSweepAttackGoal LEFT_ARM_SWEEP_ATTACK_GOAL;
	public final ArmsSweepAttackHandler armsSweepAttackHandler;
	/* ------------------------------ */

	/* -- Arms Summon Heal Beacon fields -- */
	private VoluciteWardenArmSummonHealBeaconGoal RIGHT_ARM_SUMMON_HEAL_BEACON_GOAL;
	private VoluciteWardenArmSummonHealBeaconGoal LEFT_ARM_SUMMON_HEAL_BEACON_GOAL;
	public final ArmsSummonHealBeaconHandler armsSummonHealBeaconHandler;
	/* ------------------------------------ */

	/* -- Arms Goal fields -- */
	public final ArmsGoalHandler armsGoalHandler;
	/* ----------------------- */

	/* -- Core Repulsion fields -- */
	private VoluciteWardenCoreRepulsionGoal CORE_REPULSION_GOAL;
	public static final EntityDataAccessor<Integer> CORE_DRAG_OR_REPULSE_GOAL_STATUS = SynchedEntityData.defineId(VoluciteWardenEntity.class, EntityDataSerializers.INT); //reserved to core repulsion goal
	public static final int CORE_NEUTRAL_STATUS = 0, CORE_ATTRACTING_STATUS = 1, CORE_REPULSING_STATUS = 2;
	public final CoreRepulsionHandler coreRepulsionHandler;
	/* --------------------------- */

	/* -- Core Lightning fields -- */
	private VoluciteWardenCoreLightningGoal CORE_LIGHTNING_GOAL;
	/* --------------------------- */

	/* -- Standing Players fields -- */
	public static final int STILL_TIMEOUT = 10; //ticks the warden stays still after a player stood on it for the last time
	public static final float STANDING_DETECTION_HEIGHT = 1.5F; //a player this close above the top of a collidable part is considered standing on it (jumps included)
	private int stillTimeout;
	/* ----------------------------- */

	/* -- Boss phases fields -- */
	//phase 1 : activation (invincible). shield phases (2, 4, 6) : health is the shield. vulnerable phases (3, 5, 7) : health is the real health. phase 8 : final phase (real health, no shield)
	public static final BossPhase ACTIVATION_PHASE = BossPhase.FIRST_PHASE;
	public static final List<BossPhase> SHIELD_TRANSITIONS = List.of(BossPhase.FIRST_TO_SECOND_TRANSITION, BossPhase.THIRD_TO_FOURTH_TRANSITION, BossPhase.FIFTH_TO_SIXTH_TRANSITION); //index = shield level - 1
	public static final List<BossPhase> SHIELD_PHASES = List.of(BossPhase.SECOND_PHASE, BossPhase.FOURTH_PHASE, BossPhase.SIXTH_PHASE); //index = shield level - 1
	public static final List<BossPhase> SHIELD_BREAK_TRANSITIONS = List.of(BossPhase.SECOND_TO_THIRD_TRANSITION, BossPhase.FOURTH_TO_FIFTH_TRANSITION, BossPhase.SIXTH_TO_SEVENTH_TRANSITION);
	public static final List<BossPhase> VULNERABLE_PHASES = List.of(BossPhase.THIRD_PHASE, BossPhase.FIFTH_PHASE, BossPhase.SEVENTH_PHASE);
	public static final BossPhase FINAL_TRANSITION = BossPhase.SEVENTH_TO_EIGHTH_TRANSITION; //real health reached 0 in a vulnerable phase. real health regenerates
	public static final BossPhase FINAL_PHASE = BossPhase.EIGHTH_PHASE;
	public static final float SHIELD_LEVEL_2_HEALTH = 0.66F, SHIELD_LEVEL_3_HEALTH = 0.33F; //real health (relative to max health) under which shield level 2 or 3 is used
	public static final int SHIELD_TRANSITION_DURATION = 40, SHIELD_BREAK_TRANSITION_DURATION = 40, FINAL_TRANSITION_DURATION = 100; //ticks
	public static final int VULNERABLE_PHASE_DURATION = 200; //ticks before the shield regenerates
	public static final float VULNERABLE_PHASE_MAX_DAMAGE = 0.35F; //max real health lost per vulnerable phase, relative to max health. the shield regenerates when reached
	public static final BossEvent.BossBarColor SHIELD_BOSS_BAR_COLOR = BossEvent.BossBarColor.WHITE, REAL_HEALTH_BOSS_BAR_COLOR = BossEvent.BossBarColor.BLUE;
	private float realHealth; //saved when a shield transition starts, restored when the shield breaks
	private float vulnerableHealthFloor; //real health can't go below it during current vulnerable phase. <= 0 : the real health can reach 0 (final transition)
	private int phaseTicks; //ticks spent in current phase
	//transitions animation
	public static final float LOWERED_HEAD_PITCH = 45.0F, LOWERED_HEAD_YAW = 35.0F; //shield break transitions & vulnerable phases : "disconnected" warden, head lowered toward bottom left or bottom right
	public static final float LOWERING_HEAD_SPEED = 3.0F, RAISING_HEAD_SPEED = 1.5F; //degrees per tick. head slowly raised during shield transitions
	public static final int ARMS_RAISE_DURATION = 40; //ticks to raise arms horizontally during the final transition
	public static final float INWARD_PARTICLES_MIN_RADIUS = 16.0F, INWARD_PARTICLES_MAX_RADIUS = 32.0F; //shield break transitions : glyph particles toward the core
	public static final float OUTWARD_PARTICLES_MIN_DISTANCE = 8.0F, OUTWARD_PARTICLES_MAX_DISTANCE = 20.0F; //shield & final transitions : glyph particles from the core (players repulsed from the core)
	public static final int TRANSITION_PARTICLES_PER_TICK = 3;
	private float loweredHeadYRot; //chosen when the shield breaks (bottom left or bottom right). server side
	/* ------------------------ */

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
	private final ArmPartInfo RIGHT_ARM_SEGMENT_1 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM_SEGMENT.get(), "right_arm_segment_1", 1, RIGHT_ARM_SEGMENT_1_ID, new Vec3(6.5F, 23.5F, 0.0F), true, PARTS_MAP);
	private final ArmPartInfo RIGHT_ARM_SEGMENT_2 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM_SEGMENT.get(), "right_arm_segment_2", 2, RIGHT_ARM_SEGMENT_2_ID, new Vec3(7.5F, 20.5F, 0.0F), true, PARTS_MAP);
	private final ArmPartInfo RIGHT_ARM_SEGMENT_3 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM_SEGMENT.get(), "right_arm_segment_3", 3, RIGHT_ARM_SEGMENT_3_ID, new Vec3(8.5F, 17.5F, 0.0F), true, PARTS_MAP);
	private final ArmPartInfo RIGHT_ARM_SEGMENT_4 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM_SEGMENT.get(), "right_arm_segment_4", 4, RIGHT_ARM_SEGMENT_4_ID, new Vec3(8.5F, 14.5F, 0.0F), true, PARTS_MAP);
	private final ArmPartInfo RIGHT_ARM_SEGMENT_5 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM_SEGMENT.get(), "right_arm_segment_5", 5, RIGHT_ARM_SEGMENT_5_ID, new Vec3(9.5F, 11.5F, 0.0F), true, PARTS_MAP);
	private final ArmPartInfo RIGHT_ARM_SEGMENT_6 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM_SEGMENT.get(), "right_arm_segment_6", 6, RIGHT_ARM_SEGMENT_6_ID, new Vec3(9.5F, 8.5F, 0.0F), true, PARTS_MAP);
	private final ArmPartInfo RIGHT_ARM_SEGMENT_7 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM_SEGMENT.get(), "right_arm_segment_7", 7, RIGHT_ARM_SEGMENT_7_ID, new Vec3(9.5F, 5.5F, 0.0F), true, PARTS_MAP);
	private final ArmPartInfo LEFT_ARM_SEGMENT_1 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM_SEGMENT.get(), "left_arm_segment_1", 1, LEFT_ARM_SEGMENT_1_ID, new Vec3(-6.5F, 23.5F, 0.0F), false, PARTS_MAP);
	private final ArmPartInfo LEFT_ARM_SEGMENT_2 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM_SEGMENT.get(), "left_arm_segment_2", 2, LEFT_ARM_SEGMENT_2_ID, new Vec3(-7.5F, 20.5F, 0.0F), false, PARTS_MAP);
	private final ArmPartInfo LEFT_ARM_SEGMENT_3 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM_SEGMENT.get(), "left_arm_segment_3", 3, LEFT_ARM_SEGMENT_3_ID, new Vec3(-8.5F, 17.5F, 0.0F), false, PARTS_MAP);
	private final ArmPartInfo LEFT_ARM_SEGMENT_4 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM_SEGMENT.get(), "left_arm_segment_4", 4, LEFT_ARM_SEGMENT_4_ID, new Vec3(-8.5F, 14.5F, 0.0F), false, PARTS_MAP);
	private final ArmPartInfo LEFT_ARM_SEGMENT_5 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM_SEGMENT.get(), "left_arm_segment_5", 5, LEFT_ARM_SEGMENT_5_ID, new Vec3(-9.5F, 11.5F, 0.0F), false, PARTS_MAP);
	private final ArmPartInfo LEFT_ARM_SEGMENT_6 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM_SEGMENT.get(), "left_arm_segment_6", 6, LEFT_ARM_SEGMENT_6_ID, new Vec3(-9.5F, 8.5F, 0.0F), false, PARTS_MAP);
	private final ArmPartInfo LEFT_ARM_SEGMENT_7 = new ArmPartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM_SEGMENT.get(), "left_arm_segment_7", 7, LEFT_ARM_SEGMENT_7_ID, new Vec3(-9.5F, 5.5F, 0.0F), false, PARTS_MAP);
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
	StagedActivableEntityInfo.ActivatingPhaseParameters VOLUCITE_WARDEN_AWAKENING = PLAY_ACTIVATING_PHASE_ONLY_ONCE.copy().activatingThreshold(120).activatingStartSoundHelper(new PlaySoundHelper(AerialHellSoundEvents.ENTITY_VOLUCITE_WARDEN_ACTIVATION.get(), 5.0F, 1.6F));
	public final ActivableEntityInfo.ActivationMethod VOLUCITE_WARDEN_ACTIVATION_METHOD = this.AERIAL_HELL_ACTIVABLE_ACTIVATION_METHOD.copy().activateOnlyOnHitCondition((entity) -> entity instanceof VoluciteWardenEntity voluciteWarden && !voluciteWarden.alreadyActivatedOnce()); //only on hit for first activation
	public final ActivableEntityInfo VOLUCITE_WARDEN_ACTIVABLE_INFO = new ActivableEntityInfo(ACTIVE, VOLUCITE_WARDEN_ACTIVATION_METHOD);
	public final StagedActivableEntityInfo STAGED_ACTIVABLE_INFO = new StagedActivableEntityInfo(this.VOLUCITE_WARDEN_ACTIVABLE_INFO, AWAKENING, AWAKENED, VOLUCITE_WARDEN_AWAKENING);
	/* -------------------------------------- */

	private final List<ArmPartInfo> RIGHT_ARM = List.of(RIGHT_ARM_SEGMENT_1, RIGHT_ARM_SEGMENT_2, RIGHT_ARM_SEGMENT_3, RIGHT_ARM_SEGMENT_4, RIGHT_ARM_SEGMENT_5, RIGHT_ARM_SEGMENT_6, RIGHT_ARM_SEGMENT_7);
	private final List<ArmPartInfo> LEFT_ARM = List.of(LEFT_ARM_SEGMENT_1, LEFT_ARM_SEGMENT_2, LEFT_ARM_SEGMENT_3, LEFT_ARM_SEGMENT_4, LEFT_ARM_SEGMENT_5, LEFT_ARM_SEGMENT_6, LEFT_ARM_SEGMENT_7);
	public int timeDying;

	public VoluciteWardenEntity(EntityType<? extends Monster> type, Level world)
	{
		super(type, world);

		this.lookControl = new VoluciteWardenLookControl();
		this.timeDying = 0;
		this.hurtTime = 0;
		this.realHealth = this.getMaxHealth();
		bossInfo.setColor(REAL_HEALTH_BOSS_BAR_COLOR);
		bossInfo.setOverlay(BossEvent.BossBarOverlay.NOTCHED_6);

		this.mainBeamAttackHandler = new MainBeamAttackHandler(this, () -> this.HEAD, this.MAIN_BEAM_ATTACK_GOAL);
		this.armsStrikeAttackHandler = new ArmsStrikeAttackHandler(this, this.getRightArm(), this.RIGHT_ARM_STRIKE_ATTACK_GOAL, this.getLeftArm(), this.LEFT_ARM_STRIKE_ATTACK_GOAL);
		this.armsBeamAttackHandler = new ArmsBeamAttackHandler(this, this.getRightArm(), this.RIGHT_ARM_BEAM_ATTACK_GOAL, this.getLeftArm(), this.LEFT_ARM_BEAM_ATTACK_GOAL);
		this.armsSummonAllyHandler = new ArmsSummonAllyHandler(this, this.getRightArm(), this.RIGHT_ARM_SUMMON_ALLY_GOAL, this.getLeftArm(), this.LEFT_ARM_SUMMON_ALLY_GOAL);
		this.armsLightningAttackHandler = new ArmsLightningAttackHandler(this, this.getRightArm(), this.RIGHT_ARM_LIGHTNING_ATTACK_GOAL, this.getLeftArm(), this.LEFT_ARM_LIGHTNING_ATTACK_GOAL);
		this.armsSweepAttackHandler = new ArmsSweepAttackHandler(this, this.getRightArm(), this.RIGHT_ARM_SWEEP_ATTACK_GOAL, this.getLeftArm(), this.LEFT_ARM_SWEEP_ATTACK_GOAL);
		this.armsSummonHealBeaconHandler = new ArmsSummonHealBeaconHandler(this, this.getRightArm(), this.RIGHT_ARM_SUMMON_HEAL_BEACON_GOAL, this.getLeftArm(), this.LEFT_ARM_SUMMON_HEAL_BEACON_GOAL);
		this.armsGoalHandler = new ArmsGoalHandler(this);
		this.coreRepulsionHandler = new CoreRepulsionHandler(this, this.CORE_REPULSION_GOAL);
	}

	@Override protected void defineSynchedData(SynchedEntityData.Builder builder)
	{
		super.defineSynchedData(builder);

		/* -- MasterPartEntity synched data -- */
		defineAll(builder, RIGHT_ARM_SEGMENT_1_ID, RIGHT_ARM_SEGMENT_2_ID, RIGHT_ARM_SEGMENT_3_ID, RIGHT_ARM_SEGMENT_4_ID, RIGHT_ARM_SEGMENT_5_ID, RIGHT_ARM_SEGMENT_6_ID, RIGHT_ARM_SEGMENT_7_ID, LEFT_ARM_SEGMENT_1_ID, LEFT_ARM_SEGMENT_2_ID, LEFT_ARM_SEGMENT_3_ID, LEFT_ARM_SEGMENT_4_ID, LEFT_ARM_SEGMENT_5_ID, LEFT_ARM_SEGMENT_6_ID, LEFT_ARM_SEGMENT_7_ID, RIGHT_LEG_ID, LEFT_LEG_ID, PELVIS_ID, ABDOMEN_ID, LOWER_CHEST_ID, UPPER_CHEST_ID, CORE_ID, FRONT_RIGHT_CORE_RIB_ID, FRONT_LEFT_CORE_RIB_ID, BACK_RIGHT_CORE_RIB_ID, BACK_LEFT_CORE_RIB_ID, NECK_ID, HEAD_ID);
		/* ----------------------------------- */
		builder.define(AWAKENING, false);
		builder.define(AWAKENED, false);
		builder.define(HAS_STRIKE_ACTIVE, false);
		builder.define(IS_STRIKING, false);
		builder.define(CORE_DRAG_OR_REPULSE_GOAL_STATUS, CORE_NEUTRAL_STATUS);
	}

	@SafeVarargs private static void defineAll(SynchedEntityData.Builder builder, EntityDataAccessor<Integer>... dataAccessors)
	{
		for (EntityDataAccessor<Integer> dataAccessor : dataAccessors) {builder.define(dataAccessor, 0);}
	}

	public List<ArmPartInfo> getRightArm() {return RIGHT_ARM;}
	public List<ArmPartInfo> getLeftArm() {return LEFT_ARM;}

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

		this.mainBeamAttackHandler.tick();
		this.armsStrikeAttackHandler.tick();
		this.armsBeamAttackHandler.tick();
		this.armsSummonAllyHandler.tick();
		this.armsGoalHandler.tick();
		this.coreRepulsionHandler.tick();

		//additional things (specific to the volucite warden)
		if (!this.isInDeadOrDyingPhase()) {this.timeDying = 0;}
		if (!this.level().isClientSide()) {this.tickPhases(); this.tickStill();}
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

		//additional things (specific to the volucite warden)
		valueOutput.putFloat("real_health", this.realHealth);
		valueOutput.putFloat("vulnerable_health_floor", this.vulnerableHealthFloor);
		valueOutput.putInt("phase_ticks", this.phaseTicks);
	}

	@Override public void readAdditionalSaveData(ValueInput valueInput)
	{
		super.readAdditionalSaveData(valueInput);
		this.partReadAdditionalSaveData(valueInput);

		//additional things (specific to the volucite warden)
		this.realHealth = valueInput.getFloatOr("real_health", this.getMaxHealth());
		this.vulnerableHealthFloor = valueInput.getFloatOr("vulnerable_health_floor", 0.0F);
		this.phaseTicks = valueInput.getIntOr("phase_ticks", 0);
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
			if (this.getPhase() == FINAL_TRANSITION) {shoulderPosOffset = this.getRaisedArmOffset(shoulderPosOffset, direction);} //server side (phaseTicks)
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
	public boolean recognizesLeftArmSegmentPart(@Nullable Entity potentialArmSegment) {return potentialArmSegment != null && (potentialArmSegment == this.LEFT_ARM_SEGMENT_1.getPart() || potentialArmSegment == this.LEFT_ARM_SEGMENT_2.getPart() || potentialArmSegment == this.LEFT_ARM_SEGMENT_3.getPart() || potentialArmSegment == this.LEFT_ARM_SEGMENT_4.getPart() || potentialArmSegment == this.LEFT_ARM_SEGMENT_5.getPart() || potentialArmSegment == this.LEFT_ARM_SEGMENT_6.getPart() || potentialArmSegment == this.LEFT_ARM_SEGMENT_7.getPart());}
	public boolean recognizesRightArmSegmentPart(@Nullable Entity potentialArmSegment) {return potentialArmSegment != null && (potentialArmSegment == this.RIGHT_ARM_SEGMENT_1.getPart() || potentialArmSegment == this.RIGHT_ARM_SEGMENT_2.getPart() || potentialArmSegment == this.RIGHT_ARM_SEGMENT_3.getPart() || potentialArmSegment == this.RIGHT_ARM_SEGMENT_4.getPart() || potentialArmSegment == this.RIGHT_ARM_SEGMENT_5.getPart() || potentialArmSegment == this.RIGHT_ARM_SEGMENT_6.getPart() || potentialArmSegment == this.RIGHT_ARM_SEGMENT_7.getPart());}
	/* ----------------------------------------------------------------------------------- */
	/* ----------------------------------------------------------------------------------- */
	/* ----------------------------------------------------------------------------------- */

	@Override protected void registerGoals()
    {
		this.MAIN_BEAM_ATTACK_GOAL = new VoluciteWardenMainBeamAttackGoal(this, () -> this.HEAD);
		this.RIGHT_ARM_STRIKE_ATTACK_GOAL = new VoluciteWardenArmStrikeAttackGoal(this, this::getRightArm, this::getRightArmSegment7, 0.2F);
		this.LEFT_ARM_STRIKE_ATTACK_GOAL = new VoluciteWardenArmStrikeAttackGoal(this, this::getLeftArm, this::getLeftArmSegment7, 0.2F);
		this.RIGHT_ARM_BEAM_ATTACK_GOAL = new VoluciteWardenArmBeamAttackGoal(this, this::getRightArm, 0.2F);
		this.LEFT_ARM_BEAM_ATTACK_GOAL = new VoluciteWardenArmBeamAttackGoal(this, this::getLeftArm, 0.2F);
		this.RIGHT_ARM_SUMMON_ALLY_GOAL = new VoluciteWardenArmSummonAllyGoal(this, this::getRightArm, 0.2F);
		this.LEFT_ARM_SUMMON_ALLY_GOAL = new VoluciteWardenArmSummonAllyGoal(this, this::getLeftArm, 0.2F);
		this.RIGHT_ARM_LIGHTNING_ATTACK_GOAL = new VoluciteWardenArmLightningAttackGoal(this, this::getRightArm, 0.2F);
		this.LEFT_ARM_LIGHTNING_ATTACK_GOAL = new VoluciteWardenArmLightningAttackGoal(this, this::getLeftArm, 0.2F);
		this.RIGHT_ARM_SWEEP_ATTACK_GOAL = new VoluciteWardenArmSweepAttackGoal(this, this::getRightArm, 0.2F);
		this.LEFT_ARM_SWEEP_ATTACK_GOAL = new VoluciteWardenArmSweepAttackGoal(this, this::getLeftArm, 0.2F);
		this.RIGHT_ARM_SUMMON_HEAL_BEACON_GOAL = new VoluciteWardenArmSummonHealBeaconGoal(this, this::getRightArm, 0.2F);
		this.LEFT_ARM_SUMMON_HEAL_BEACON_GOAL = new VoluciteWardenArmSummonHealBeaconGoal(this, this::getLeftArm, 0.2F);
		this.CORE_REPULSION_GOAL = new VoluciteWardenCoreRepulsionGoal(this, CoreRepulsionHandler.ATTRACTION_DURATION, CoreRepulsionHandler.REPULSION_DURATION);
		this.CORE_LIGHTNING_GOAL = new VoluciteWardenCoreLightningGoal(this);

		//the target is kept even if out of sight
		this.targetSelector.addGoal(2, new ConditionalGoal(this, new NearestAttackableTargetGoal<>(this, Player.class, false)));
		this.targetSelector.addGoal(1, new PersistentHurtByTargetGoal(this));

		this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 16.0F));
		this.goalSelector.addGoal(4, MAIN_BEAM_ATTACK_GOAL);
		this.goalSelector.addGoal(4, RIGHT_ARM_STRIKE_ATTACK_GOAL);
		this.goalSelector.addGoal(4, LEFT_ARM_STRIKE_ATTACK_GOAL);
		this.goalSelector.addGoal(4, RIGHT_ARM_BEAM_ATTACK_GOAL);
		this.goalSelector.addGoal(4, LEFT_ARM_BEAM_ATTACK_GOAL);
		this.goalSelector.addGoal(4, RIGHT_ARM_SUMMON_ALLY_GOAL);
		this.goalSelector.addGoal(4, LEFT_ARM_SUMMON_ALLY_GOAL);
		this.goalSelector.addGoal(4, RIGHT_ARM_LIGHTNING_ATTACK_GOAL);
		this.goalSelector.addGoal(4, LEFT_ARM_LIGHTNING_ATTACK_GOAL);
		this.goalSelector.addGoal(4, RIGHT_ARM_SWEEP_ATTACK_GOAL);
		this.goalSelector.addGoal(4, LEFT_ARM_SWEEP_ATTACK_GOAL);
		this.goalSelector.addGoal(4, RIGHT_ARM_SUMMON_HEAL_BEACON_GOAL);
		this.goalSelector.addGoal(4, LEFT_ARM_SUMMON_HEAL_BEACON_GOAL);
		this.goalSelector.addGoal(4, CORE_REPULSION_GOAL);
		this.goalSelector.addGoal(4, CORE_LIGHTNING_GOAL);

		//this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		//this.goalSelector.addGoal(4, new ConditionalGoal(this, new MeleeAttackGoal(this, 1.25D, false)));
		//this.goalSelector.addGoal(4, new ConditionalGoal(this, new DirectMeleeAttackGoal(this, 1.25D, 4.0D)));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MudCycleMageEntity.class, true));
    }

	public static AttributeSupplier.Builder registerAttributes()
    {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 1400.0D)
				.add(Attributes.FOLLOW_RANGE, 64.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 5.0D)
				.add(Attributes.ATTACK_DAMAGE, 25.0D);
    }

	@Override public int getPhaseIdToSkipToDyingPhase() {return BossPhase.DYING.getPhaseId();} //no skip : final phase is followed by dying phase
	@Override public boolean enableTickPhaseUpdate(BossPhaseTickType type) {return false;}
	@Override public boolean enableTryDyingPhaseUpdate() {return true;}

	@Override public void applyPhaseUpdateEffect(BossPhase nextPhase) //server side
	{
		this.phaseTicks = 0;
		if (SHIELD_BREAK_TRANSITIONS.contains(nextPhase)) {this.vulnerableHealthFloor = this.realHealth - VULNERABLE_PHASE_MAX_DAMAGE * this.getMaxHealth();} //damage taken during the shield break transition counts for the vulnerable phase max damage
		if (nextPhase == FINAL_PHASE) {this.separateUpperBody();}

		if (SHIELD_TRANSITIONS.contains(nextPhase)) {this.playShieldActivationSound();}
		if (SHIELD_BREAK_TRANSITIONS.contains(nextPhase))
		{
			this.playShieldBreakSound();
			this.playShieldShutdownSound();
			this.loweredHeadYRot = this.yBodyRot + (this.random.nextBoolean() ? LOWERED_HEAD_YAW : -LOWERED_HEAD_YAW);
		}
		if (nextPhase == FINAL_TRANSITION) {this.playFinalShutdownSound();}
		if (nextPhase == FINAL_PHASE) {this.playFinalPhaseSound();}
		if (nextPhase == BossPhase.DYING) {this.playDeathSound();}
	}

	/* --------------------------------- */
	/* ---------- Boss phases ---------- */
	/* --------------------------------- */
	//design summary :
	//shield phase : the player attacks the shield. vulnerable phase : the player attacks the real health. phase 1 is the beginning of the fight
	//phase numbers are BossPhase phases : phase 1 = FIRST_PHASE, phase 1 to 2 = FIRST_TO_SECOND_TRANSITION, ..., phase 8 = EIGHTH_PHASE
	//  phase 1 : real health (like a vulnerable phase), but invincible. activation animation, then next phase
	//  phase 1 to 2 : transition to shield phase (real health >= 66%)
	//  phase 2 : shield phase (level 1)
	//  phase 2 to 3 : the shield broke, transition to vulnerable phase
	//  phase 3 : vulnerable phase
	//  phase 3 to 4 : timer or damage cap reached, and real health < 66% : transition to shield phase
	//  phase 4 : shield phase (level 2)
	//  phase 4 to 5 : the shield broke, transition to vulnerable phase
	//  phase 5 : vulnerable phase
	//  phase 5 to 6 : timer or damage cap reached, and real health < 33% : transition to shield phase
	//  phase 6 : shield phase (level 3)
	//  phase 6 to 7 : the shield broke, transition to vulnerable phase
	//  phase 7 : vulnerable phase
	//  phase 7 to 8 : the player reached the end of the real health, transition to the final phase (real health regenerated, a bit like the chained god)
	//  phase 8 : final phase, no shield. when the player reaches the end of this phase, the warden really dies (dying & dead phases)
	//shield level depends on real health : level 1 above 66%, level 2 between 33% and 66%, level 3 below 33%
	//at the end of a vulnerable phase (timer or damage cap reached), the transition to the shield of the adapted level starts
	//for example : in phase 7, if the warden is not "dead" when the timer ends, phase 5 to 6 starts again
	//
	//implementation :
	//phase 1 (activation) : invincible, ends when the warden has finished awakening
	//shield transitions : health goes from real health to max (shield regenerated), players are repulsed from the core. shield level depends on real health
	//shield phases : health is the shield. when it reaches 0, the shield breaks (default tryDying behavior : next phase)
	//shield break transitions : real health is restored as soon as the shield breaks. the warden can already be hurt (unlike other transitions) :
	//  damage taken counts for the vulnerable phase max damage (if reached, a shield transition starts directly, without vulnerable phase), real health reaching 0 starts the final transition
	//vulnerable phases : health is the real health. after VULNERABLE_PHASE_DURATION, or when VULNERABLE_PHASE_MAX_DAMAGE is reached (since the shield broke), a shield transition starts. if real health reaches 0, the final transition starts
	//final transition : health goes from 0 to max (real health regenerated)
	//final phase : no shield. arm segments & head get semi detached, neck, upper chest & core ribs die (see separateUpperBody). when health reaches 0, the warden really dies (dying & dead phases)
	public boolean isInActivationPhase() {return this.getPhase() == ACTIVATION_PHASE;}
	public boolean isInShieldPhase() {return SHIELD_PHASES.contains(this.getPhase());}
	public boolean isInVulnerablePhase() {return VULNERABLE_PHASES.contains(this.getPhase());}
	public boolean isInShieldBreakTransition() {return SHIELD_BREAK_TRANSITIONS.contains(this.getPhase());}
	public boolean isRealHealthVulnerable() {return this.isInShieldBreakTransition() || this.isInVulnerablePhase();} //real health can be damaged, until the vulnerable phase max damage
	public boolean isInFinalPhase() {return this.getPhase() == FINAL_PHASE;}
	public boolean isInAttackingPhase() {return this.isInShieldPhase() || this.isInFinalPhase();} //the warden can use its attacks (arms, core repulsion). no attack during vulnerable phases
	public int getShieldLevel() {return SHIELD_PHASES.indexOf(this.getPhase()) + 1;} //0 if not in shield phase
	private boolean isHealthTheShield() {return this.isInShieldPhase() || SHIELD_TRANSITIONS.contains(this.getPhase());}
	public boolean isInShieldTransition() {return SHIELD_TRANSITIONS.contains(this.getPhase());}
	//no heal from heal beacons during shield break transitions, vulnerable phases & shield transitions (activation phase can be healed). see VoluciteWardenHealBeaconEntity.isValidHealTarget
	public boolean canBeHealedByBeacons() {return !this.isRealHealthVulnerable() && !this.isInShieldTransition();}

	//shield & final transitions : invulnerable (health is regenerated). shield break transitions : the warden can be hurt
	@Override public boolean isInvulnerableTo(ServerLevel level, DamageSource source)
	{
		return super.isInvulnerableTo(level, source) || (this.isInTransitionPhase() && !this.isInShieldBreakTransition() && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY));
	}

	@Override protected void actuallyHurt(ServerLevel level, DamageSource source, float amount)
	{
		boolean bypassesInvulnerability = source.is(DamageTypeTags.BYPASSES_INVULNERABILITY); //kill & void damage
		//activation phase : no damage, but still hurt (a hit is needed to activate the warden)
		if (this.isInActivationPhase() && !bypassesInvulnerability) {return;}

		super.actuallyHurt(level, source, amount);
		//shield break transitions & vulnerable phases : real health can't go below the floor
		if (this.isRealHealthVulnerable() && this.getHealth() < this.vulnerableHealthFloor && !bypassesInvulnerability) {this.setHealth(this.vulnerableHealthFloor);}
	}

	@Override public boolean tryDying(DamageSource damageSource)
	{
		//real health reaching 0 in any shield break transition or vulnerable phase : final transition (default behavior would only do it from the last vulnerable phase)
		if (this.isRealHealthVulnerable() && !damageSource.is(DamageTypes.GENERIC_KILL))
		{
			this.updateToPhase(FINAL_TRANSITION);
			this.applyAfterTriedDyingPhaseUpdateEffect(FINAL_TRANSITION, NotDeadReason.START_TRANSITION_PHASE);
			return false;
		}
		else {return super.tryDying(damageSource);}
	}

	@Override public void applyAfterTriedDyingPhaseUpdateEffect(BossPhase nextPhase, NotDeadReason reason)
	{
		if (SHIELD_BREAK_TRANSITIONS.contains(nextPhase)) {this.setHealth(this.realHealth);} //shield broken : real health is restored immediately
		else {super.applyAfterTriedDyingPhaseUpdateEffect(nextPhase, reason);}
	}

	private void tickPhases() //server side
	{
		this.phaseTicks++;
		if (this.isInActivationPhase())
		{
			if (this.alreadyActivatedOnce()) {this.startShieldTransition();}
		}
		else if (this.isInTransitionPhase())
		{
			if (this.isInShieldBreakTransition() && this.hasReachedVulnerablePhaseMaxDamage()) {this.startShieldTransition();} //max damage reached before the vulnerable phase : no vulnerable phase
			else {this.tickTransition();}
		}
		else if (this.isInVulnerablePhase())
		{
			if (this.phaseTicks >= VULNERABLE_PHASE_DURATION || this.hasReachedVulnerablePhaseMaxDamage()) {this.startShieldTransition();}
		}
		this.bossInfo.setColor(this.isHealthTheShield() ? SHIELD_BOSS_BAR_COLOR : REAL_HEALTH_BOSS_BAR_COLOR);
	}

	private boolean hasReachedVulnerablePhaseMaxDamage() {return this.vulnerableHealthFloor > 0.0F && this.getHealth() <= this.vulnerableHealthFloor;}

	private void startShieldTransition()
	{
		this.realHealth = this.getHealth();
		float realHealthRatio = this.realHealth / this.getMaxHealth();
		int shieldLevel = realHealthRatio >= SHIELD_LEVEL_2_HEALTH ? 1 : realHealthRatio >= SHIELD_LEVEL_3_HEALTH ? 2 : 3;
		this.updateToPhase(SHIELD_TRANSITIONS.get(shieldLevel - 1));
	}

	private void tickTransition()
	{
		BossPhase transition = this.getPhase();
		int remainingTicks = this.getTransitionDuration(transition) - this.phaseTicks;
		//shield & final transitions : health goes linearly to max (shield regenerated, or real health regenerated for the final phase)
		if (!SHIELD_BREAK_TRANSITIONS.contains(transition)) {this.setHealth(this.getHealth() + (this.getMaxHealth() - this.getHealth()) / (Math.max(remainingTicks, 0) + 1));}
		if (remainingTicks <= 0) {this.updateToNextPhase();}
	}

	//final phase start : only the lower body (legs to lower chest, and core) stays attached
	//semi detached parts have their own health and AI, but stay linked to the warden (see PartStatus)
	private void separateUpperBody()
	{
		for (ArmPartInfo armSegment : this.getRightArm()) {this.semiDetachChildPart(armSegment);}
		for (ArmPartInfo armSegment : this.getLeftArm()) {this.semiDetachChildPart(armSegment);}
		this.semiDetachChildPart(this.HEAD);
		this.killChildPart(this.NECK);
		this.killChildPart(this.UPPER_CHEST);
		this.killChildPart(this.FRONT_RIGHT_CORE_RIB);
		this.killChildPart(this.FRONT_LEFT_CORE_RIB);
		this.killChildPart(this.BACK_RIGHT_CORE_RIB);
		this.killChildPart(this.BACK_LEFT_CORE_RIB);
		if (this.LOWER_CHEST.getPart() instanceof VoluciteWardenChestPartEntity lowerChest) {lowerChest.setUpperStructureRenderOff();} //lower chest model also renders upper chest plate & core ribs
	}

	private int getTransitionDuration(BossPhase transition)
	{
		if (SHIELD_TRANSITIONS.contains(transition)) {return SHIELD_TRANSITION_DURATION;}
		else if (SHIELD_BREAK_TRANSITIONS.contains(transition)) {return SHIELD_BREAK_TRANSITION_DURATION;}
		else {return FINAL_TRANSITION_DURATION;}
	}
	/* --------------------------------- */
	/* --------------------------------- */
	/* --------------------------------- */

	@Override public boolean fireImmune() {return true;}
	@Override public boolean displayFireAnimation() {return false;}

	@Override public boolean causeFallDamage(double distance, float damageMultiplier, DamageSource source) {return false;}

	@Override public void tickTransitionPhase() //both sides
	{
		BossPhase transition = this.getPhase();
		if (SHIELD_TRANSITIONS.contains(transition) || transition == FINAL_TRANSITION) {this.runTransitionEffect();} //players repulsed from the core (both sides : player movement is client authoritative)
		if (this.level().isClientSide())
		{
			Vec3 coreCenter = this.getCoreLightningStartPos();
			if (SHIELD_BREAK_TRANSITIONS.contains(transition)) {VoluciteGlyphParticles.spawnInward(this.level(), coreCenter, INWARD_PARTICLES_MIN_RADIUS, INWARD_PARTICLES_MAX_RADIUS, TRANSITION_PARTICLES_PER_TICK);}
			else {VoluciteGlyphParticles.spawnOutward(this.level(), coreCenter, OUTWARD_PARTICLES_MIN_DISTANCE, OUTWARD_PARTICLES_MAX_DISTANCE, TRANSITION_PARTICLES_PER_TICK);} //shield & final transitions, like the repulsion
		}
	}

	public boolean isHeadLowered() {return SHIELD_BREAK_TRANSITIONS.contains(this.getPhase()) || this.isInVulnerablePhase();}
	public boolean isHeadAnimated() {return this.isHeadLowered() || SHIELD_TRANSITIONS.contains(this.getPhase());}

	//final transition : arms progressively raised horizontally on the sides (like arm beam preparation), and kept there : detached segments won't fall at the same place
	private Vec3 getRaisedArmOffset(Vec3 shoulderPosOffset, float direction)
	{
		float angle = direction * Mth.HALF_PI * Mth.clamp((float) this.phaseTicks / ARMS_RAISE_DURATION, 0.0F, 1.0F); //outward rotation around z axis
		double cos = Math.cos(angle), sin = Math.sin(angle);
		return new Vec3(shoulderPosOffset.x * cos - shoulderPosOffset.y * sin, shoulderPosOffset.x * sin + shoulderPosOffset.y * cos, shoulderPosOffset.z);
	}

	@Override public void tickDyingPhase()
	{
		this.dragOrRepulseEntities(NearbyEntitiesInteractionInfo.REPULSE_FAR, 40.0F);
		this.timeDying++;
		if (this.timeDying > 140) {this.tryDying(this.lastDamageSource == null ? this.damageSources().generic() : this.lastDamageSource);}
	}

	@Override protected boolean canDragOrRepulseEntity(Entity entity) {return super.canDragOrRepulseEntity(entity) && !entity.is(AerialHellTags.Entities.VOLUCITE);}
	@Override protected boolean dragOrRepulseBypassesSneak() {return true;}
	@Override public boolean canAttack(LivingEntity target) {return !EntityHelper.isVoluciteEntity(target) && super.canAttack(target);}

	@Override public void tickDeadPhase() {}

	@Override public Item getTrophy() {return AerialHellItems.VOLUCITE_ORE.get();}

	protected void runTransitionEffect()
	{
		this.dragOrRepulseEntities(NearbyEntitiesInteractionInfo.REPULSE_UNIFORM, 120.0F);
	}
	
	@Override public boolean isPushable() {return false;}
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_VOLUCITE_WARDEN_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_VOLUCITE_WARDEN_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_VOLUCITE_WARDEN_DEATH.get();}
    protected SoundEvent getFastDeathSound() {return AerialHellSoundEvents.ENTITY_VOLUCITE_WARDEN_DEATH.get();}

	public void playDeathSound() {this.playSound(this.getDeathSound(), 5.0F, 1.0F);}
	@Override public void playFastDeathSound() {this.playSound(getFastDeathSound(), this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);}
	public void playShieldBreakSound() {this.playSound(SoundEvents.IRON_GOLEM_REPAIR, 5.0F, 0.5F);}
	public void playShieldActivationSound() {this.playSound(AerialHellSoundEvents.ENTITY_VOLUCITE_WARDEN_SHIELD_ACTIVATION.get(), 5.0F, 1.0F);} //shield transitions start
	public void playShieldShutdownSound() {this.playSound(AerialHellSoundEvents.ENTITY_VOLUCITE_WARDEN_SHIELD_SHUTDOWN.get(), 5.0F, 1.0F);} //shield break transitions start
	public void playFinalShutdownSound() {this.playSound(AerialHellSoundEvents.ENTITY_VOLUCITE_WARDEN_FINAL_SHUTDOWN.get(), 5.0F, 1.0F);} //final transition start
	public void playFinalPhaseSound() {this.playSound(AerialHellSoundEvents.ENTITY_VOLUCITE_WARDEN_FINAL_PHASE.get(), 5.0F, 1.0F);} //final phase start

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

	public static class ArmPartInfo extends PartInfo
	{
		public final int segmentIndex;
		public final boolean isRightArm;

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

	@Override protected float getDragOrRepulseSourcePosRelativeY() {return CORE_BOTTOM_RELATIVE_HEIGHT;}
	@Override protected float getDragOrRepulseParticlesPosRelativeY() {return CORE_CENTER_RELATIVE_HEIGHT;}

	@Override public void tickNonHeadPartRotation(PartInfo partInfo)
	{
		if (partInfo instanceof ArmPartInfo armPartinfo && this.isArmActive(armPartinfo.isRightArm)) {return;}
		else {MasterPartEntity.super.tickNonHeadPartRotation(partInfo);}
	}

	@Override public void tickHeadPartRotation(PartInfo partInfo)
	{
		if (partInfo == this.HEAD && this.HEAD.getPart() instanceof VoluciteWardenHeadEntity head && head.isBeaming()) {return;}
		else {MasterPartEntity.super.tickHeadPartRotation(partInfo);}
	}

	@Override @Nullable public Vec3 calculatePartPos(PartInfo partInfo, double masterX, double masterY, double masterZ)
	{
		if (partInfo instanceof ArmPartInfo armPartinfo && this.isArmActive(armPartinfo.isRightArm)) {return null;}
		return MasterPartEntity.super.calculatePartPos(partInfo, masterX, masterY, masterZ);
	}

	public boolean isArmActive(List<ArmPartInfo> arm)
	{
		return this.isArmActive(arm.getFirst().isRightArm);
	}

	public boolean isArmActive(boolean isRightArm)
	{
		if (isRightArm) {return this.isRightArmActive();}
		else {return this.isLeftArmActive();}
	}

	public boolean isHeadActive() {return this.isHeadBeaming();}
	public boolean isRightArmActive() {return this.isRightArmStriking() || this.isRightArmBeaming() || this.isRightArmSummoningAlly() || this.isRightArmLightningAttacking() || this.isRightArmSweepAttacking() || this.isRightArmSummoningHealBeacon();}
	public boolean isLeftArmActive() {return this.isLeftArmStriking() || this.isLeftArmBeaming() || this.isLeftArmSummoningAlly() || this.isLeftArmLightningAttacking() || this.isLeftArmSweepAttacking() || this.isLeftArmSummoningHealBeacon();}

	/* ----------------------------------------------------- */
	/* ---------- Main Beam Attack : Goal methods ---------- */
	/* ----------------------------------------------------- */
	public boolean isHeadBeaming() {return this.MAIN_BEAM_ATTACK_GOAL.isActive();} //only server side

	public boolean shouldTriggerMainBeamAttack() {return false;}
	public boolean canUseMainBeam() {return this.getShieldLevel() == 3 || this.isInFinalPhase();} //sixth phase (shield level 3) and final phase
	/* ----------------------------------------------------- */
	/* ----------------------------------------------------- */
	/* ----------------------------------------------------- */

	/* ---------------------------------------------------- */
	/* ---------- Arm Beam Attack : Goal methods ---------- */
	/* ---------------------------------------------------- */
	public boolean isRightArmBeaming() {return this.RIGHT_ARM_BEAM_ATTACK_GOAL.isActive();}
	public boolean isLeftArmBeaming() {return this.LEFT_ARM_BEAM_ATTACK_GOAL.isActive();}

	public boolean shouldTriggerArmBeamAttack() {return false;}
	/* ---------------------------------------------------- */
	/* ---------------------------------------------------- */
	/* ---------------------------------------------------- */

	/* --------------------------------------------------------------------------- */
	/* ---------- StrikeAttackEntity : Interface methods implementation ---------- */
	/* --------------------------------------------------------------------------- */
	public boolean isRightArmStriking() {return this.RIGHT_ARM_STRIKE_ATTACK_GOAL.isActive();}
	public boolean isLeftArmStriking() {return this.LEFT_ARM_STRIKE_ATTACK_GOAL.isActive();}

	@Override public List<Phase> getStrikeAttackSequence(@NotNull LivingEntity entityUsedToStrike)
	{
		return this.armsStrikeAttackHandler.getStrikeAttackSequence(entityUsedToStrike);
	}

	@Override public boolean canUseStrikeAttack() {return this.getTarget() != null;}

	@Override public boolean shouldTriggerStrikeAttack() {return false;}

	@Nullable public LivingEntity getLeftArmSegment7()
	{
		PartEntity rightArmSegment7 = this.LEFT_ARM_SEGMENT_7.getPart();
		return rightArmSegment7 != null ? rightArmSegment7.getSelf() : null;
	}

	@Nullable public LivingEntity getRightArmSegment7()
	{
		PartEntity rightArmSegment7 = this.RIGHT_ARM_SEGMENT_7.getPart();
		return rightArmSegment7 != null ? rightArmSegment7.getSelf() : null;
	}

	/* --------------------------------------------------------------------------- */
	/* --------------------------------------------------------------------------- */
	/* --------------------------------------------------------------------------- */

	/* ---------------------------------------------------- */
	/* ---------- Arm Summon Ally : Goal methods ---------- */
	/* ---------------------------------------------------- */
	public boolean isRightArmSummoningAlly() {return this.RIGHT_ARM_SUMMON_ALLY_GOAL.isActive();}
	public boolean isLeftArmSummoningAlly() {return this.LEFT_ARM_SUMMON_ALLY_GOAL.isActive();}

	public boolean shouldTriggerArmSummonAlly() {return false;}
	/* ---------------------------------------------------- */
	/* ---------------------------------------------------- */
	/* ---------------------------------------------------- */

	/* --------------------------------------------------------- */
	/* ---------- Arm Lightning Attack : Goal methods ---------- */
	/* --------------------------------------------------------- */
	public boolean isRightArmLightningAttacking() {return this.RIGHT_ARM_LIGHTNING_ATTACK_GOAL.isActive();}
	public boolean isLeftArmLightningAttacking() {return this.LEFT_ARM_LIGHTNING_ATTACK_GOAL.isActive();}

	public boolean shouldTriggerArmLightningAttack() {return false;}
	/* --------------------------------------------------------- */
	/* --------------------------------------------------------- */
	/* --------------------------------------------------------- */

	/* ----------------------------------------------------- */
	/* ---------- Arm Sweep Attack : Goal methods ---------- */
	/* ----------------------------------------------------- */
	public boolean isRightArmSweepAttacking() {return this.RIGHT_ARM_SWEEP_ATTACK_GOAL.isActive();}
	public boolean isLeftArmSweepAttacking() {return this.LEFT_ARM_SWEEP_ATTACK_GOAL.isActive();}

	public boolean shouldTriggerArmSweepAttack() {return false;}
	/* ----------------------------------------------------- */
	/* ----------------------------------------------------- */
	/* ----------------------------------------------------- */

	/* ----------------------------------------------------------- */
	/* ---------- Arm Summon Heal Beacon : Goal methods ---------- */
	/* ----------------------------------------------------------- */
	public boolean isRightArmSummoningHealBeacon() {return this.RIGHT_ARM_SUMMON_HEAL_BEACON_GOAL.isActive();}
	public boolean isLeftArmSummoningHealBeacon() {return this.LEFT_ARM_SUMMON_HEAL_BEACON_GOAL.isActive();}

	public boolean shouldTriggerArmSummonHealBeacon() {return false;}
	/* ----------------------------------------------------------- */
	/* ----------------------------------------------------------- */
	/* ----------------------------------------------------------- */

	/* ----------------------------------------------- */
	/* ---------- Arm Goals : Global methods ---------- */
	/* ----------------------------------------------- */
	@Nullable public VoluciteWardenArmGoal getArmGoal(ArmGoalType goalType, boolean isRightArm)
	{
		return switch (goalType)
		{
			case BEAM -> isRightArm ? this.RIGHT_ARM_BEAM_ATTACK_GOAL : this.LEFT_ARM_BEAM_ATTACK_GOAL;
			case STRIKE -> isRightArm ? this.RIGHT_ARM_STRIKE_ATTACK_GOAL : this.LEFT_ARM_STRIKE_ATTACK_GOAL;
			case SUMMON_ALLY -> isRightArm ? this.RIGHT_ARM_SUMMON_ALLY_GOAL : this.LEFT_ARM_SUMMON_ALLY_GOAL;
			case LIGHTNING -> isRightArm ? this.RIGHT_ARM_LIGHTNING_ATTACK_GOAL : this.LEFT_ARM_LIGHTNING_ATTACK_GOAL;
			case SWEEP -> isRightArm ? this.RIGHT_ARM_SWEEP_ATTACK_GOAL : this.LEFT_ARM_SWEEP_ATTACK_GOAL;
			case SUMMON_HEAL_BEACON -> isRightArm ? this.RIGHT_ARM_SUMMON_HEAL_BEACON_GOAL : this.LEFT_ARM_SUMMON_HEAL_BEACON_GOAL;
		};
	}
	/* ----------------------------------------------- */
	/* ----------------------------------------------- */
	/* ----------------------------------------------- */

	/* --------------------------------------------------- */
	/* ---------- Core Repulsion : Goal methods ---------- */
	/* --------------------------------------------------- */
	public boolean shouldTriggerCoreRepulsion() {return false;}

	public Vec3 getCoreCenterPos() {return this.getDragOrRepulseSourcePos();}

	public void dragOrRepulseEntitiesFromCore(NearbyEntitiesInteractionInfo type, float factor, int range, DisplayParticlesHelper... particles)
	{
		this.dragOrRepulseEntities(type, factor, range, range, particles);
	}

	//core drag or repulse goal status : set by the goal (server side), used by the handler (drag / repulse & particles) on both sides
	public int getCoreDragOrRepulseGoalStatus() {return this.entityData.get(CORE_DRAG_OR_REPULSE_GOAL_STATUS);} //both sides
	public void setCoreDragOrRepulseGoalStatus(int status) {this.entityData.set(CORE_DRAG_OR_REPULSE_GOAL_STATUS, status);} //server side

	public boolean isCoreAttracting() {return this.getCoreDragOrRepulseGoalStatus() == CORE_ATTRACTING_STATUS;}
	public boolean isCoreRepulsing() {return this.getCoreDragOrRepulseGoalStatus() == CORE_REPULSING_STATUS;}
	/* --------------------------------------------------- */
	/* --------------------------------------------------- */
	/* --------------------------------------------------- */

	/* --------------------------------------------------- */
	/* ---------- Core Lightning : Goal methods ---------- */
	/* --------------------------------------------------- */
	public boolean canCoreStrikeLightning() {return this.isInShieldPhase() || this.isInFinalPhase();} //very frequent lightning in shield phases, occasional lightning in final phase

	public Vec3 getCoreLightningStartPos() {return this.position().add(0.0D, CORE_CENTER_RELATIVE_HEIGHT, 0.0D);} //real core center (getCoreCenterPos() is the core bottom)
	/* --------------------------------------------------- */
	/* --------------------------------------------------- */
	/* --------------------------------------------------- */

	/* ----------------------------------------------------- */
	/* ---------- Players standing on the warden ---------- */
	/* ----------------------------------------------------- */
	//like the happy ghast : while a player stands on a collidable part, the warden stays still (no rotation, no movement), and its rotation is aligned with the hitboxes (multiple of 90 degrees)
	//hitboxes never rotate : no rotation avoids players falling through parts, aligned rotation makes rendered parts match their hitbox. precise position sync avoids client-server hitbox offsets
	public boolean isStill() {return this.stillTimeout > 0;}

	private void tickStill() //server side
	{
		if (this.stillTimeout > 0) {this.stillTimeout--;}
		if (this.isPlayerStandingOnParts()) {this.stillTimeout = STILL_TIMEOUT;}

		boolean isStill = this.isStill();
		if (isStill) {this.getNavigation().stop();}
		this.setRequiresPrecisePosition(isStill);
		for (PartInfo partInfo : this.PARTS_MAP.values())
		{
			PartEntity part = partInfo.getPart();
			if (part != null) {part.getSelf().setRequiresPrecisePosition(isStill && this.isPartAttached(partInfo));}
		}
	}

	private boolean isPlayerStandingOnParts()
	{
		for (Player player : this.level().players())
		{
			if (player.isSpectator() || !player.isAlive()) {continue;}
			for (PartInfo partInfo : this.PARTS_MAP.values())
			{
				PartEntity part = partInfo.getPart();
				if (part != null && this.isPartAttached(partInfo) && part.getSelf().canBeCollidedWith(player) && isStandingOn(player, part.getSelf())) {return true;}
			}
		}
		return false;
	}

	//player hitbox overlapping the top of the part hitbox (feet on it, or slightly above it)
	private static boolean isStandingOn(Player player, Entity part)
	{
		AABB partBox = part.getBoundingBox();
		AABB topBox = new AABB(partBox.minX, partBox.maxY - 1.0E-5F, partBox.minZ, partBox.maxX, partBox.maxY + STANDING_DETECTION_HEIGHT, partBox.maxZ);
		return player.getBoundingBox().intersects(topBox);
	}

	//while still : body and head rotation aligned with the hitboxes (nearest multiple of 90 degrees), look at targets ignored
	private class VoluciteWardenLookControl extends LookControl
	{
		private VoluciteWardenLookControl() {super(VoluciteWardenEntity.this);}

		@Override public void tick()
		{
			VoluciteWardenEntity warden = VoluciteWardenEntity.this;
			if (warden.isStill())
			{
				float alignedRot = warden.yBodyRot - Mth.wrapDegrees90(warden.yBodyRot); //parts rotation follows body rotation
				warden.yBodyRot = alignedRot;
				warden.setYRot(alignedRot);
				warden.setYHeadRot(alignedRot);
			}
			else if (warden.isHeadLowered()) {this.rotateHeadToward(warden.loweredHeadYRot, LOWERED_HEAD_PITCH, LOWERING_HEAD_SPEED);} //"disconnected" warden
			else if (SHIELD_TRANSITIONS.contains(warden.getPhase())) {this.rotateHeadToward(warden.yBodyRot, 0.0F, RAISING_HEAD_SPEED);} //head slowly raised
			else {super.tick();}
		}

		private void rotateHeadToward(float yHeadRot, float xRot, float maxAngle)
		{
			VoluciteWardenEntity warden = VoluciteWardenEntity.this;
			warden.setYHeadRot(Mth.approachDegrees(warden.getYHeadRot(), yHeadRot, maxAngle));
			warden.setXRot(Mth.approachDegrees(warden.getXRot(), xRot, maxAngle));
		}
	}

	@Override protected BodyRotationControl createBodyControl() {return new VoluciteWardenBodyRotationControl();}

	//animated head (transitions, vulnerable phases) : body keeps its rotation (vanilla body rotation would follow the head)
	private class VoluciteWardenBodyRotationControl extends BodyRotationControl
	{
		private VoluciteWardenBodyRotationControl() {super(VoluciteWardenEntity.this);}

		@Override public void clientTick() {if (!VoluciteWardenEntity.this.isHeadAnimated()) {super.clientTick();}}
	}
	/* ----------------------------------------------------- */
	/* ----------------------------------------------------- */
	/* ----------------------------------------------------- */

	@Override public void handleEntityEvent(byte id)
	{
		if (id == 68)
		{
			Vec3 center = this.fromUnrotatedRelativeToLevelPos(this.armsStrikeAttackHandler.getRelativeStrikePos()); //can't use this.getEntityUsedToStrike().position() because client pos is interpolated
			this.spawnStrikeParticles(center, 0.5F);
		}
		else {super.handleEntityEvent(id);}
	}

	private void spawnStrikeParticles(Vec3 center, float radius)
	{
		this.spawnHorizontalShockwaveParticles(center, 100, 0.5F, 1.5F);

		this.spawnParticles(ParticleTypes.EXPLOSION, center, radius * 12, 5);
		this.spawnParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, center, radius, 20);
	}

	private void spawnHorizontalShockwaveParticles(Vec3 center, int number, float radius, float speed)
	{
		for (int i = 0; i < number; i++)
		{
			//radius variation (50 percent)
			float radiusOffset = radius * 0.5F;
			float finalRadius = radius + (float)this.randomParticleOffset(radiusOffset);
			//speed variation (70 percent)
			float speedOffset = speed * 0.7F;
			float finalSpeed = speed + (float)this.randomParticleOffset(speedOffset);

			double angle = 2 * Math.PI * i / number;

			double x = Math.cos(angle);
			double z = Math.sin(angle);

			double px = center.x + x * finalRadius;
			double py = center.y + this.randomParticleYOffset(0.2F);
			double pz = center.z + z * finalRadius;

			double vx = x * finalSpeed;
			double vy = 0.1D;
			double vz = z * finalSpeed;

			this.level().addParticle(ParticleTypes.CLOUD, px, py, pz, vx, vy, vz);
		}
	}

	private void spawnParticles(ParticleOptions particle, Vec3 center, float radius, int number)
	{
		for (int i = 0; i < number; i++)
		{
			double x = center.x + this.randomParticleOffset(radius);
			double y = center.y + this.randomParticleOffset(radius);
			double z = center.z + this.randomParticleOffset(radius);
			double xSpeed = this.randomParticleOffset() * 0.5D;
			double ySpeed = this.randomParticleYOffset() * 0.5D;
			double zSpeed = this.randomParticleOffset() * 0.5D;

			this.level().addParticle(particle, x, y, z, xSpeed, ySpeed, zSpeed);
		}
	}

	private double randomParticleOffset(float radius) {return radius == 0.0F ? this.randomParticleOffset() : this.randomParticleOffset() * radius;}
	private double randomParticleOffset() {return this.random.nextDouble() - 0.5D;}
	private double randomParticleYOffset(float radius) {return radius == 0.0F ? this.randomParticleYOffset() : this.randomParticleYOffset() * radius;}
	private double randomParticleYOffset() {return this.random.nextDouble();}
}
