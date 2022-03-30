package fr.factionbedrock.aerialhell.Entity.Monster;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public class CrystalSlimeEntity extends SlimeEntity
{
	public CrystalSlimeEntity(EntityType<? extends CrystalSlimeEntity> type, World worldIn)
	{
		super(type, worldIn);
	}

	public CrystalSlimeEntity(World worldIn)
	{
		super(AerialHellEntities.CRYSTAL_SLIME.get(), worldIn);
		/*setSlimeSize(2, false); ?? When summoned with trapped blocks, it appears little ..*/
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new SlimeEntity.FloatGoal(this));
		this.goalSelector.addGoal(2, new SlimeEntity.AttackGoal(this));
		this.goalSelector.addGoal(3, new SlimeEntity.FaceRandomGoal(this));
		this.goalSelector.addGoal(5, new SlimeEntity.HopGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, (entity) -> Math.abs(entity.getPosY() - this.getPosY()) <= 4.0));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
	}
	
	@Override
	protected void setSlimeSize(int size, boolean resetHealth)
	{
	      this.dataManager.set(SLIME_SIZE, 2);
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return SlimeEntity.func_233666_p_()
        		.createMutableAttribute(Attributes.ATTACK_DAMAGE, 4D)
        		.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
        		.createMutableAttribute(Attributes.MAX_HEALTH, 50.0D)
        		.createMutableAttribute(Attributes.FOLLOW_RANGE, 16.0D);
    }
	
	public static boolean canSpawn(EntityType<CrystalSlimeEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn)
    {
        return randomIn.nextInt(10) == 0 && worldIn.getWorld().isDaytime();
    }
	
	@Override
	protected IParticleData getSquishParticle()
	{
		return new BlockParticleData(ParticleTypes.BLOCK, AerialHellBlocksAndItems.MUD_BRICKS.get().getDefaultState());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EntityType<? extends CrystalSlimeEntity> getType()
	{
		return (EntityType<? extends CrystalSlimeEntity>) super.getType();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void remove(boolean keepData)
	{
		this.removed = true;
		super.remove(keepData);
	}
	
	@Override
	protected ResourceLocation getLootTable()
	{
		return this.getType().getLootTable();
	}
}