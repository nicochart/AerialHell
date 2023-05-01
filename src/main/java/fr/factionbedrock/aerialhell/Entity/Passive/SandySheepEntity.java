package fr.factionbedrock.aerialhell.Entity.Passive;

import fr.factionbedrock.aerialhell.Entity.AerialHellAnimalEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class SandySheepEntity extends AerialHellAnimalEntity
{
	private int shearedTimer;
    public static final DataParameter<Boolean> SHEARED = EntityDataManager.<Boolean>createKey(SandySheepEntity.class, DataSerializers.BOOLEAN);

    public SandySheepEntity(EntityType<? extends SandySheepEntity> type, World worldIn)
    {
        super(type, worldIn);
        this.shearedTimer = 200;
    }

    public SandySheepEntity(World worldIn)
    {
        this(AerialHellEntities.SANDY_SHEEP.get(), worldIn);
        this.shearedTimer = 200;
    }

    @Override
    protected void registerData()
    {
        super.registerData();
        this.dataManager.register(SHEARED, false);
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1, Ingredient.fromItems(AerialHellBlocksAndItems.AERIAL_BERRY.get()), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return AerialHellAnimalEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 12.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.26);
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) //getBreedOffspring
    {
        return AerialHellEntities.SANDY_SHEEP.get().create(this.world);
    }
    
    public boolean hasWool()
    {
    	return !this.dataManager.get(SHEARED);
    }
    
    public void setWool(boolean flag)
    {
        this.dataManager.set(SHEARED, !flag);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return AerialHellSoundEvents.ENTITY_SANDY_SHEEP_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return AerialHellSoundEvents.ENTITY_SANDY_SHEEP_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return AerialHellSoundEvents.ENTITY_SANDY_SHEEP_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState par4)
    {
        this.world.playSound(null, this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_SHEEP_STEP, SoundCategory.NEUTRAL, 0.15F, 1.0F);
    }

    @Override
    public void writeAdditional(CompoundNBT compound)
    {
        super.writeAdditional(compound);
        compound.putBoolean("Wool", this.hasWool());
    }

    @Override
    public void readAdditional(CompoundNBT compound)
    {
        super.readAdditional(compound);
        this.setWool(compound.getBoolean("Wool"));
    }

    @Override
    public boolean hitByEntity(Entity entityIn)
    {
    	if (this.hasWool())
    	{
    		this.setWool(false);
    		this.entityDropItem(new ItemStack(Items.YELLOW_WOOL));
    		for (int i=0;i<10;i++)
            {
    			double random = rand.nextFloat() * 2;
    			double x = getPosX() + (rand.nextFloat() - 0.5F) * random;
    			double y = (this.getBoundingBox().minY + random) + 0.5D;
    			double z = getPosZ() + (rand.nextFloat() - 0.5F) * random;
    			double dx = (rand.nextFloat() - 0.5F)/10;
    			double dz = (rand.nextFloat() - 0.5F)/10;
    			this.world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, AerialHellBlocksAndItems.SLIPPERY_SAND.get().getDefaultState()), x, y, z, dx, -0.06D, dz);
            }
    	}
        return false;
    }
    
    @Override
    public void tick()
    {
    	if (this.shearedTimer > 4200 && !this.hasWool())
        {
        	this.setWool(true);
            this.shearedTimer = 0;
        }
    	if (!this.hasWool())
    	{
    		this.shearedTimer++;
    	}
        super.tick();
    }
}
