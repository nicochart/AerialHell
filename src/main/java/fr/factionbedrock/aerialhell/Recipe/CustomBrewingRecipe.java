package fr.factionbedrock.aerialhell.Recipe;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
//import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;

/*
 * I followed this tutorial : https://www.youtube.com/watch?v=ILDolvc_5tU
 * CustomBrewingRecipe code from CAS-ual-TY, Extra-Potions mod owner : https://github.com/CAS-ual-TY/Extra-Potions
 */
/*
 * MIT License

 * Copyright (c) 2022 Luis Thiele

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class CustomBrewingRecipe implements IBrewingRecipe
{
    private final Potion input;
    private final Item ingredient;
    private final Potion output;

    public CustomBrewingRecipe(Potion input, Item ingredient, Potion output)
    {
        this.input = input;
        this.ingredient = ingredient;
        this.output = output;
    }

    @Override public boolean isInput(ItemStack input) {return false;}//{return PotionUtils.getPotion(input) == this.input;} TODO

    @Override public boolean isIngredient(ItemStack ingredient) {return ingredient.getItem() == this.ingredient;}

    @Override public ItemStack getOutput(ItemStack input, ItemStack ingredient)
    {
        if(!this.isInput(input) || !this.isIngredient(ingredient)) {return ItemStack.EMPTY;}

        ItemStack itemStack = new ItemStack(input.getItem());
        //itemStack.setTag(new CompoundTag()); TODO
        //PotionUtils.setPotion(itemStack, this.output);
        return itemStack;
    }
}
