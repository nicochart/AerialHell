package fr.factionbedrock.aerialhell.Entity.Monster;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

import javax.annotation.Nonnull;

public class CrystalSlimeEntity extends Slime
{
	public CrystalSlimeEntity(EntityType<? extends CrystalSlimeEntity> type, Level worldIn)
	{
		super(type, worldIn);
	}

	public CrystalSlimeEntity(Level worldIn)
	{
		super(AerialHellEntities.CRYSTAL_SLIME.get(), worldIn);
		this.xpReward = 10;
		/*setSlimeSize(2, false); ?? When summoned with trapped blocks, it appears little ..*/
	}

	@Override
	protected void registerGoals()
	{
		/*this.goalSelector.addGoal(1, new Slime.SlimeFloatGoal(this)); TODO : Update access transformer
		this.goalSelector.addGoal(2, new Slime.SlimeAttackGoal(this));
		this.goalSelector.addGoal(3, new Slime.SlimeRandomDirectionGoal(this));
		this.goalSelector.addGoal(5, new Slime.SlimeKeepOnJumpingGoal(this));*/
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (entity) -> Math.abs(entity.getY() - this.getY()) <= 4.0));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
	}
	
	@Override
	protected void setSize(int size, boolean resetHealth)
	{
		//this.entityData.set(ID_SIZE, 2); TODO : Update access transformer
		super.setSize(size, resetHealth);
	}
	
	public static AttributeSupplier.Builder registerAttributes()
    {
        return Slime.createMobAttributes()
        		.add(Attributes.ATTACK_DAMAGE, 4D)
        		.add(Attributes.MOVEMENT_SPEED, 0.25D)
        		.add(Attributes.MAX_HEALTH, 24.0D)
        		.add(Attributes.FOLLOW_RANGE, 16.0D);
    }
	
	public static boolean canSpawn(EntityType<CrystalSlimeEntity> type, ServerLevelAccessor worldIn, MobSpawnType reason, BlockPos pos, Random randomIn)
    {
        return randomIn.nextInt(10) == 0 && worldIn.getLevel().isDay();
    }
	
	@Override protected ParticleOptions getParticleType()
	{
		return new BlockParticleOption(ParticleTypes.BLOCK, AerialHellBlocksAndItems.CRYSTAL_BLOCK.get().defaultBlockState());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EntityType<? extends CrystalSlimeEntity> getType() {return (EntityType<? extends CrystalSlimeEntity>) super.getType();}

	@Override public void remove(@Nonnull Entity.RemovalReason p_146834_) //copied from Entity class
	{
		this.setRemoved(p_146834_);
		if (p_146834_ == Entity.RemovalReason.KILLED) {this.gameEvent(GameEvent.ENTITY_KILLED);}
		this.invalidateCaps();
	}

	@Override protected ResourceLocation getDefaultLootTable() {return this.getType().getDefaultLootTable();}
}