package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Enchantments.SolidEtherWalker;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellEnchantments
{
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create( ForgeRegistries.ENCHANTMENTS, AerialHell.MODID);

    private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    public static final RegistryObject<Enchantment> SOLID_ETHER_WALKER = ENCHANTMENTS.register("solid_ether_walker", () -> new SolidEtherWalker(Enchantment.Rarity.RARE, ARMOR_SLOTS));
}
