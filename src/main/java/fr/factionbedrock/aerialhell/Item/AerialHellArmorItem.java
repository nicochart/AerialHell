package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.core.Holder;
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
    public AerialHellArmorItem(Holder<ArmorMaterial> materialIn, Type armorType, Properties builderIn) {super(materialIn, armorType, builderIn);}

    @Override public void inventoryTick(ItemStack stack, Level level, Entity entity, int itemSlot, boolean isSelected)
    {
        if (entity instanceof Player)
        {
            Player playerEntity = (Player) entity;

            if (ItemHelper.getItemInTagCount(playerEntity.getArmorSlots(), AerialHellTags.Items.SHADOW_ARMOR) >= 4 && !level.isClientSide())
            {
                playerEntity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false));

                int shadowBindAmplifier = playerEntity.hasEffect(AerialHellMobEffects.SHADOW_BIND.getDelegate()) ? 0 : 1;
                playerEntity.addEffect(new MobEffectInstance(AerialHellMobEffects.SHADOW_BIND.getDelegate(), 200, shadowBindAmplifier, false, false));
            }
        }
    }
}