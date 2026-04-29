package fr.factionbedrock.aerialhell.Item.Material;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public record AttributeEntry(Holder<Attribute> attribute, float value, AttributeModifier.Operation operation) {}
