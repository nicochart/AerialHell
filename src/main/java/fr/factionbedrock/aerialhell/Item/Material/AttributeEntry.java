package fr.factionbedrock.aerialhell.Item.Material;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public record AttributeEntry(Holder<Attribute> attribute, float value, AttributeModifier.Operation operation)
{
    public static AttributeEntry movementSpeed(float movementSpeed)
    {
        return new AttributeEntry(Attributes.MOVEMENT_SPEED, movementSpeed, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    public static AttributeEntry maxHealth(float maxHealth)
    {
        return new AttributeEntry(Attributes.MAX_HEALTH, maxHealth, AttributeModifier.Operation.ADD_VALUE);
    }

    public static AttributeEntry entityInteractionRange(float value)
    {
        return new AttributeEntry(Attributes.ENTITY_INTERACTION_RANGE, value, AttributeModifier.Operation.ADD_VALUE);
    }
}
