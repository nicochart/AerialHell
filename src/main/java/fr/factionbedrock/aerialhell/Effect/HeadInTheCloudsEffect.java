package fr.factionbedrock.aerialhell.Effect;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import java.util.Map;

import static fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects.HEAD_IN_THE_CLOUDS_GRAVITY_MODIFIER;

public class HeadInTheCloudsEffect extends MobEffect
{
    public HeadInTheCloudsEffect(MobEffectCategory typeIn, int liquidColorIn) {super(typeIn, liquidColorIn);}

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entityLivingBaseIn, int amplifier)
    {
        /*TODO doesn't work because MobEffect can't impact entity movements*/
        double x=entityLivingBaseIn.getDeltaMovement().x, y=entityLivingBaseIn.getDeltaMovement().y, z=entityLivingBaseIn.getDeltaMovement().z;
        double xNew = x, yNew = y, zNew = z;
        if (!entityLivingBaseIn.onGround())
        {
            double maxHorizontalSpeed = 0.17f * (1 + amplifier);
            double horizontalSpeed = Math.sqrt(x * x + z * z);
            if (horizontalSpeed < maxHorizontalSpeed) {xNew = x*1.1; zNew = z*1.1;} //maximize horizontal speed if speed is not already too high
        }

        double yMin = Math.min(-0.2 + 0.05 * amplifier, 0.0); //amplifier=0 : -0.2  ;  amplifier=1 : -0.15  ; amplifier=2 : -0.1  ;  amplifier=3 : -0.05  ;  amplifier=4+ : 0.0
		if (entityLivingBaseIn.isCrouching()) {xNew /= 1.2; zNew /= 1.2; if (yNew > -2) {yNew -= 0.02;}} //slow down horizontal speed, faster vertical speed if player is crouching
        else if (y < yMin) {yNew = yMin;} //minimize vertical speed if player is not crouching

        entityLivingBaseIn.setDeltaMovement(xNew, yNew, zNew);
        entityLivingBaseIn.resetFallDistance();
        return true;
    }

    @Override public boolean isInstantenous() {return false;}

    @Override public void addAttributeModifiers(AttributeMap attributeMap, int amplifier)
    {
        for (Map.Entry<Holder<Attribute>, AttributeTemplate> entry : this.attributeModifiers.entrySet())
        {
            AttributeInstance attributeinstance = attributeMap.getInstance(entry.getKey());
            if (attributeinstance != null)
            {
                int appliedAmplifier = (entry.getValue().id() == HEAD_IN_THE_CLOUDS_GRAVITY_MODIFIER) ? 0 : amplifier;
                attributeinstance.removeModifier(entry.getValue().id());
                attributeinstance.addPermanentModifier(entry.getValue().create(appliedAmplifier));
            }
        }
    }
}