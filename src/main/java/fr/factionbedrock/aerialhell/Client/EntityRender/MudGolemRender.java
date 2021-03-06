package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellGolemModel;
import fr.factionbedrock.aerialhell.Entity.Monster.MudGolemEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MudGolemRender extends MobRenderer<MudGolemEntity, AerialHellGolemModel<MudGolemEntity>>
{
	private static String name = "mud_golem";
    private static final ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public MudGolemRender(EntityRendererManager manager)
    {
        super(manager, new AerialHellGolemModel(), 0.6f);
    }

    @Override
    public ResourceLocation getEntityTexture(MudGolemEntity entity) {return TEXTURE;}
}