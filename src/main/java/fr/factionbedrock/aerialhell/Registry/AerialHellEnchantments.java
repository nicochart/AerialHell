package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class AerialHellEnchantments
{
    public static final RegistryKey<Enchantment> SOLID_ETHER_WALKER = key("solid_ether_walker");
    public static final RegistryKey<Enchantment> VULNERABILITY_ASPECT = key("vulnerability_aspect");

    private static RegistryKey<Enchantment> key(String name)
    {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, AerialHell.id(name));
    }
}
