package fr.factionbedrock.aerialhell.Entity.Bosses;

import java.util.List;

import fr.factionbedrock.aerialhell.Block.*;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.AbstractBossEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellDimensions;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LilithEntity extends AbstractBossEntity
{
	public int attackTimer;
	
	private static final DataParameter<Boolean> IS_TRANSFORMING = EntityDataManager.createKey(LilithEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_TRANSFORMED = EntityDataManager.createKey(LilithEntity.class, DataSerializers.BOOLEAN);
	private int timeSinceTransforming;
	private final int transformationTime = 160; //8 seconds

	public LilithEntity(EntityType<? extends MonsterEntity> type, World world)
	{
		super(type, world);
		attackTimer = 0;
		timeSinceTransforming = 0; this.hurtTime = 0;
		bossInfo.setColor(BossInfo.Color.PURPLE);
		bossInfo.setOverlay(BossInfo.Overlay.NOTCHED_6);
	}

	@Override
    protected void registerGoals()
    {
		this.targetSelector.addGoal(2, new BossNearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(3, new LilithMeleeAttackGoal(this, 1.25D, false));
		this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(5, new LilithWaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MudCycleMageEntity.class, true));
    }
	
	public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
		return MonsterEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 600.0D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 32.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
				.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.1D)
				.createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 20.0D);
    }
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		Entity immediateSourceEntity = source.getImmediateSource();
		Entity trueSourceEntity = source.getTrueSource();
		if (this.isTransforming() && !source.isCreativePlayer()) {return false;}
		boolean flag = super.attackEntityFrom(source, amount);
		if (flag)
		{
			if (trueSourceEntity instanceof LivingEntity && !(immediateSourceEntity instanceof AbstractArrowEntity))
			{
				if (!(trueSourceEntity instanceof PlayerEntity && ((PlayerEntity)trueSourceEntity).isCreative()))
				{
					this.setAttackTarget((LivingEntity) trueSourceEntity);
				}
			}
		}
		return flag;
	}
	
	@Override
	protected void registerData()
	{
	    super.registerData();
	    this.dataManager.register(IS_TRANSFORMING, false);
	    this.dataManager.register(IS_TRANSFORMED, false);
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		
		compound.putShort("timeTransforming", (short)this.timeSinceTransforming);
		compound.putBoolean("isTransforming", this.isTransforming());
		if (this.dataManager.get(IS_TRANSFORMED)) {compound.putBoolean("isTransformed", true);}
	}
	
	@Override
	public void readAdditional(CompoundNBT compound)
	{
	    super.readAdditional(compound);
	    if (compound.contains("timeTransforming", 99))
	    {
	    	this.timeSinceTransforming = compound.getShort("timeTransforming");
	    }
	    this.setTransforming(compound.getBoolean("isTransforming"));
	    this.dataManager.set(IS_TRANSFORMED,compound.getBoolean("isTransformed"));
	}
	
	public boolean isTransforming() {return this.dataManager.get(IS_TRANSFORMING);}
	public void setTransforming(boolean isTransforming) {this.dataManager.set(IS_TRANSFORMING, isTransforming);}
	
	public boolean isTransformed() {return this.dataManager.get(IS_TRANSFORMED);}
	public void setTransformed() {this.dataManager.set(IS_TRANSFORMED, true);}

	@Override public boolean isImmuneToFire() {return true;}
	@Override public boolean canRenderOnFire() {return false;}
	
	@Override
	public boolean onLivingFall(float distance, float damageMultiplier) {return false;}
	
	@Override
    public void tick()
    {		
		if (this.isActive() && !this.isTransformed() && !this.isTransforming())
		{
			this.timeSinceTransforming = 0;
			this.setTransforming(true);
			this.playSound(AerialHellSoundEvents.ENTITY_LILITH_TRANSFORMATION.get(), 5.0F, 1.0F);
		}
		
		if (this.isTransforming())
		{
			if (!world.isRemote)
			{
				this.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 20, 10, true, false)));
				this.addPotionEffect(new EffectInstance(new EffectInstance(Effects.RESISTANCE, 1, 10, true, false)));
			}
			this.timeSinceTransforming++;

			for (int i=0; i<10 + timeSinceTransforming/1.5; i++)
			{
				if (this.world.getDimensionKey() == AerialHellDimensions.AERIAL_HELL_DIMENSION) {this.transformRandomBlock();}
			}

	        if (this.timeSinceTransforming >= transformationTime)
	        {
	        	this.transform();
				this.setTransforming(false);
				if (this.world.getDimensionKey() == AerialHellDimensions.AERIAL_HELL_DIMENSION) {this.transformAllBlocks();}
		        this.timeSinceTransforming = 0;
	        }
	        
	        if (this.timeSinceTransforming > 12)
	        {
	        	List<Entity> nearbyEntities = this.world.getEntitiesInAABBexcluding(this, this.getBoundingBox().grow(20), EntityPredicates.withinRange(this.getPosX(), this.getPosY(), this.getPosZ(), 15));
				for (Entity entity : nearbyEntities)
		    	{
					boolean creaOrSpecPlayer = (entity instanceof PlayerEntity && (((PlayerEntity) entity).isSpectator() || ((PlayerEntity) entity).isCreative()));
		    		if (entity instanceof LivingEntity && !creaOrSpecPlayer) {dragEntity(entity);}
		    	}
				
				if (this.world.isRemote)
		        {
		        	for (int i=0; i<5; i++)
		        	{
		        		double random = rand.nextFloat() * 2;
						double x = getPosX() + (rand.nextFloat() - 0.5F) * random;
						double y = (this.getBoundingBox().minY + random) + 0.5D;
						double z = getPosZ() + (rand.nextFloat() - 0.5F) * random;
						double dx = (rand.nextFloat() - 0.5F)/10;
						double dz = (rand.nextFloat() - 0.5F)/10;
						this.world.addParticle(AerialHellParticleTypes.SHADOW_PARTICLE.get(), x, y, z, dx, 0.0D, dz);
		        	}
		        }
	        }
		}		
		super.tick();
    }

	private void transformRandomBlock()
	{
		int maxHorizontalDistance = 14;
		int maxVerticalDistance = 10;
		int x = rand.nextInt(2*maxHorizontalDistance) - maxHorizontalDistance;
		int y = rand.nextInt(2*maxVerticalDistance) - maxVerticalDistance;
		int z = rand.nextInt(2*maxHorizontalDistance) - maxHorizontalDistance;
		BlockPos transformationPos = new BlockPos(this.getPosition().add(new Vector3i(x, y, z)));
		if (world.getBlockState(transformationPos).isIn(AerialHellTags.Blocks.LILITH_TRANSFORMABLE))
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
					BlockPos transformationPos = new BlockPos(this.getPosition().add(new Vector3i(x, y, z)));
					if (world.getBlockState(transformationPos).isIn(AerialHellTags.Blocks.LILITH_TRANSFORMABLE))
					{
						transformBlock(transformationPos);
					}
				}
			}
		}
	}

	private void transformBlock(BlockPos pos)
	{
		if (world.getBlockState(pos).getBlock() instanceof DoorBlock)
		{
			DoubleBlockHalf half = world.getBlockState(pos).get(DoorBlock.HALF);
			if (half == DoubleBlockHalf.LOWER)
			{
				world.destroyBlock(pos, false);
				world.destroyBlock(pos.up(), false);
			}
			else
			{
				world.destroyBlock(pos.down(), false);
				world.destroyBlock(pos, false);
			}
		}
		else {world.setBlockState(pos, getEquivalentShadowBlockstate(world.getBlockState(pos)));}
	}

	private BlockState getEquivalentShadowBlockstate(BlockState blockState)
	{
		Block block = blockState.getBlock(), newBlock;
		if (block instanceof AerialHellTorchBlock)
		{
			if (block instanceof AerialHellWallTorchBlock)
			{
				return AerialHellBlocksAndItems.SHADOW_WALL_TORCH.get().getDefaultState().with(AerialHellWallTorchBlock.HORIZONTAL_FACING, blockState.get(AerialHellWallTorchBlock.HORIZONTAL_FACING));
			}
			else //not a wall torch
			{
				return AerialHellBlocksAndItems.SHADOW_TORCH.get().getDefaultState();
			}
		}
		else if (blockState.isIn(AerialHellTags.Blocks.SOLID_ETHER))
		{
			return AerialHellBlocksAndItems.PURPLE_SOLID_ETHER.get().getDefaultState();
		}
		else if (block == AerialHellBlocksAndItems.CRYSTAL_BLOCK.get())
		{
			return AerialHellBlocksAndItems.SHADOW_CRYSTAL_BLOCK.get().getDefaultState();
		}
		else if (block == AerialHellBlocksAndItems.VIBRANT_SKY_CACTUS_FIBER_LANTERN.get())
		{
			return AerialHellBlocksAndItems.GIANT_CORTINARIUS_VIOLACEUS_LIGHT.get().getDefaultState();
		}
		else if (block instanceof StellarGrassBlock)
		{
			return AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get().getDefaultState();
		}
		else if (block instanceof LanternBlock)
		{
			return AerialHellBlocksAndItems.SHADOW_LANTERN.get().getDefaultState().with(LanternBlock.HANGING, blockState.get(LanternBlock.HANGING));
		}
		else if (block instanceof ChainBlock)
		{
			return AerialHellBlocksAndItems.SHADOW_CHAIN.get().getDefaultState().with(ChainBlock.AXIS, blockState.get(ChainBlock.AXIS));
		}
		else if (block instanceof PaneBlock)
		{
			return AerialHellBlocksAndItems.SHADOW_BARS.get().getDefaultState().with(PaneBlock.NORTH, blockState.get(PaneBlock.NORTH)).with(PaneBlock.SOUTH, blockState.get(PaneBlock.SOUTH)).with(PaneBlock.WEST, blockState.get(PaneBlock.WEST)).with(PaneBlock.EAST, blockState.get(PaneBlock.EAST));
		}
		else if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_PLANKS.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_PLANKS.get() || block == AerialHellBlocksAndItems.CHISELED_AERIAL_TREE_PLANKS.get())
		{
			return AerialHellBlocksAndItems.GRAY_SHROOM_PLANKS.get().getDefaultState();
		}
		else if (block == AerialHellBlocksAndItems.GOLDEN_BEECH_PLANKS.get() || block == AerialHellBlocksAndItems.CHISELED_GOLDEN_BEECH_PLANKS.get() || block == Blocks.DARK_OAK_PLANKS || block == AerialHellBlocksAndItems.CHISELED_AERIAL_TREE_PLANKS.get())
		{
			return AerialHellBlocksAndItems.SHADOW_PINE_PLANKS.get().getDefaultState();
		}
		else if (block instanceof SaplingBlock)
		{
			return AerialHellBlocksAndItems.SHADOW_PINE_SAPLING.get().getDefaultState();
		}
		else if (block instanceof LeavesBlock)
		{
			if (block == AerialHellBlocksAndItems.GOLDEN_BEECH_LEAVES.get() || block == Blocks.DARK_OAK_LEAVES) {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_LEAVES.get();}
			else {newBlock = AerialHellBlocksAndItems.PURPLE_SHADOW_PINE_LEAVES.get();}
			return newBlock.getDefaultState().with(LeavesBlock.PERSISTENT, blockState.get(LeavesBlock.PERSISTENT)).with(LeavesBlock.DISTANCE, blockState.get(LeavesBlock.DISTANCE));
		}
		else if (block instanceof CraftingTableBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_CRAFTING_TABLE.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_CRAFTING_TABLE.get()) {return AerialHellBlocksAndItems.GRAY_SHROOM_CRAFTING_TABLE.get().getDefaultState();}
			else {return AerialHellBlocksAndItems.SHADOW_PINE_CRAFTING_TABLE.get().getDefaultState();}
		}
		else if (block instanceof StairsBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_STAIRS.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_STAIRS.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_STAIRS.get();}
			else if (block == Blocks.QUARTZ_STAIRS) {newBlock = AerialHellBlocksAndItems.SMOKY_QUARTZ_STAIRS.get();}
			else if (block == Blocks.SMOOTH_QUARTZ_STAIRS) {newBlock = AerialHellBlocksAndItems.SMOOTH_SMOKY_QUARTZ_STAIRS.get();}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_STAIRS.get();}
			return newBlock.getDefaultState().with(StairsBlock.FACING, blockState.get(StairsBlock.FACING)).with(StairsBlock.HALF, blockState.get(StairsBlock.HALF)).with(StairsBlock.SHAPE, blockState.get(StairsBlock.SHAPE));
		}
		else if (block instanceof SlabBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_SLAB.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_SLAB.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_SLAB.get();}
			else if (block == Blocks.QUARTZ_SLAB) {newBlock = AerialHellBlocksAndItems.SMOKY_QUARTZ_SLAB.get();}
			else if (block == Blocks.SMOOTH_QUARTZ_SLAB) {newBlock = AerialHellBlocksAndItems.SMOOTH_SMOKY_QUARTZ_SLAB.get();}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_SLAB.get();}
			return newBlock.getDefaultState().with(SlabBlock.TYPE, blockState.get(SlabBlock.TYPE));
		}
		else if (block instanceof FenceBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_FENCE.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_FENCE.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_FENCE.get();}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_FENCE.get();}
			return newBlock.getDefaultState().with(FenceBlock.NORTH, blockState.get(FenceBlock.NORTH)).with(FenceBlock.EAST, blockState.get(FenceBlock.EAST)).with(FenceBlock.WEST, blockState.get(FenceBlock.WEST)).with(FenceBlock.SOUTH, blockState.get(FenceBlock.SOUTH));
		}
		else if (block instanceof FenceGateBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_GATE.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_GATE.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_GATE.get();}
			else /*golden beech & all other woods*/ {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_GATE.get();}
			return newBlock.getDefaultState().with(FenceGateBlock.HORIZONTAL_FACING, blockState.get(FenceGateBlock.HORIZONTAL_FACING)).with(FenceGateBlock.OPEN, blockState.get(FenceGateBlock.OPEN)).with(FenceGateBlock.POWERED, blockState.get(FenceGateBlock.POWERED)).with(FenceGateBlock.IN_WALL, blockState.get(FenceGateBlock.IN_WALL));
		}
		else if (block instanceof RotatedPillarBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_LOG.get() || block == AerialHellBlocksAndItems.ENCHANTED_LAPIS_ROBINIA_LOG.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_LOG.get()) {newBlock = AerialHellBlocksAndItems.GIANT_VERDIGRIS_AGARIC_STEM.get();}
			else if (block == AerialHellBlocksAndItems.STRIPPED_LAPIS_ROBINIA_LOG.get() || block == AerialHellBlocksAndItems.STRIPPED_AERIAL_TREE_LOG.get()) {newBlock = AerialHellBlocksAndItems.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM.get();}
			else if (block == Blocks.QUARTZ_PILLAR) {newBlock = AerialHellBlocksAndItems.SMOKY_QUARTZ_PILLAR.get();}
			else if (block == AerialHellBlocksAndItems.STRIPPED_GOLDEN_BEECH_LOG.get() || block == Blocks.STRIPPED_DARK_OAK_LOG || block == Blocks.STRIPPED_OAK_LOG) {newBlock = AerialHellBlocksAndItems.STRIPPED_SHADOW_PINE_LOG.get();}
			else {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_LOG.get();}
			return newBlock.getDefaultState().with(RotatedPillarBlock.AXIS, blockState.get(RotatedPillarBlock.AXIS));
		}
		else if (block == Blocks.CHISELED_QUARTZ_BLOCK) {return AerialHellBlocksAndItems.CHISELED_SMOKY_QUARTZ_BLOCK.get().getDefaultState();}
		else if (block == Blocks.QUARTZ_BRICKS) {return AerialHellBlocksAndItems.SMOKY_QUARTZ_BRICKS.get().getDefaultState();}
		else if (block == Blocks.SMOOTH_QUARTZ) {return AerialHellBlocksAndItems.SMOOTH_SMOKY_QUARTZ.get().getDefaultState();}
		else if (block == AerialHellBlocksAndItems.GIANT_GANODERMA_APPLANATUM_BLOCK.get()) {return AerialHellBlocksAndItems.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.get().getDefaultState();}
		else if (block instanceof AerialHellBookshelfBlock || block == Blocks.BOOKSHELF)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_BOOKSHELF.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_BOOKSHELF.get()) {return AerialHellBlocksAndItems.GRAY_SHROOM_BOOKSHELF.get().getDefaultState();}
			else {return AerialHellBlocksAndItems.SHADOW_PINE_BOOKSHELF.get().getDefaultState();}
		}
		else if (block instanceof TrapDoorBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_TRAPDOOR.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_TRAPDOOR.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_TRAPDOOR.get();}
			else {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_TRAPDOOR.get();}
			return newBlock.getDefaultState().with(TrapDoorBlock.OPEN, blockState.get(TrapDoorBlock.OPEN)).with(TrapDoorBlock.HALF, blockState.get(TrapDoorBlock.HALF)).with(TrapDoorBlock.POWERED, blockState.get(TrapDoorBlock.POWERED)).with(TrapDoorBlock.HORIZONTAL_FACING, blockState.get(TrapDoorBlock.HORIZONTAL_FACING));
		}
		else if (block instanceof DoorBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_DOOR.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_DOOR.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_DOOR.get();}
			else {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_DOOR.get();}
			return newBlock.getDefaultState().with(DoorBlock.FACING, blockState.get(DoorBlock.FACING)).with(DoorBlock.OPEN, blockState.get(DoorBlock.OPEN)).with(DoorBlock.HINGE, blockState.get(DoorBlock.HINGE)).with(DoorBlock.POWERED, blockState.get(DoorBlock.POWERED)).with(DoorBlock.HALF, blockState.get(DoorBlock.HALF));
		}
		else if (block instanceof WoodButtonBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_BUTTON.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_BUTTON.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_BUTTON.get();}
			else {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_BUTTON.get();}
			return newBlock.getDefaultState().with(WoodButtonBlock.POWERED, blockState.get(WoodButtonBlock.POWERED)).with(WoodButtonBlock.FACE, blockState.get(WoodButtonBlock.FACE)).with(WoodButtonBlock.HORIZONTAL_FACING, blockState.get(WoodButtonBlock.HORIZONTAL_FACING));
		}
		else if (block instanceof PressurePlateBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_PRESSURE_PLATE.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_PRESSURE_PLATE.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_PRESSURE_PLATE.get();}
			else {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_PRESSURE_PLATE.get();}
			return newBlock.getDefaultState().with(PressurePlateBlock.POWERED, blockState.get(PressurePlateBlock.POWERED));
		}
		else if (block instanceof ComposterBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_COMPOSTER.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_COMPOSTER.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_COMPOSTER.get();}
			else {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_COMPOSTER.get();}
			return newBlock.getDefaultState().with(ComposterBlock.LEVEL, blockState.get(ComposterBlock.LEVEL));
		}
		else if (block instanceof AbstractSignBlock)
		{
			if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_SIGN.get() || block == AerialHellBlocksAndItems.AERIAL_TREE_SIGN.get()) {newBlock = AerialHellBlocksAndItems.GRAY_SHROOM_SIGN.get();}
			else {newBlock = AerialHellBlocksAndItems.SHADOW_PINE_SIGN.get();}
			if (block instanceof StandingSignBlock)
			{
				return newBlock.getDefaultState().with(AerialHellSignBlock.FLOOR, true);
			}
			else if (block instanceof AerialHellSignBlock)
			{
				return newBlock.getDefaultState().with(AerialHellSignBlock.ROTATION, blockState.get(StandingSignBlock.ROTATION)).with(AerialHellSignBlock.FLOOR, blockState.get(AerialHellSignBlock.FLOOR));
			}
			return newBlock.getDefaultState().with(AerialHellSignBlock.ROTATION, blockState.get(StandingSignBlock.ROTATION)).with(AerialHellSignBlock.FLOOR, false);
		}
		return AerialHellBlocksAndItems.SHADOW_CATACOMBS_BRICKS.get().getDefaultState();
	}
	
	@Override
    public void livingTick()
    {
		if (this.attackTimer > 0) {this.attackTimer--;}
		super.livingTick();
    }
	
	@Override public boolean canBePushed() {return false;}
	
	@Override
	public boolean attackEntityAsMob(Entity attackedEntity)
	{
	      this.world.setEntityState(this, (byte)4);
	      float f = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
	      float amount = (int)f > 0 ? f / 2.0F + (float)this.rand.nextInt((int)f) : f;
	      float kb = (float)this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
	      boolean flag = attackedEntity.attackEntityFrom(DamageSource.causeMobDamage(this), amount);
	      if (flag)
	      {
	    	 ((LivingEntity)attackedEntity).applyKnockback(kb * 0.5F, (double)MathHelper.sin(this.rotationYaw * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(this.rotationYaw * ((float)Math.PI / 180F))));
	         attackedEntity.setMotion(attackedEntity.getMotion().getX(), (double)0.8F, attackedEntity.getMotion().getZ());
	         this.applyEnchantments(this, attackedEntity);
	      }

	      this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
	      return flag;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 4)
		{
	         this.attackTimer = 10;
	         this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
	    }
		else {super.handleStatusUpdate(id);}
	}
	
	@Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_LILITH_HURT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_LILITH_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_LILITH_DEATH.get();}
	@Override public void playAmbientSound() {if (this.isTransforming()) {} else {super.playAmbientSound();}}

	private void dragEntity(Entity entityIn)
	{
		double factor = 0.2 / Math.max(5, this.getDistance(entityIn)); //0.04 / Math.max(1, this.getDistance(entityIn)); and multiply only one time, to get uniform dragging
		Vector3d toGod = new Vector3d(this.getPosX() - entityIn.getPosX(), this.getPosY() - entityIn.getPosY(), this.getPosZ() - entityIn.getPosZ()).mul(factor, factor, factor);
		entityIn.setMotion(entityIn.getMotion().add(toGod.mul(factor,factor,factor)));
	}
	
	private void transform()
	{
		this.setTransformed();
		spawnTransformationParticle();
	}
	
	public void spawnTransformationParticle()
	{
		if (this.world.isRemote)
        {
        	for(int i = 0; i < 30; ++i)
            {
            	double d0 = this.rand.nextGaussian() * 0.02D;
            	double d1 = this.rand.nextGaussian() * 0.02D;
            	double d2 = this.rand.nextGaussian() * 0.02D;
            	this.world.addParticle(AerialHellParticleTypes.SHADOW_PARTICLE.get(), this.getPosXWidth(1.0D) - d0 * 10.0D, this.getPosYRandom() - d1 * 10.0D, this.getPosZRandom(1.0D) - d2 * 10.0D, 2 * d0, d1, 2 * d2);
            }
        }
        else
        {
           this.world.setEntityState(this, (byte)20);
        }
	}
	
	/* Lilith Goals */
	
	public static class LilithMeleeAttackGoal extends BossMeleeAttackGoal
	{
		public LilithMeleeAttackGoal(LilithEntity godIn, double speedIn, boolean useLongMemory) {super(godIn, speedIn, useLongMemory);}
		@Override public boolean shouldExecute() {return !((LilithEntity) this.boss).isTransforming() && super.shouldExecute();}
		@Override public boolean shouldContinueExecuting() {return !((LilithEntity) this.boss).isTransforming() && super.shouldContinueExecuting();}
	}
	
	public static class LilithWaterAvoidingRandomWalkingGoal extends BossWaterAvoidingRandomWalkingGoal
	{
		public LilithWaterAvoidingRandomWalkingGoal(LilithEntity god, double speedIn) {super(god, speedIn);}
		@Override public boolean shouldExecute() {return !((LilithEntity) this.boss).isTransforming() && super.shouldExecute();}
		@Override public boolean shouldContinueExecuting() {return !((LilithEntity) this.boss).isTransforming() && super.shouldContinueExecuting();}
	}
}
