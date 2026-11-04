package fr.factionbedrock.aerialhell.Client.Particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

//volucite glyph : moves in a straight line from its spawn pos to its destination, through blocks and entities, then disappears
//spawn speed parameters are the offset from spawn pos to destination (see VoluciteGlyphParticles). speed is random. random glyph texture (dungeon glyph blocks)
public class VoluciteGlyphParticle extends SingleQuadParticle
{
    private static final float MIN_SPEED = 0.2F, MAX_SPEED = 0.7F; //blocks per tick

    protected VoluciteGlyphParticle(ClientLevel level, double x, double y, double z, double toDestinationX, double toDestinationY, double toDestinationZ, TextureAtlasSprite sprite)
    {
        super(level, x, y, z, sprite);
        double distance = Math.sqrt(toDestinationX * toDestinationX + toDestinationY * toDestinationY + toDestinationZ * toDestinationZ);
        this.lifetime = Math.max(1, Mth.ceil(distance / Mth.nextFloat(this.random, MIN_SPEED, MAX_SPEED)));
        this.xd = toDestinationX / this.lifetime; //destination reached at the end of the lifetime
        this.yd = toDestinationY / this.lifetime;
        this.zd = toDestinationZ / this.lifetime;
        this.hasPhysics = false;
        this.gravity = 0.0F;
        this.friction = 1.0F;
        this.quadSize = 0.125F;
        this.rCol = 0.2F; this.gCol = 0.45F; this.bCol = 0.75F; //dark volucite blue (volucite warden color)
    }

    @Override protected Layer getLayer() {return Layer.TRANSLUCENT;}

    @Override public int getLightCoords(float partialTick) {return 15728880;} //full bright

    public static class Provider implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {this.sprites = sprites;}

        @Override public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random)
        {
            return new VoluciteGlyphParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites.get(random));
        }
    }
}
