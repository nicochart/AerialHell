package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class AerialHellDamageTypes
{
    public static final ResourceKey<DamageType> GOD_BLESS = create("god_bless");
    public static final ResourceKey<DamageType> WEB_THORNS = create("web_thorns");
    public static final ResourceKey<DamageType> BRAMBLES_THORNS = create("brambles_thorns");
    public static final ResourceKey<DamageType> ROOT_THORNS = create("root_thorns");
    public static final ResourceKey<DamageType> SHURIKEN_HIT = create("shuriken_hit");
    public static final ResourceKey<DamageType> LUNATIC_PROJECTION = create("lunatic_projection");
    public static final ResourceKey<DamageType> CURSED_TOOL = create("cursed_tool");
    public static final ResourceKey<DamageType> GOLEM_BEAM = create("golem_beam");
    public static final ResourceKey<DamageType> VOLUCITE_WARDEN_MAIN_BEAM = create("volucite_warden_main_beam"); //damage type json not added yet (volucite warden not ported yet)

    public static final float GOLEM_BEAM_ARMOR_PIERCING = 0.25F;
    public static final float GOLEM_BEAM_ENCHANTMENT_PIERCING = 0.25F;
    public static final float VOLUCITE_WARDEN_MAIN_BEAM_ARMOR_PIERCING = 0.5F;
    public static final float VOLUCITE_WARDEN_MAIN_BEAM_ENCHANTMENT_PIERCING = 0.5F;

    private static ResourceKey<DamageType> create(String name) {return ResourceKey.create(Registries.DAMAGE_TYPE, AerialHell.id(name));}

    //fraction of the armor protection ignored by the damage. 0.0F : normal armor protection, 1.0F : armor fully ignored. 0.5F : armor only blocks half of what it normally blocks
    //only armor points & armor toughness are concerned (armor durability loss stays the same, enchantments : see getEnchantmentPiercing). applied in LivingArmorAbsorbMixin
    public static float getArmorPiercing(DamageSource damageSource)
    {
        if (damageSource.is(GOLEM_BEAM)) {return GOLEM_BEAM_ARMOR_PIERCING;}
        if (damageSource.is(VOLUCITE_WARDEN_MAIN_BEAM)) {return VOLUCITE_WARDEN_MAIN_BEAM_ARMOR_PIERCING;}
        return 0.0F;
    }

    //fraction of the protection enchantments reduction ignored by the damage (same scale as getArmorPiercing). applied in LivingMagicAbsorbMixin
    //effects reduction (resistance) is never ignored
    public static float getEnchantmentPiercing(DamageSource damageSource)
    {
        if (damageSource.is(GOLEM_BEAM)) {return GOLEM_BEAM_ENCHANTMENT_PIERCING;}
        if (damageSource.is(VOLUCITE_WARDEN_MAIN_BEAM)) {return VOLUCITE_WARDEN_MAIN_BEAM_ENCHANTMENT_PIERCING;}
        return 0.0F;
    }

    public static DamageSource getDamageSource(Level level, ResourceKey<DamageType> typeKey)
    {
        return getDamageSource(level, typeKey, null, null);
    }

    public static DamageSource getDamageSource(Level world, ResourceKey<DamageType> typeKey, @Nullable Entity immediateSource, @Nullable Entity trueSource)
    {
        return new DamageSource(world.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(typeKey), immediateSource, trueSource);
    }
}
