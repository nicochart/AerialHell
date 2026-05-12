package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class CrystalCaterpillarEntity extends AbstractCaterpillarEntity implements LunarMisleadableEntity
{
	public CrystalCaterpillarEntity(EntityType<? extends AbstractCaterpillarEntity> type, Level level) {super(type, level);}

	public CrystalCaterpillarEntity(Level level) {this(AerialHellEntities.CRYSTAL_CATERPILLAR.get(), level);}

	/* ------- MisleadableEntity : Interface method implementation ------- */
	@Override public Mob getSelf() {return this;}
	/* ------------------------------------------------------------------- */

	/* ------- MisleadableEntity : Superclass methods Overridden to delegate to interface ------- */
	@Override public boolean hurtServer(ServerLevel serverLevel, DamageSource source, float amount)
	{
		return this.misleadableHurtServer(serverLevel, source, amount, super::hurtServer);
	}

	@Override public void die(DamageSource damageSource)
	{
		this.misleadableDie(damageSource);
		super.die(damageSource);
	}

	@Override public boolean canAttack(LivingEntity target) {return this.misleadableCanAttack(target, super::canAttack);}
	/* ------------------------------------------------------------------------------------------ */
	
	@Override protected void registerGoals()
	{
	      super.registerGoals();
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true, (potentialTarget, serverLevel) -> !this.isMisleadedBy(potentialTarget)));
	}
	
	public static AttributeSupplier.Builder registerAttributes()
    {
        return createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.FOLLOW_RANGE, 12.0D)
        		.add(Attributes.ATTACK_DAMAGE, 2.0D);
    }
}
