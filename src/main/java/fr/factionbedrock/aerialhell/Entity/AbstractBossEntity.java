package fr.factionbedrock.aerialhell.Entity;

import javax.annotation.Nullable;

import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

import java.util.List;

public class AbstractBossEntity extends AbstractActivableEntity
{
	protected final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.PROGRESS);
	
	public AbstractBossEntity(EntityType<? extends Monster> type, Level world) {super(type, world);}
	
	/* Add the given player to the list of players tracking this entity. For instance, a player may track a boss in order to view its associated boss bar. */
	@Override public void startSeenByPlayer(ServerPlayer player)
	{
    	super.startSeenByPlayer(player);
    	this.bossInfo.addPlayer(player);
    }

    /* Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for more information on tracking. */
	@Override public void stopSeenByPlayer(ServerPlayer player)
    {
    	super.stopSeenByPlayer(player);
    	this.bossInfo.removePlayer(player);
    }
	
	@Override public void readAdditionalSaveData(CompoundTag compound)
	{
	      super.readAdditionalSaveData(compound);
	      if (this.hasCustomName()) {this.bossInfo.setName(this.getDisplayName());}
	}
	
	@Override public void setCustomName(@Nullable Component name)
	{
	      super.setCustomName(name);
	      this.bossInfo.setName(this.getDisplayName());
	}
	
	@Override protected void customServerAiStep()
	{
		super.customServerAiStep();
		this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
	}

	@Override public void tick()
	{
		super.tick();
		if (this.isActive() && this.tickCount % 900 == 0) {this.adaptBossDifficulty();}
		this.bossInfo.setVisible(this.isActive());
	}

	@Override public void setActive(boolean isActive)
	{
		super.setActive(isActive);
		this.adaptBossDifficulty();
	}

	public void adaptBossDifficulty()
	{
		List<Entity> nearbyEntities = this.level.getEntities(this, this.getBoundingBox().inflate(30), EntitySelector.withinDistance(this.getX(), this.getY(), this.getZ(), 15));
		int difficulty = -1;
		for (Entity entity : nearbyEntities)
		{
			if (entity instanceof Player)
			{
				Player player = (Player) entity;
				if (!(player.isCreative() || player.isSpectator())) {difficulty += 1;}
			}
		}
		if (this.hasEffect(MobEffects.DAMAGE_RESISTANCE)) {this.removeEffect(MobEffects.DAMAGE_RESISTANCE);}
		if (this.hasEffect(MobEffects.DAMAGE_BOOST)) {this.removeEffect(MobEffects.DAMAGE_BOOST);}
		if (difficulty > 0) //is 0 if there is only one player, +1 per additional player
		{
			this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 54000, Math.min(3, (int) Math.ceil(difficulty / 2.0F)), false, false));
			this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 54000, Math.min(3, difficulty - 1), false, false));
		}
	}

	@Override public int getMinTimeToActivate() {return 5;}
	@Override public double getMinDistanceToActivate() {return 8;}
	@Override public double getMinDistanceToDeactivate() {return 48;}
	@Override public boolean removeWhenFarAway(double distanceToClosestPlayer) {return false;}
	@Override public boolean canChangeDimensions() {return false;}
}
