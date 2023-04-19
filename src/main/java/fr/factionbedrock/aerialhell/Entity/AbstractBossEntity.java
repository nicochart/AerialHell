package fr.factionbedrock.aerialhell.Entity;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

import java.util.List;

public class AbstractBossEntity extends AbstractActivableEntity
{
	protected final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS));
	
	public AbstractBossEntity(EntityType<? extends MonsterEntity> type, World world) {super(type, world);}
	
	/* Add the given player to the list of players tracking this entity. For instance, a player may track a boss in order to view its associated boss bar. */
	@Override public void addTrackingPlayer(ServerPlayerEntity player)
	{
    	super.addTrackingPlayer(player);
    	this.bossInfo.addPlayer(player);
    }

    /* Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for more information on tracking. */
	@Override public void removeTrackingPlayer(ServerPlayerEntity player)
    {
    	super.removeTrackingPlayer(player);
    	this.bossInfo.removePlayer(player);
    }
	
	@Override public void readAdditional(CompoundNBT compound)
	{
	      super.readAdditional(compound);
	      if (this.hasCustomName()) {this.bossInfo.setName(this.getDisplayName());}
	}
	
	@Override public void setCustomName(@Nullable ITextComponent name)
	{
	      super.setCustomName(name);
	      this.bossInfo.setName(this.getDisplayName());
	}
	
	@Override protected void updateAITasks()
	{
		super.updateAITasks();
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
	}

	@Override public void tick()
	{
		super.tick();
		if (this.isActive() && this.ticksExisted % 900 == 0) {this.adaptBossDifficulty();}
	}

	@Override public void setActive(boolean isActive)
	{
		super.setActive(isActive);
		this.adaptBossDifficulty();
	}

	public void adaptBossDifficulty()
	{
		List<Entity> nearbyEntities = this.world.getEntitiesInAABBexcluding(this, this.getBoundingBox().grow(30), EntityPredicates.withinRange(this.getPosX(), this.getPosY(), this.getPosZ(), 15));
		int difficulty = -1;
		for (Entity entity : nearbyEntities)
		{
			if (entity instanceof PlayerEntity)
			{
				PlayerEntity player = (PlayerEntity) entity;
				if (!(player.isCreative() || player.isSpectator())) {difficulty += 1;}
			}
		}
		if (this.isPotionActive(Effects.RESISTANCE)) {this.removePotionEffect(Effects.RESISTANCE);}
		if (this.isPotionActive(Effects.STRENGTH)) {this.removePotionEffect(Effects.STRENGTH);}
		if (difficulty > 0) //is 0 if there is only one player, +1 per additional player
		{
			this.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 54000, Math.min(6,difficulty * 2), false, false));
			this.addPotionEffect(new EffectInstance(Effects.STRENGTH, 54000, Math.min(3, difficulty - 1), false, false));
		}
	}

	@Override public int getMinTimeToActivate() {return 5;}
	@Override public double getMinDistanceToActivate() {return 8;}
	@Override public double getMinDistanceToDeactivate() {return 48;}
	@Override public boolean canDespawn(double distanceToClosestPlayer) {return false;}
	@Override public boolean isNonBoss() {return false;}
}
