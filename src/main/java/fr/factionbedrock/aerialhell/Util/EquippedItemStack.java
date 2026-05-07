package fr.factionbedrock.aerialhell.Util;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public record EquippedItemStack(EquipmentSlot slot, ItemStack stack) {}