package fr.factionbedrock.aerialhell.Mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Rarity.class)
public interface RarityMixin
{
    //enums have two invisible parameters (name and index in the enum) to add in the invoker
    @Invoker("<init>")
    static Rarity invokeConstructor(String enumName, int enumIndex, int rarityIndex, String rarityName, ChatFormatting formatting)
    {
        throw new AssertionError();
    }
}
