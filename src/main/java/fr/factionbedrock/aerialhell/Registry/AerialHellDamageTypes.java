package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;

public class AerialHellDamageTypes
{
    public static final ResourceKey<DamageType> GOD_BLESS = create("god_bless");
    public static final ResourceKey<DamageType> WEB_THORNS = create("web_thorns");
    public static final ResourceKey<DamageType> BRAMBLES_THORNS = create("brambles_thorns");
    public static final ResourceKey<DamageType> ROOT_THORNS = create("root_thorns");
    public static final ResourceKey<DamageType> SHURIKEN_HIT = create("shuriken_hit");
    public static final ResourceKey<DamageType> LUNATIC_PROJECTION = create("lunatic_projection");
    public static final ResourceKey<DamageType> CURSED_TOOL = create("cursed_tool");

    private static ResourceKey<DamageType> create(String name) {return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, name));}

    public static DamageSource getDamageSource(World level, ResourceKey<DamageType> typeKey)
    {
        return getDamageSource(level, typeKey, null, null);
    }

    public static DamageSource getDamageSource(World world, ResourceKey<DamageType> typeKey, @Nullable Entity immediateSource, @Nullable Entity trueSource)
    {
        return new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(typeKey), immediateSource, trueSource);
    }
}
