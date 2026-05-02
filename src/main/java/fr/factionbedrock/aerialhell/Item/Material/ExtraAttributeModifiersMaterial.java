package fr.factionbedrock.aerialhell.Item.Material;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public abstract class ExtraAttributeModifiersMaterial
{
    private final AttributeEntryList extraAttributes;

    public ExtraAttributeModifiersMaterial() {this.extraAttributes = new AttributeEntryList();}

    public ExtraAttributeModifiersMaterial addAttributeModifier(Holder<Attribute> attribute, float value, AttributeModifier.Operation operation)
    {
        this.extraAttributes.add(attribute, value, operation);
        return this;
    }

    public ItemAttributeModifiers applyExtraAttributes(ItemAttributeModifiers.Builder modifiersBuilder, AttributeEntryList additionalAttributes, EquipmentSlotGroup slotGroup, String nameSuffix)
    {
        ExtraAttributeModifiers attributesToApply = new ExtraAttributeModifiers();
        attributesToApply.addAttributeModifiers(this.extraAttributes);
        attributesToApply.addAttributeModifiers(additionalAttributes);

        Identifier modifierId = this.getModifierId(nameSuffix);
        return attributesToApply.applyAll(modifiersBuilder, modifierId, slotGroup);
    }

    public Identifier getModifierId(String nameSuffix) {return Identifier.fromNamespaceAndPath(AerialHell.MODID, "item."+nameSuffix);}
}
