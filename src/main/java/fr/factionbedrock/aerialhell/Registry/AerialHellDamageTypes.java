package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class AerialHellDamageTypes
{
    public static final ResourceKey<DamageType> GOD_BLESS = create("god_bless");
    public static final ResourceKey<DamageType> WEB_THORNS = create("web_thorns");
    public static final ResourceKey<DamageType> BRAMBLES_THORNS = create("brambles_thorns");
    public static final ResourceKey<DamageType> ROOT_THORNS = create("root_thorns");
    public static final ResourceKey<DamageType> SHURIKEN_HIT = create("shuriken_hit");
    public static final ResourceKey<DamageType> LUNATIC_PROJECTION = create("lunatic_projection");
    public static final ResourceKey<DamageType> CURSED_TOOL = create("cursed_tool");

    private static ResourceKey<DamageType> create(String name) {return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(AerialHell.MODID, name));}

    public static DamageSource getDamageSource(Level level, ResourceKey<DamageType> typeKey)
    {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(typeKey));
    }
}
