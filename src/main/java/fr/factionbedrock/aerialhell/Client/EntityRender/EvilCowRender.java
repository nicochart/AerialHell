package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Monster.EvilCowEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

public class EvilCowRender extends MobEntityRenderer<EvilCowEntity, LivingEntityRenderState, CowEntityModel>
{
    private static final Identifier EVIL_COW_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/evil_cow/evil_cow.png");
    
    public EvilCowRender(EntityRendererFactory.Context context)
    {
        super(context, new CowEntityModel(context.getPart(EntityModelLayers.COW)), 0.7F);
    }

    @Override public LivingEntityRenderState createRenderState() {return new LivingEntityRenderState();}

    @Override public Identifier getTexture(LivingEntityRenderState renderState) {return EVIL_COW_TEXTURE;}
}
