package fr.factionbedrock.aerialhell.Item.Material;

import com.google.common.collect.ImmutableMultimap;
import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public abstract class ExtraAttributeModifiersMaterial
{
    private final AttributeEntryList extraAttributes;

    public ExtraAttributeModifiersMaterial() {this.extraAttributes = new AttributeEntryList();}

    public ExtraAttributeModifiersMaterial addAttributeModifier(Attribute attribute, float value, AttributeModifier.Operation operation)
    {
        this.extraAttributes.add(attribute, value, operation);
        return this;
    }

    public ImmutableMultimap<Attribute, AttributeModifier> applyExtraAttributes(ImmutableMultimap.Builder<Attribute, AttributeModifier> modifiersBuilder, AttributeEntryList additionalAttributes, String nameSuffix)
    {
        ExtraAttributeModifiers attributesToApply = new ExtraAttributeModifiers();
        attributesToApply.addAttributeModifiers(this.extraAttributes);
        attributesToApply.addAttributeModifiers(additionalAttributes);

        UUID modifierId = this.getModifierId(nameSuffix);
        return attributesToApply.applyAll(modifiersBuilder, modifierId);
    }

    public UUID getModifierId(String nameSuffix)
    {
        String key = AerialHell.MODID + ".item." + nameSuffix;
        return UUID.nameUUIDFromBytes(key.getBytes(StandardCharsets.UTF_8));
    }
}
