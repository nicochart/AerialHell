package fr.factionbedrock.aerialhell.Item.Material;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ExtraAttributeModifiers
{
    private final Map<Attribute, EnumMap<AttributeModifier.Operation, Float>> extraAttributes = new HashMap<>();

    public ExtraAttributeModifiers() {}

    public ExtraAttributeModifiers addAttributeModifiers(AttributeEntryList attributeEntries)
    {
        for (AttributeEntry entry : attributeEntries.get()) {this.addAttributeModifier(entry.attribute(), entry.value(), entry.operation());}
        return this;
    }

    private void addAttributeModifier(Attribute attribute, float value, AttributeModifier.Operation operation)
    {
        this.extraAttributes
            .computeIfAbsent(attribute, attr ->
            {
                EnumMap<AttributeModifier.Operation, Float> map = new EnumMap<>(AttributeModifier.Operation.class);
                for (AttributeModifier.Operation op : AttributeModifier.Operation.values()) {map.put(op, 0.0F);}
                return map;
            })
            .merge(operation, value, Float::sum);
    }

    public ImmutableMultimap<Attribute, AttributeModifier> applyAll(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder, UUID modifierId)
    {
        for (var attributeEntry : this.extraAttributes.entrySet())
        {
            Attribute attribute = attributeEntry.getKey();
            EnumMap<AttributeModifier.Operation, Float> operations = attributeEntry.getValue();

            for (var operationEntry : operations.entrySet())
            {
                AttributeModifier.Operation operation = operationEntry.getKey();
                float value = operationEntry.getValue();

                if (value != 0.0F)
                {
                    builder.put(attribute, new AttributeModifier(modifierId, "Tool modifier", value, operation));
                }
            }
        }

        return builder.build();
    }
}
