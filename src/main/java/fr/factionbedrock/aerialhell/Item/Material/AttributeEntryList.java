package fr.factionbedrock.aerialhell.Item.Material;

import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.ArrayList;
import java.util.List;

public class AttributeEntryList
{
    private final List<AttributeEntry> attributes;

    public AttributeEntryList() {this.attributes = new ArrayList<>();}

    public AttributeEntryList add(Holder<Attribute> attribute, float value, AttributeModifier.Operation operation) {return this.add(new AttributeEntry(attribute, value, operation));}
    public AttributeEntryList add(AttributeEntry entry) {this.attributes.add(entry);return this;}

    public List<AttributeEntry> get() {return this.attributes;}

    public ItemAttributeModifiers addAllAttributesToBuilder(ItemAttributeModifiers.Builder builder, Identifier modifierId, EquipmentSlotGroup slotGroup)
    {
        for (AttributeEntry entry : this.attributes)
        {
            builder.add(entry.attribute(), new AttributeModifier(modifierId, entry.value(), entry.operation()), slotGroup);
        }
        return builder.build();
    }
}
