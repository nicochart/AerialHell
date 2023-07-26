package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;

public class AerialHellArmorItem extends ArmorItem
{
    public AerialHellArmorItem(ArmorMaterial materialIn, ArmorItem.Type armorType, Properties builderIn) {super(materialIn, armorType, builderIn);}

    @Override public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected)
    {
        if (entity instanceof Player)
        {
            Player playerEntity = (Player) entity;

            if (ItemHelper.getItemInTagCount(playerEntity.getArmorSlots(), AerialHellTags.Items.SHADOW_ARMOR) >= 4)
            {
                playerEntity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false));
                playerEntity.addEffect(new MobEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY.get(), 100, 0, false, false));
            }
        }
    }
}