package fr.factionbedrock.aerialhell.Mixin;

import net.minecraft.client.renderer.blockentity.state.ChestRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ChestRenderState.ChestMaterialType.class)
public interface ChestVariantMixin
{
    //enums have two invisible parameters (name and index in the enum) to add in the invoker
    @Invoker("<init>")
    static ChestRenderState.ChestMaterialType invokeConstructor(String enumName, int enumIndex)
    {
        throw new AssertionError();
    }
}
