package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.Bosses.AbstractBossEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.BossPhase;
import fr.factionbedrock.aerialhell.Entity.Bosses.MudCycleMageEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.NearbyEntitiesInteractionInfo;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.MasterPartEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartInfo;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Supplier;

public class VoluciteWardenEntity extends AbstractBossEntity implements MasterPartEntity
{
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
	private static final PartInfo RIGHT_ARM_SEGMENT_1_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "right_arm_segment_1", RIGHT_ARM_SEGMENT_1_ID, new Vec3(6.5F, 23.5F, 0.0F));
	private static final PartInfo RIGHT_ARM_SEGMENT_2_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "right_arm_segment_2", RIGHT_ARM_SEGMENT_2_ID, new Vec3(7.5F, 20.5F, 0.0F));
	private static final PartInfo RIGHT_ARM_SEGMENT_3_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "right_arm_segment_3", RIGHT_ARM_SEGMENT_3_ID, new Vec3(8.5F, 17.5F, 0.0F));
	private static final PartInfo RIGHT_ARM_SEGMENT_4_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "right_arm_segment_4", RIGHT_ARM_SEGMENT_4_ID, new Vec3(8.5F, 14.5F, 0.0F));
	private static final PartInfo RIGHT_ARM_SEGMENT_5_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "right_arm_segment_5", RIGHT_ARM_SEGMENT_5_ID, new Vec3(9.5F, 11.5F, 0.0F));
	private static final PartInfo RIGHT_ARM_SEGMENT_6_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "right_arm_segment_6", RIGHT_ARM_SEGMENT_6_ID, new Vec3(9.5F, 8.5F, 0.0F));
	private static final PartInfo RIGHT_ARM_SEGMENT_7_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "right_arm_segment_7", RIGHT_ARM_SEGMENT_7_ID, new Vec3(9.5F, 5.5F, 0.0F));
	private static final PartInfo LEFT_ARM_SEGMENT_1_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "left_arm_segment_1", LEFT_ARM_SEGMENT_1_ID, new Vec3(-6.5F, 23.5F, 0.0F));
	private static final PartInfo LEFT_ARM_SEGMENT_2_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "left_arm_segment_2", LEFT_ARM_SEGMENT_2_ID, new Vec3(-7.5F, 20.5F, 0.0F));
	private static final PartInfo LEFT_ARM_SEGMENT_3_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "left_arm_segment_3", LEFT_ARM_SEGMENT_3_ID, new Vec3(-8.5F, 17.5F, 0.0F));
	private static final PartInfo LEFT_ARM_SEGMENT_4_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "left_arm_segment_4", LEFT_ARM_SEGMENT_4_ID, new Vec3(-8.5F, 14.5F, 0.0F));
	private static final PartInfo LEFT_ARM_SEGMENT_5_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "left_arm_segment_5", LEFT_ARM_SEGMENT_5_ID, new Vec3(-9.5F, 11.5F, 0.0F));
	private static final PartInfo LEFT_ARM_SEGMENT_6_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "left_arm_segment_6", LEFT_ARM_SEGMENT_6_ID, new Vec3(-9.5F, 8.5F, 0.0F));
	private static final PartInfo LEFT_ARM_SEGMENT_7_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_ARM.get(), "left_arm_segment_7", LEFT_ARM_SEGMENT_7_ID, new Vec3(-9.5F, 5.5F, 0.0F));
	private static final PartInfo RIGHT_LEG_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_LEG.get(), "right_leg", RIGHT_LEG_ID, new Vec3(2.5F, 0.0F, 0.0F));
	private static final PartInfo LEFT_LEG_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_LEG.get(), "left_leg", LEFT_LEG_ID, new Vec3(-2.5F, 0.0F, 0.0F));
	private static final PartInfo PELVIS_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_PELVIS.get(), "pelvis", PELVIS_ID, new Vec3(0.0F, 9.5F, 0.0F));
	private static final PartInfo ABDOMEN_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_ABDOMEN.get(), "abdomen", ABDOMEN_ID, new Vec3(0.0F, 12.5F, 0.0F));
	private static final PartInfo LOWER_CHEST_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_CHEST.get(), "lower_chest", LOWER_CHEST_ID, new Vec3(0.0F, 16.5F, 0.0F));
	private static final PartInfo UPPER_CHEST_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_CHEST.get(), "upper_chest", UPPER_CHEST_ID, new Vec3(0.0F, 24.5F, 0.0F));
	private static final PartInfo CORE_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_CORE.get(), "core", CORE_ID, new Vec3(0.0F, 18.5F, 0.0F));
	private static final PartInfo FRONT_RIGHT_CORE_RIB_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_CORE_RIB.get(), "front_right_core_rib", FRONT_RIGHT_CORE_RIB_ID, new Vec3(4.0F, 18.5F, 4.0F));
	private static final PartInfo FRONT_LEFT_CORE_RIB_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_CORE_RIB.get(), "front_left_core_rib", FRONT_LEFT_CORE_RIB_ID, new Vec3(-4.0F, 18.5F, 4.0F));
	private static final PartInfo BACK_RIGHT_CORE_RIB_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_CORE_RIB.get(), "back_right_core_rib", BACK_RIGHT_CORE_RIB_ID, new Vec3(4.0F, 18.5F, -4.0F));
	private static final PartInfo BACK_LEFT_CORE_RIB_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_CORE_RIB.get(), "back_left_core_rib", BACK_LEFT_CORE_RIB_ID, new Vec3(-4.0F, 18.5F, -4.0F));
	private static final PartInfo NECK_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_NECK.get(), "neck", NECK_ID, new Vec3(0.0F, 26.5F, 0.0F));
	private static final PartInfo HEAD_PART_INFO = new PartInfo(AerialHellEntities.VOLUCITE_WARDEN_HEAD.get(), "head", HEAD_ID, new Vec3(0.0F, 30.5F, 0.0F));
	@Nullable private PartEntity rightArmSegment1;
	@Nullable private String rightArmSegment1StringUUID;
	@Nullable private PartEntity rightArmSegment2;
	@Nullable private String rightArmSegment2StringUUID;
	@Nullable private PartEntity rightArmSegment3;
	@Nullable private String rightArmSegment3StringUUID;
	@Nullable private PartEntity rightArmSegment4;
	@Nullable private String rightArmSegment4StringUUID;
	@Nullable private PartEntity rightArmSegment5;
	@Nullable private String rightArmSegment5StringUUID;
	@Nullable private PartEntity rightArmSegment6;
	@Nullable private String rightArmSegment6StringUUID;
	@Nullable private PartEntity rightArmSegment7;
	@Nullable private String rightArmSegment7StringUUID;
	@Nullable private PartEntity leftArmSegment1;
	@Nullable private String leftArmSegment1StringUUID;
	@Nullable private PartEntity leftArmSegment2;
	@Nullable private String leftArmSegment2StringUUID;
	@Nullable private PartEntity leftArmSegment3;
	@Nullable private String leftArmSegment3StringUUID;
	@Nullable private PartEntity leftArmSegment4;
	@Nullable private String leftArmSegment4StringUUID;
	@Nullable private PartEntity leftArmSegment5;
	@Nullable private String leftArmSegment5StringUUID;
	@Nullable private PartEntity leftArmSegment6;
	@Nullable private String leftArmSegment6StringUUID;
	@Nullable private PartEntity leftArmSegment7;
	@Nullable private String leftArmSegment7StringUUID;
	@Nullable private PartEntity rightLeg;
	@Nullable private String rightLegStringUUID;
	@Nullable private PartEntity leftLeg;
	@Nullable private String leftLegStringUUID;
	@Nullable private PartEntity pelvis;
	@Nullable private String pelvisStringUUID;
	@Nullable private PartEntity abdomen;
	@Nullable private String abdomenStringUUID;
	@Nullable private PartEntity lowerChest;
	@Nullable private String lowerChestStringUUID;
	@Nullable private PartEntity upperChest;
	@Nullable private String upperChestStringUUID;
	@Nullable private PartEntity core;
	@Nullable private String coreStringUUID;
	@Nullable private PartEntity frontRightCoreRib;
	@Nullable private String frontRightCoreRibStringUUID;
	@Nullable private PartEntity frontLeftCoreRib;
	@Nullable private String frontLeftCoreRibStringUUID;
	@Nullable private PartEntity backRightCoreRib;
	@Nullable private String backRightCoreRibStringUUID;
	@Nullable private PartEntity backLeftCoreRib;
	@Nullable private String backLeftCoreRibStringUUID;
	@Nullable private PartEntity neck;
	@Nullable private String neckStringUUID;
	@Nullable private PartEntity head;
	@Nullable private String headStringUUID;
	protected int ticksInInvalidSituation;
	public Map<PartInfo, Supplier<PartEntity>> PARTS_MAP = Maps.newHashMap(ImmutableMap.<PartInfo, Supplier<PartEntity>>builder()
		.put(RIGHT_ARM_SEGMENT_1_PART_INFO, () -> this.rightArmSegment1)
		.put(RIGHT_ARM_SEGMENT_2_PART_INFO, () -> this.rightArmSegment2)
		.put(RIGHT_ARM_SEGMENT_3_PART_INFO, () -> this.rightArmSegment3)
		.put(RIGHT_ARM_SEGMENT_4_PART_INFO, () -> this.rightArmSegment4)
		.put(RIGHT_ARM_SEGMENT_5_PART_INFO, () -> this.rightArmSegment5)
		.put(RIGHT_ARM_SEGMENT_6_PART_INFO, () -> this.rightArmSegment6)
		.put(RIGHT_ARM_SEGMENT_7_PART_INFO, () -> this.rightArmSegment7)
		.put(LEFT_ARM_SEGMENT_1_PART_INFO, () -> this.leftArmSegment1)
		.put(LEFT_ARM_SEGMENT_2_PART_INFO, () -> this.leftArmSegment2)
		.put(LEFT_ARM_SEGMENT_3_PART_INFO, () -> this.leftArmSegment3)
		.put(LEFT_ARM_SEGMENT_4_PART_INFO, () -> this.leftArmSegment4)
		.put(LEFT_ARM_SEGMENT_5_PART_INFO, () -> this.leftArmSegment5)
		.put(LEFT_ARM_SEGMENT_6_PART_INFO, () -> this.leftArmSegment6)
		.put(LEFT_ARM_SEGMENT_7_PART_INFO, () -> this.leftArmSegment7)
		.put(RIGHT_LEG_PART_INFO, () -> this.rightLeg)
		.put(LEFT_LEG_PART_INFO, () -> this.leftLeg)
		.put(PELVIS_PART_INFO, () -> this.pelvis)
		.put(ABDOMEN_PART_INFO, () -> this.abdomen)
		.put(LOWER_CHEST_PART_INFO, () -> this.lowerChest)
		.put(UPPER_CHEST_PART_INFO, () -> this.upperChest)
		.put(CORE_PART_INFO, () -> this.core)
		.put(FRONT_RIGHT_CORE_RIB_PART_INFO, () -> this.frontRightCoreRib)
		.put(FRONT_LEFT_CORE_RIB_PART_INFO, () -> this.frontLeftCoreRib)
		.put(BACK_RIGHT_CORE_RIB_PART_INFO, () -> this.backRightCoreRib)
		.put(BACK_LEFT_CORE_RIB_PART_INFO, () -> this.backLeftCoreRib)
		.put(NECK_PART_INFO, () -> this.neck)
		.put(HEAD_PART_INFO, () -> this.head)
		.build());
	/* ----------------------------- */

	public int timeDying;

	public VoluciteWardenEntity(EntityType<? extends Monster> type, Level world)
	{
		super(type, world);

		/* -- MasterPartEntity init -- */
		this.initMaster();
		/* --------------------------- */

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
	}

	@SafeVarargs private static void defineAll(SynchedEntityData.Builder builder, EntityDataAccessor<Integer>... dataAccessors)
	{
		for (EntityDataAccessor<Integer> dataAccessor : dataAccessors) {builder.define(dataAccessor, 0);}
	}

	/* ------------------------------------------------------------------------- */
	/* ---------- MasterPartEntity : Interface methods implementation ---------- */
	/* ------------------------------------------------------------------------- */
	@Override public Mob getSelf() {return this;}
	@Override public Map<PartInfo, Supplier<PartEntity>> getAllParts() {return this.PARTS_MAP;}

	@Override public void tickPartRotation(PartInfo partInfo, @NotNull PartEntity partEntity)
	{
		if (partEntity instanceof VoluciteWardenPartEntity part)
		{
			if (partInfo == HEAD_PART_INFO)
			{
				part.yBodyRotO = part.yBodyRot;
				part.yBodyRot = this.yHeadRot; //the whole "body" is head
				part.yHeadRotO = part.yHeadRot;
				part.yHeadRot = this.yHeadRot;
				part.setXRot(this.getXRot());
				part.setYRot(this.getYRot());
			}
			else
			{
				part.yBodyRotO = part.yBodyRot;
				part.yBodyRot = this.yBodyRot;
				part.yHeadRotO = part.yHeadRot;
				part.yHeadRot = this.yHeadRot;
				part.setXRot(this.getXRot());
				part.setYRot(this.getYRot());
			}
		}
	}

	@Override @Nullable public String getPartStringUUID(PartInfo part)
	{
		if (part == RIGHT_ARM_SEGMENT_1_PART_INFO) {return this.rightArmSegment1StringUUID;}
		else if (part == RIGHT_ARM_SEGMENT_2_PART_INFO) {return this.rightArmSegment2StringUUID;}
		else if (part == RIGHT_ARM_SEGMENT_3_PART_INFO) {return this.rightArmSegment3StringUUID;}
		else if (part == RIGHT_ARM_SEGMENT_4_PART_INFO) {return this.rightArmSegment4StringUUID;}
		else if (part == RIGHT_ARM_SEGMENT_5_PART_INFO) {return this.rightArmSegment5StringUUID;}
		else if (part == RIGHT_ARM_SEGMENT_6_PART_INFO) {return this.rightArmSegment6StringUUID;}
		else if (part == RIGHT_ARM_SEGMENT_7_PART_INFO) {return this.rightArmSegment7StringUUID;}
		else if (part == LEFT_ARM_SEGMENT_1_PART_INFO) {return this.leftArmSegment1StringUUID;}
		else if (part == LEFT_ARM_SEGMENT_2_PART_INFO) {return this.leftArmSegment2StringUUID;}
		else if (part == LEFT_ARM_SEGMENT_3_PART_INFO) {return this.leftArmSegment3StringUUID;}
		else if (part == LEFT_ARM_SEGMENT_4_PART_INFO) {return this.leftArmSegment4StringUUID;}
		else if (part == LEFT_ARM_SEGMENT_5_PART_INFO) {return this.leftArmSegment5StringUUID;}
		else if (part == LEFT_ARM_SEGMENT_6_PART_INFO) {return this.leftArmSegment6StringUUID;}
		else if (part == LEFT_ARM_SEGMENT_7_PART_INFO) {return this.leftArmSegment7StringUUID;}
		else if (part == RIGHT_LEG_PART_INFO) {return this.rightLegStringUUID;}
		else if (part == LEFT_LEG_PART_INFO) {return this.leftLegStringUUID;}
		else if (part == PELVIS_PART_INFO) {return this.pelvisStringUUID;}
		else if (part == ABDOMEN_PART_INFO) {return this.abdomenStringUUID;}
		else if (part == LOWER_CHEST_PART_INFO) {return this.lowerChestStringUUID;}
		else if (part == UPPER_CHEST_PART_INFO) {return this.upperChestStringUUID;}
		else if (part == CORE_PART_INFO) {return this.coreStringUUID;}
		else if (part == FRONT_RIGHT_CORE_RIB_PART_INFO) {return this.frontRightCoreRibStringUUID;}
		else if (part == FRONT_LEFT_CORE_RIB_PART_INFO) {return this.frontLeftCoreRibStringUUID;}
		else if (part == BACK_RIGHT_CORE_RIB_PART_INFO) {return this.backRightCoreRibStringUUID;}
		else if (part == BACK_LEFT_CORE_RIB_PART_INFO) {return this.backLeftCoreRibStringUUID;}
		else if (part == NECK_PART_INFO) {return this.neckStringUUID;}
		else if (part == HEAD_PART_INFO) {return this.headStringUUID;}
		else {return "null";}
	}

	@Override public void setPartStringUUID(PartInfo part, String uuid)
	{
		if (part == RIGHT_ARM_SEGMENT_1_PART_INFO) {this.rightArmSegment1StringUUID = uuid;}
		else if (part == RIGHT_ARM_SEGMENT_2_PART_INFO) {this.rightArmSegment2StringUUID = uuid;}
		else if (part == RIGHT_ARM_SEGMENT_3_PART_INFO) {this.rightArmSegment3StringUUID = uuid;}
		else if (part == RIGHT_ARM_SEGMENT_4_PART_INFO) {this.rightArmSegment4StringUUID = uuid;}
		else if (part == RIGHT_ARM_SEGMENT_5_PART_INFO) {this.rightArmSegment5StringUUID = uuid;}
		else if (part == RIGHT_ARM_SEGMENT_6_PART_INFO) {this.rightArmSegment6StringUUID = uuid;}
		else if (part == RIGHT_ARM_SEGMENT_7_PART_INFO) {this.rightArmSegment7StringUUID = uuid;}
		else if (part == LEFT_ARM_SEGMENT_1_PART_INFO) {this.leftArmSegment1StringUUID = uuid;}
		else if (part == LEFT_ARM_SEGMENT_2_PART_INFO) {this.leftArmSegment2StringUUID = uuid;}
		else if (part == LEFT_ARM_SEGMENT_3_PART_INFO) {this.leftArmSegment3StringUUID = uuid;}
		else if (part == LEFT_ARM_SEGMENT_4_PART_INFO) {this.leftArmSegment4StringUUID = uuid;}
		else if (part == LEFT_ARM_SEGMENT_5_PART_INFO) {this.leftArmSegment5StringUUID = uuid;}
		else if (part == LEFT_ARM_SEGMENT_6_PART_INFO) {this.leftArmSegment6StringUUID = uuid;}
		else if (part == LEFT_ARM_SEGMENT_7_PART_INFO) {this.leftArmSegment7StringUUID = uuid;}
		else if (part == RIGHT_LEG_PART_INFO) {this.rightLegStringUUID = uuid;}
		else if (part == LEFT_LEG_PART_INFO) {this.leftLegStringUUID = uuid;}
		else if (part == PELVIS_PART_INFO) {this.pelvisStringUUID = uuid;}
		else if (part == ABDOMEN_PART_INFO) {this.abdomenStringUUID = uuid;}
		else if (part == LOWER_CHEST_PART_INFO) {this.lowerChestStringUUID = uuid;}
		else if (part == UPPER_CHEST_PART_INFO) {this.upperChestStringUUID = uuid;}
		else if (part == CORE_PART_INFO) {this.coreStringUUID = uuid;}
		else if (part == FRONT_RIGHT_CORE_RIB_PART_INFO) {this.frontRightCoreRibStringUUID = uuid;}
		else if (part == FRONT_LEFT_CORE_RIB_PART_INFO) {this.frontLeftCoreRibStringUUID = uuid;}
		else if (part == BACK_RIGHT_CORE_RIB_PART_INFO) {this.backRightCoreRibStringUUID = uuid;}
		else if (part == BACK_LEFT_CORE_RIB_PART_INFO) {this.backLeftCoreRibStringUUID = uuid;}
		else if (part == NECK_PART_INFO) {this.neckStringUUID = uuid;}
		else if (part == HEAD_PART_INFO) {this.headStringUUID = uuid;}
	}

	@Override public int getTicksInInvalidSituation() {return this.ticksInInvalidSituation;}
	@Override public void setTickInInvalidSituation(int newValue) {this.ticksInInvalidSituation = newValue;}
	@Override public void setPartRaw(PartInfo partInfo, PartEntity part)
	{
		if (partInfo == RIGHT_ARM_SEGMENT_1_PART_INFO) {this.rightArmSegment1 = part;}
		else if (partInfo == RIGHT_ARM_SEGMENT_2_PART_INFO) {this.rightArmSegment2 = part;}
		else if (partInfo == RIGHT_ARM_SEGMENT_3_PART_INFO) {this.rightArmSegment3 = part;}
		else if (partInfo == RIGHT_ARM_SEGMENT_4_PART_INFO) {this.rightArmSegment4 = part;}
		else if (partInfo == RIGHT_ARM_SEGMENT_5_PART_INFO) {this.rightArmSegment5 = part;}
		else if (partInfo == RIGHT_ARM_SEGMENT_6_PART_INFO) {this.rightArmSegment6 = part;}
		else if (partInfo == RIGHT_ARM_SEGMENT_7_PART_INFO) {this.rightArmSegment7 = part;}
		else if (partInfo == LEFT_ARM_SEGMENT_1_PART_INFO) {this.leftArmSegment1 = part;}
		else if (partInfo == LEFT_ARM_SEGMENT_2_PART_INFO) {this.leftArmSegment2 = part;}
		else if (partInfo == LEFT_ARM_SEGMENT_3_PART_INFO) {this.leftArmSegment3 = part;}
		else if (partInfo == LEFT_ARM_SEGMENT_4_PART_INFO) {this.leftArmSegment4 = part;}
		else if (partInfo == LEFT_ARM_SEGMENT_5_PART_INFO) {this.leftArmSegment5 = part;}
		else if (partInfo == LEFT_ARM_SEGMENT_6_PART_INFO) {this.leftArmSegment6 = part;}
		else if (partInfo == LEFT_ARM_SEGMENT_7_PART_INFO) {this.leftArmSegment7 = part;}
		else if (partInfo == RIGHT_LEG_PART_INFO) {this.rightLeg = part;}
		else if (partInfo == LEFT_LEG_PART_INFO) {this.leftLeg = part;}
		else if (partInfo == PELVIS_PART_INFO) {this.pelvis = part;}
		else if (partInfo == ABDOMEN_PART_INFO) {this.abdomen = part;}
		else if (partInfo == LOWER_CHEST_PART_INFO) {this.lowerChest = part;}
		else if (partInfo == UPPER_CHEST_PART_INFO) {this.upperChest = part;}
		else if (partInfo == CORE_PART_INFO) {this.core = part;}
		else if (partInfo == FRONT_RIGHT_CORE_RIB_PART_INFO) {this.frontRightCoreRib = part;}
		else if (partInfo == FRONT_LEFT_CORE_RIB_PART_INFO) {this.frontLeftCoreRib = part;}
		else if (partInfo == BACK_RIGHT_CORE_RIB_PART_INFO) {this.backRightCoreRib = part;}
		else if (partInfo == BACK_LEFT_CORE_RIB_PART_INFO) {this.backLeftCoreRib = part;}
		else if (partInfo == NECK_PART_INFO) {this.neck = part;}
		else if (partInfo == HEAD_PART_INFO) {this.head = part;}
	}
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

		//additional things (specific to the volucite warden)
		if (!this.isInDeadOrDyingPhase()) {this.timeDying = 0;}
	}

	@Override public void aiStep()
	{
		super.aiStep();
		this.partAiStep();
	}

	@Override public void setPos(double x, double y, double z)
	{
		super.setPos(x, y, z);
		this.setPartsPos(x, y, z);
	}

	@Override public void setXRot(float xRot)
	{
		super.setXRot(xRot);
		this.setPartsXRot(xRot);
	}

	@Override public void setYRot(float yRot)
	{
		super.setYRot(yRot);
		this.setPartsYRot(yRot);
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


	/* --------------------------------------------------------------------------------------------------- */
	/* ----------- MasterPartEntity : Superclass methods Overridden for part-specific behavior ----------- */
	/* --------------------------------------------------------------------------------------------------- */
	@Override public double getEyeY() {return this.position().y + 34.50F;}

	@Override public boolean removeWhenFarAway(double distanceToClosestPlayer) {return false;}
	/* --------------------------------------------------------------------------------------------------- */
	/* --------------------------------------------------------------------------------------------------- */
	/* --------------------------------------------------------------------------------------------------- */

	@Override protected void registerGoals()
    {
		this.targetSelector.addGoal(2, new ActiveNearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(5, new ActiveMeleeAttackGoal(this, 1.25D, false));
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
}
