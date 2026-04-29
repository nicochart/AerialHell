package fr.factionbedrock.aerialhell.Item.Material;

import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ExtraAttributeModifiers
{
    private final Map<Holder<Attribute>, EnumMap<AttributeModifier.Operation, Float>> extraAttributes = new HashMap<>();

    public ExtraAttributeModifiers() {}

    public ExtraAttributeModifiers addAttributeModifiers(AttributeEntryList attributeEntries)
    {
        for (AttributeEntry entry : attributeEntries.get()) {this.addAttributeModifier(entry.attribute(), entry.value(), entry.operation());}
        return this;
    }

    private void addAttributeModifier(Holder<Attribute> attribute, float value, AttributeModifier.Operation operation)
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

    public ItemAttributeModifiers applyAll(ItemAttributeModifiers.Builder builder, Identifier modifierId, EquipmentSlotGroup slotGroup)
    {
        for (var attributeEntry : this.extraAttributes.entrySet())
        {
            Holder<Attribute> attribute = attributeEntry.getKey();
            EnumMap<AttributeModifier.Operation, Float> operations = attributeEntry.getValue();

            for (var operationEntry : operations.entrySet())
            {
                AttributeModifier.Operation operation = operationEntry.getKey();
                float value = operationEntry.getValue();

                if (value != 0.0F)
                {
                    builder.add(attribute, new AttributeModifier(modifierId, value, operation), slotGroup);
                }
            }
        }

        return builder.build();
    }
}
