package fr.factionbedrock.aerialhell.Client.Util;

import net.minecraft.resources.Identifier;

public class TextureInfo
{
    private final Identifier texture;
    private final float u;
    private final float v;
    private final int width;
    private final int height;
    private final int textureWidth;
    private final int textureHeight;

    public TextureInfo(Identifier texture, int textureWidth, int textureHeight) {this(texture, 0.0F, 0.0F, textureWidth, textureHeight, textureWidth, textureHeight);}

    public TextureInfo(Identifier texture, float u, float v, int width, int height, int textureWidth, int textureHeight)
    {
        this.texture = texture;
        this.u = u;
        this.v = v;
        this.width = width;
        this.height = height;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    public Identifier texture() {return texture;}

    public float u() {return u;}

    public float v() {return v;}

    public int width() {return width;}

    public int height() {return height;}

    public int textureWidth() {return textureWidth;}

    public int textureHeight() {return textureHeight;}
}
