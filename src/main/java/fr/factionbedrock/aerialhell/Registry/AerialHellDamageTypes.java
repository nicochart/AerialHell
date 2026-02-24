package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AerialHellDamageTypes
{
    public static final RegistryKey<DamageType> GOD_BLESS = create("god_bless");
    public static final RegistryKey<DamageType> WEB_THORNS = create("web_thorns");
    public static final RegistryKey<DamageType> BRAMBLES_THORNS = create("brambles_thorns");
    public static final RegistryKey<DamageType> ROOT_THORNS = create("root_thorns");
    public static final RegistryKey<DamageType> SHURIKEN_HIT = create("shuriken_hit");
    public static final RegistryKey<DamageType> LUNATIC_PROJECTION = create("lunatic_projection");
    public static final RegistryKey<DamageType> CURSED_TOOL = create("cursed_tool");
    public static final RegistryKey<DamageType> GOLEM_BEAM = create("golem_beam");

    private static RegistryKey<DamageType> create(String name) {return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, AerialHell.id(name));}

    public static DamageSource getDamageSource(World level, RegistryKey<DamageType> typeKey)
    {
        return getDamageSource(level, typeKey, null, null);
    }

    public static DamageSource getDamageSource(World world, RegistryKey<DamageType> typeKey, @Nullable Entity immediateSource, @Nullable Entity trueSource)
    {
        return new DamageSource(world.getRegistryManager().getOrThrow(RegistryKeys.DAMAGE_TYPE).getOrThrow(typeKey), immediateSource, trueSource);
    }
}
