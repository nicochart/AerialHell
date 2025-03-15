package fr.factionbedrock.aerialhell.Entity.Passive;

import fr.factionbedrock.aerialhell.Entity.AerialHellAnimalEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SandySheepEntity extends AerialHellAnimalEntity
{
	private int shearedTimer;
    public static final TrackedData<Boolean> SHEARED = DataTracker.<Boolean>registerData(SandySheepEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public SandySheepEntity(EntityType<? extends SandySheepEntity> type, World world)
    {
        super(type, world);
        this.shearedTimer = 200;
    }

    public SandySheepEntity(World world)
    {
        this(AerialHellEntities.SANDY_SHEEP, world);
        this.shearedTimer = 200;
    }

    @Override public boolean isBreedingItem(ItemStack stack) {return super.isBreedingItem(stack) || stack.getItem() == AerialHellItems.STELLAR_WHEAT;}

    @Override protected void initDataTracker(DataTracker.Builder builder)
    {
        super.initDataTracker(builder);
        builder.add(SHEARED, false);
    }

    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return AerialHellAnimalEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 12.0D)
                .add(EntityAttributes.TEMPT_RANGE, 10.0D)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.26);
    }

    @Nullable @Override public PassiveEntity createChild(ServerWorld serverWorld, PassiveEntity mob)
    {
        return AerialHellEntities.SANDY_SHEEP.create(this.getWorld(), SpawnReason.BREEDING);
    }
    
    public boolean hasWool() {return !this.getDataTracker().get(SHEARED);}
    public void setWool(boolean flag) {this.getDataTracker().set(SHEARED, !flag);}

    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_SANDY_SHEEP_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_SANDY_SHEEP_HURT;}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_SANDY_SHEEP_DEATH;}

    @Override protected void playStepSound(BlockPos pos, BlockState par4)
    {
        this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_SHEEP_STEP, SoundCategory.NEUTRAL, 0.15F, 1.0F);
    }

    @Override public void writeCustomDataToNbt(NbtCompound nbt)
    {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Wool", this.hasWool());
    }

    @Override public void readCustomDataFromNbt(NbtCompound nbt)
    {
        super.readCustomDataFromNbt(nbt);
        this.setWool(nbt.getBoolean("Wool"));
    }

    @Override public boolean handleAttack(Entity entityIn)
    {
    	if (this.hasWool())
    	{
    		this.setWool(false);
            if (this.getWorld() instanceof ServerWorld serverWorld) {this.dropStack(serverWorld, new ItemStack(Items.YELLOW_WOOL));}

    		for (int i=0;i<10;i++)
            {
    			double rand = random.nextFloat() * 2;
    			double x = getX() + (random.nextFloat() - 0.5F) * rand;
    			double y = (this.getBoundingBox().minY + rand) + 0.5D;
    			double z = getZ() + (random.nextFloat() - 0.5F) * rand;
    			double dx = (random.nextFloat() - 0.5F)/10;
    			double dz = (random.nextFloat() - 0.5F)/10;
    			this.getWorld().addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, AerialHellBlocks.SLIPPERY_SAND.getDefaultState()), x, y, z, dx, -0.06D, dz);
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
