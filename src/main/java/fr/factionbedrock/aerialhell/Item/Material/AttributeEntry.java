package fr.factionbedrock.aerialhell.Item.Material;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;

public record AttributeEntry(Attribute attribute, float value, AttributeModifier.Operation operation)
{
    public static AttributeEntry movementSpeed(float movementSpeed)
    {
        return new AttributeEntry(Attributes.MOVEMENT_SPEED, movementSpeed, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    public static AttributeEntry maxHealth(float maxHealth)
    {
        return new AttributeEntry(Attributes.MAX_HEALTH, maxHealth, AttributeModifier.Operation.ADDITION);
    }

    public static AttributeEntry entityInteractionRange(float value)
    {
        return new AttributeEntry(ForgeMod.ENTITY_REACH.get(), value, AttributeModifier.Operation.ADDITION);
    }
}
