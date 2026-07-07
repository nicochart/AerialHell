package fr.factionbedrock.aerialhell.Item.Material;

import com.google.common.base.Suppliers;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class VanillaToolMaterial implements Tier
{
    private final TagKey<Block> incorrectBlocksForDrops;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    public VanillaToolMaterial(final TagKey<Block> incorrectBlocksForDrops, final int uses, final float speed, final float damage, final int enchantmentValue, final Supplier<Ingredient> repairIngredients)
    {
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = Suppliers.memoize(repairIngredients::get);
    }

    @Override public int getUses() {return this.uses;}
    @Override public float getSpeed() {return this.speed;}
    @Override public float getAttackDamageBonus() {return this.damage;}
    @Override public TagKey<Block> getIncorrectBlocksForDrops() {return this.incorrectBlocksForDrops;}
    @Override public int getEnchantmentValue() {return this.enchantmentValue;}
    @Override public Ingredient getRepairIngredient() {return this.repairIngredient.get();}
}
