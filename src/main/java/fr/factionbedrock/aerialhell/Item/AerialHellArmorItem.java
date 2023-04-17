package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class AerialHellArmorItem extends ArmorItem
{
    public AerialHellArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {super(materialIn, slot, builderIn);}

    @Override public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
    {
        if (entity instanceof PlayerEntity)
        {
            PlayerEntity playerEntity = (PlayerEntity) entity;

            if (ItemHelper.getItemInTagCount(playerEntity.getArmorInventoryList(), AerialHellTags.Items.SHADOW_ARMOR) >= 4)
            {
                playerEntity.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 220, 0, false, false));
                playerEntity.addPotionEffect(new EffectInstance(AerialHellPotionEffects.SHADOW_IMMUNITY.get(), 100, 0, false, false));
            }
        }
    }
}