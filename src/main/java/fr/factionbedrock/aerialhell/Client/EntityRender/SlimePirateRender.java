
package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.SlimePirateModel;
import fr.factionbedrock.aerialhell.Entity.Monster.SlimePirateEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class SlimePirateRender extends MobRenderer<SlimePirateEntity, SlimePirateModel<SlimePirateEntity>>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/pirate/slime.png");

    public SlimePirateRender(EntityRendererProvider.Context context)
    {
        super(context, new SlimePirateModel(context.bakeLayer(AerialHellModelLayers.SLIME_PIRATE)), 0.3f);
    }

    @Nullable @Override protected RenderType getRenderType(SlimePirateEntity entity, boolean b1, boolean b2, boolean b3)
    {
        return RenderType.entityTranslucent(this.getTextureLocation(entity));
    }

    @Override public ResourceLocation getTextureLocation(SlimePirateEntity entity) {return TEXTURE;}
}