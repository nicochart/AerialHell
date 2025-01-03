package fr.factionbedrock.aerialhell.Entity.Passive;

import fr.factionbedrock.aerialhell.Entity.AerialHellAnimalEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

public class SandySheepEntity extends AerialHellAnimalEntity
{
	private int shearedTimer;
    public static final EntityDataAccessor<Boolean> SHEARED = SynchedEntityData.<Boolean>defineId(SandySheepEntity.class, EntityDataSerializers.BOOLEAN);

    public SandySheepEntity(EntityType<? extends SandySheepEntity> type, Level worldIn)
    {
        super(type, worldIn);
        this.shearedTimer = 200;
    }

    public SandySheepEntity(Level worldIn)
    {
        this(AerialHellEntities.SANDY_SHEEP.get(), worldIn);
        this.shearedTimer = 200;
    }

    @Override public boolean isFood(ItemStack stack)
    {
        return super.isFood(stack) || stack.getItem() == AerialHellBlocksAndItems.STELLAR_WHEAT_ITEM.get();
    }

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(SHEARED, false);
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return AerialHellAnimalEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.26);
    }

    @Nullable @Override public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob mob)
    {
        return AerialHellEntities.SANDY_SHEEP.get().create(this.level());
    }
    
    public boolean hasWool() {return !this.entityData.get(SHEARED);}
    public void setWool(boolean flag) {this.entityData.set(SHEARED, !flag);}

    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_SANDY_SHEEP_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_SANDY_SHEEP_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_SANDY_SHEEP_DEATH.get();}

    @Override protected void playStepSound(BlockPos pos, BlockState par4)
    {
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.SHEEP_STEP, SoundSource.NEUTRAL, 0.15F, 1.0F);
    }

    @Override public void addAdditionalSaveData(CompoundTag compound)
    {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Wool", this.hasWool());
    }

    @Override public void readAdditionalSaveData(CompoundTag compound)
    {
        super.readAdditionalSaveData(compound);
        this.setWool(compound.getBoolean("Wool"));
    }

    @Override public boolean skipAttackInteraction(Entity entityIn)
    {
    	if (this.hasWool())
    	{
    		this.setWool(false);
    		this.spawnAtLocation(new ItemStack(Items.YELLOW_WOOL));
    		for (int i=0;i<10;i++)
            {
    			double rand = random.nextFloat() * 2;
    			double x = getX() + (random.nextFloat() - 0.5F) * rand;
    			double y = (this.getBoundingBox().minY + rand) + 0.5D;
    			double z = getZ() + (random.nextFloat() - 0.5F) * rand;
    			double dx = (random.nextFloat() - 0.5F)/10;
    			double dz = (random.nextFloat() - 0.5F)/10;
    			this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, AerialHellBlocksAndItems.SLIPPERY_SAND.get().defaultBlockState()), x, y, z, dx, -0.06D, dz);
            }
    	}
        return false;
    }
    
    @Override public void tick()
    {
    	if (this.shearedTimer > 4200 && !this.hasWool())
        {
        	this.setWool(true);
            this.shearedTimer = 0;
        }
    	if (!this.hasWool()) {this.shearedTimer++;}
        super.tick();
    }
}
