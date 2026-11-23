package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.Util.VoluciteGlyphParticles;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

//beacon sustaining the anchored volucite warden shield, standing on a boss room pedestal. looks like a volucite pillar (arm segment tail, see VoluciteWardenShieldBeaconModel)
//placed by the warden when a shield transition starts : 2 to 4 beacons depending on the boss difficulty (see BossRoomShieldBeaconsHandler)
//linked to its warden (saved, see setWarden) :
//  a fraction of the taken damage is also dealt to VW
//  same resistance as the warden (boss difficulty) : harder to break with more players
public class VoluciteWardenShieldBeaconEntity extends AbstractVoluciteWardenBeaconEntity
{
    /* -- Warden link fields -- */
    @Nullable private EntityReference<VoluciteWardenEntity> warden; //linked warden (saved uuid, resolved again once the warden is loaded), also the beacon target (synced for the link particles). server side
    private float wardenDamageMultiplier = 1.0F; //part of the beacons damage dealt by this beacon to the warden : 1/n with n beacons, given by the warden (see BossRoomShieldBeaconsHandler). saved
    private static final float SHIELD_RATIO_PER_HEALTH = 0.01F; //warden shield lost per beacon health point lost (relative to the warden max health), before the damage multiplier : 1%
    private static final int CLOUD_PARTICLE_CHANCE = 2, GLYPH_PARTICLE_CHANCE = 8; //link particles : 1 chance in n per tick
    /* ------------------------ */

    public VoluciteWardenShieldBeaconEntity(EntityType<? extends VoluciteWardenShieldBeaconEntity> type, Level level) {super(type, level);}

    public static AttributeSupplier.Builder registerAttributes() {return createBeaconAttributes().add(Attributes.MAX_HEALTH, 75.0D);}

    @Override public void tick()
    {
        super.tick(); //synced target (see AbstractVoluciteWardenBeaconEntity)
        if (this.level().isClientSide()) {this.tickLinkParticles();}
        else if (this.isAlive()) {this.tickWardenLink();}
    }

    /* ----------------------------------------- */
    /* ---------- Warden link (server) --------- */
    /* ----------------------------------------- */

    //linked to this warden, dealing wardenDamageMultiplier of the beacons damage (1/n with n beacons)
    public void setWarden(VoluciteWardenEntity warden, float wardenDamageMultiplier)
    {
        this.warden = EntityReference.of(warden);
        this.wardenDamageMultiplier = wardenDamageMultiplier;
        this.setTarget(warden);
    }

    //linked warden, if it is loaded (alive or not)
    @Nullable private VoluciteWardenEntity getLinkedWarden() {return this.warden != null ? this.warden.getEntity(this.level(), VoluciteWardenEntity.class) : null;}

    //linked warden, if it is loaded and alive
    @Nullable public VoluciteWardenEntity getWarden()
    {
        @Nullable VoluciteWardenEntity warden = this.getLinkedWarden();
        return warden != null && warden.isAlive() ? warden : null;
    }

    //warden shield down (shield broken, or warden dead) : the beacon disappears. warden not loaded : the beacon waits for it
    //otherwise : the warden is the beacon target (synced, set again once the warden is loaded), same resistance as the warden
    private void tickWardenLink()
    {
        @Nullable VoluciteWardenEntity warden = this.getLinkedWarden();
        if (warden != null && (!warden.isAlive() || !isWardenShieldUp(warden))) {this.disappear(); return;}

        if (this.getTarget() != warden) {this.setTarget(warden);}
        this.copyWardenResistance(warden);
    }

    //shield transitions & shield phases
    private static boolean isWardenShieldUp(VoluciteWardenEntity warden) {return warden.isInShieldTransition() || warden.isInShieldPhase();}

    //glyphs scattering from the beacon (opposite of the placement glyphs, see BossRoomShieldBeaconsHandler) & a few clouds, then removed : no death animation, no drop
    private void disappear()
    {
        if (this.level() instanceof ServerLevel level)
        {
            Vec3 center = this.position().add(0.0D, this.getBbHeight() / 2.0F, 0.0D);
            VoluciteGlyphParticles.sendOutward(level, center, 1.5F, 3.5F, 12);
            level.sendParticles(ParticleTypes.CLOUD, center.x, center.y, center.z, 12, 0.25D, 0.5D, 0.25D, 0.05D); //spread over the beacon height
            level.playSound(null, center.x, center.y, center.z, SoundEvents.STONE_BREAK, this.getSoundSource(), 2.0F, 0.6F);
        }
        this.discard();
    }

