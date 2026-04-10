package fr.factionbedrock.aerialhell.Entity.Passive;

import fr.factionbedrock.aerialhell.Entity.AerialHellAnimalEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import org.jetbrains.annotations.Nullable;

public class SandySheepEntity extends AerialHellAnimalEntity
{
	private int shearedTimer;
    public static final EntityDataAccessor<Boolean> SHEARED = SynchedEntityData.<Boolean>defineId(SandySheepEntity.class, EntityDataSerializers.BOOLEAN);

    public SandySheepEntity(EntityType<? extends SandySheepEntity> type, Level world)
    {
        super(type, world);
        this.shearedTimer = 200;
    }

    public SandySheepEntity(Level world)
    {
        this(AerialHellEntities.SANDY_SHEEP, world);
        this.shearedTimer = 200;
    }

    @Override public boolean isFood(ItemStack stack) {return super.isFood(stack) || stack.getItem() == AerialHellItems.STELLAR_WHEAT;}

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(SHEARED, false);
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return AerialHellAnimalEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.TEMPT_RANGE, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.26);
    }

    @Nullable @Override public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob mob)
    {
        return AerialHellEntities.SANDY_SHEEP.create(this.level(), EntitySpawnReason.BREEDING);
    }
    
    public boolean hasWool() {return !this.getEntityData().get(SHEARED);}
    public void setWool(boolean flag) {this.getEntityData().set(SHEARED, !flag);}

    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_SANDY_SHEEP_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_SANDY_SHEEP_HURT;}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_SANDY_SHEEP_DEATH;}

    @Override protected void playStepSound(BlockPos pos, BlockState par4)
    {
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.SHEEP_STEP, SoundSource.NEUTRAL, 0.15F, 1.0F);
    }

    @Override protected void addAdditionalSaveData(ValueOutput view)
    {
        super.addAdditionalSaveData(view);
        view.putBoolean("Wool", this.hasWool());
    }

    @Override protected void readAdditionalSaveData(ValueInput view)
    {
        super.readAdditionalSaveData(view);
        this.setWool(view.getBooleanOr("Wool", false));
    }

    @Override public boolean skipAttackInteraction(Entity entityIn)
    {
    	if (this.hasWool())
    	{
    		this.setWool(false);
            if (this.level() instanceof ServerLevel serverWorld) {this.spawnAtLocation(serverWorld, new ItemStack(Items.YELLOW_WOOL));}

    		for (int i=0;i<10;i++)
            {
    			double rand = random.nextFloat() * 2;
    			double x = getX() + (random.nextFloat() - 0.5F) * rand;
    			double y = (this.getBoundingBox().minY + rand) + 0.5D;
    			double z = getZ() + (random.nextFloat() - 0.5F) * rand;
    			double dx = (random.nextFloat() - 0.5F)/10;
    			double dz = (random.nextFloat() - 0.5F)/10;
    			this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, AerialHellBlocks.SLIPPERY_SAND.defaultBlockState()), x, y, z, dx, -0.06D, dz);
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
