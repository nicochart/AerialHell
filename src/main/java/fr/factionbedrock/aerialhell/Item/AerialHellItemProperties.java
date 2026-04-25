package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Item.Tools.AerialHellToolMaterial;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class AerialHellItemProperties extends Item.Properties
{
    public AerialHellItemProperties() {super();}

    public AerialHellItemProperties tool(AerialHellToolMaterial material, TagKey<Block> minesEfficiently, float attackDamage, float attackSpeed,float movementSpeed, float maxHealth, float disableBlockingSeconds)
    {
        return material.applyToolProperties(this, minesEfficiently, attackDamage, attackSpeed, movementSpeed, maxHealth, disableBlockingSeconds);
    }

    public AerialHellItemProperties pickaxe(AerialHellToolMaterial material, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth)
    {
        return this.tool(material, BlockTags.MINEABLE_WITH_PICKAXE, attackDamage, attackSpeed, movementSpeed, maxHealth, 0.0F);
    }

    public AerialHellItemProperties axe(AerialHellToolMaterial material, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth)
    {
        return this.tool(material, BlockTags.MINEABLE_WITH_AXE, attackDamage, attackSpeed, movementSpeed, maxHealth, 5.0F);
    }

    public AerialHellItemProperties hoe(AerialHellToolMaterial material, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth)
    {
        return this.tool(material, BlockTags.MINEABLE_WITH_HOE, attackDamage, attackSpeed, movementSpeed, maxHealth, 0.0F);
    }

    public AerialHellItemProperties shovel(AerialHellToolMaterial material, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth)
    {
        return this.tool(material, BlockTags.MINEABLE_WITH_SHOVEL, attackDamage, attackSpeed, movementSpeed, maxHealth, 0.0F);
    }

    public AerialHellItemProperties sword(AerialHellToolMaterial material, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth)
    {
        return material.applySwordProperties(this, attackDamage, attackSpeed, movementSpeed, maxHealth);
    }

    @Override public AerialHellItemProperties setId(ResourceKey<Item> id) {return (AerialHellItemProperties) super.setId(id);}
}
