package fr.factionbedrock.aerialhell.Mixin;

import net.minecraft.client.render.block.entity.state.ChestBlockEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ChestBlockEntityRenderState.Variant.class)
public interface ChestVariantMixin
{
    //enums have two invisible parameters (name and index in the enum) to add in the invoker
    @Invoker("<init>")
    static ChestBlockEntityRenderState.Variant invokeConstructor(String enumName, int enumIndex)
    {
        throw new AssertionError();
    }
}
