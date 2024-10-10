package fr.factionbedrock.aerialhell.Client.BlockBakedModels;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class ShadowGrassBlockBakedModel implements BakedModel
{
    private final BakedModel shadowModel;

    public ShadowGrassBlockBakedModel(BakedModel shadowModel) {this.shadowModel = shadowModel;}

    @Override public List<BakedQuad> getQuads(BlockState state, Direction side, RandomSource rand)
    {
        if (EntityHelper.isCurrentPlayerInstanceShadowBind())
        {
            BlockState lightState = getLightDefaultState();
            BakedModel lightModel = Minecraft.getInstance().getBlockRenderer().getBlockModel(lightState);
            return lightModel.getQuads(lightState, side, rand);
        }
        else
        {
            return shadowModel.getQuads(state, side, rand);
        }
    }

    @Override public boolean useAmbientOcclusion() {return getModel(shadowModel).useAmbientOcclusion();}
    @Override public boolean isGui3d() {return getModel(shadowModel).isGui3d();}
    @Override public boolean usesBlockLight() {return getModel(shadowModel).usesBlockLight();}
    @Override public boolean isCustomRenderer() {return getModel(shadowModel).isCustomRenderer();}
    @Override public TextureAtlasSprite getParticleIcon() {return getModel(shadowModel).getParticleIcon();}
    @Override public ItemTransforms getTransforms() {return getModel(shadowModel).getTransforms();}
    @Override public ItemOverrides getOverrides() {return getModel(shadowModel).getOverrides();}

    private static BakedModel getModel(BakedModel defaultModel)
    {
        return EntityHelper.isCurrentPlayerInstanceShadowBind() ? getLightDefaultModel() : defaultModel;
    }

    private static BakedModel getLightDefaultModel() {return Minecraft.getInstance().getBlockRenderer().getBlockModel(getLightDefaultState());}
    private static BlockState getLightDefaultState() {return AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get().defaultBlockState();}
}