    //same resistance effect as the warden (boss difficulty, see AbstractBossEntity.adaptBossDifficulty). no resistance without warden
    private void copyWardenResistance(@Nullable VoluciteWardenEntity warden)
    {
        @Nullable MobEffectInstance wardenResistance = warden != null ? warden.getEffect(MobEffects.RESISTANCE) : null;
        @Nullable MobEffectInstance resistance = this.getEffect(MobEffects.RESISTANCE);
        int wardenAmplifier = wardenResistance != null ? wardenResistance.getAmplifier() : -1;
        if (wardenAmplifier == (resistance != null ? resistance.getAmplifier() : -1)) {return;}

        this.removeEffect(MobEffects.RESISTANCE);
        if (wardenResistance != null) {this.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, MobEffectInstance.INFINITE_DURATION, wardenAmplifier, false, false));}
    }

    /* ------------------------------------------- */
    /* ---------- Link particles (client) -------- */
    /* ------------------------------------------- */

    //while the warden (synced target) shield is up : a few clouds along the link, drifting toward the warden, and glyphs traveling from the beacon top to the warden core
    private void tickLinkParticles()
    {
        if (!(this.getSyncedTarget() instanceof VoluciteWardenEntity warden) || !isWardenShieldUp(warden)) {return;}

        Vec3 start = this.position().add(0.0D, this.getBbHeight(), 0.0D);
        Vec3 end = warden.getCoreLightningStartPos();
        Vec3 path = end.subtract(start);
        if (this.random.nextInt(CLOUD_PARTICLE_CHANCE) == 0)
        {
            Vec3 pos = start.add(path.scale(this.random.nextDouble()));
            Vec3 speed = path.normalize().scale(0.05D);
            this.level().addParticle(ParticleTypes.CLOUD, pos.x + this.random.nextGaussian() * 0.15D, pos.y + this.random.nextGaussian() * 0.15D, pos.z + this.random.nextGaussian() * 0.15D, speed.x, speed.y, speed.z);
        }
        if (this.random.nextInt(GLYPH_PARTICLE_CHANCE) == 0) {VoluciteGlyphParticles.spawn(this.level(), start, end);}
    }

    /* ---------------------------- */
    /* ---------- Damage ---------- */
    /* ---------------------------- */

    //health lost during the warden shield phases is also lost by the warden shield (see VoluciteWardenEntity.hurtShieldFromBeacon).
    //1 health point = 1% of the shield x the warden damage multiplier (based on the health really lost, caped by beacon remaining health)
    @Override public boolean hurtServer(ServerLevel level, DamageSource source, float amount)
    {
        float healthBefore = this.getHealth();
        boolean hurt = super.hurtServer(level, source, amount); //not hurt by the warden or its allies (see AbstractVoluciteWardenBeaconEntity)
        float lostHealth = healthBefore - this.getHealth();
        @Nullable VoluciteWardenEntity warden = this.getWarden();
        if (hurt && lostHealth > 0.0F && warden != null && warden.isInShieldPhase() && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) //kill & void damage not sent to warden
        {
            warden.hurtShieldFromBeacon(level, source, lostHealth * this.wardenDamageMultiplier * SHIELD_RATIO_PER_HEALTH);
        }
        return hurt;
    }

    @Override protected void addAdditionalSaveData(ValueOutput valueOutput)
    {
        super.addAdditionalSaveData(valueOutput);
        EntityReference.store(this.warden, valueOutput, "warden");
        valueOutput.putFloat("warden_damage_multiplier", this.wardenDamageMultiplier);
    }

    @Override protected void readAdditionalSaveData(ValueInput valueInput)
    {
        super.readAdditionalSaveData(valueInput);
        this.warden = EntityReference.read(valueInput, "warden");
        this.wardenDamageMultiplier = valueInput.getFloatOr("warden_damage_multiplier", 1.0F);
    }
}
