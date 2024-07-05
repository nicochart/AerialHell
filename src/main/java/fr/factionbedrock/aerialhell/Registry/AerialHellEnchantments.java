package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

public class AerialHellEnchantments
{
    public static final ResourceKey<Enchantment> SOLID_ETHER_WALKER = key("solid_ether_walker");

    private static ResourceKey<Enchantment> key(String name)
    {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, name));
    }
}
