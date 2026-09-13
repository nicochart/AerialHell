package fr.factionbedrock.aerialhell.Item.Material;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AttributeEntryList
{
    private final List<AttributeEntry> attributes;

    public AttributeEntryList() {this.attributes = new ArrayList<>();}

    public AttributeEntryList add(Attribute attribute, float value, AttributeModifier.Operation operation) {return this.add(new AttributeEntry(attribute, value, operation));}
    public AttributeEntryList add(AttributeEntry entry) {this.attributes.add(entry);return this;}

    public List<AttributeEntry> get() {return this.attributes;}

    public ImmutableMultimap<Attribute, AttributeModifier> addAllAttributesToBuilder(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder, UUID modifierId)
    {
        for (AttributeEntry entry : this.attributes)
        {
            builder.put(entry.attribute(), new AttributeModifier(modifierId, "Tool modifier", entry.value(), entry.operation()));
        }
        return builder.build();
    }
}
