package fr.factionbedrock.aerialhell.Entity.Util;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.function.BiConsumer;

//volucite glyph particles (see VoluciteGlyphParticle) : each particle moves from its spawn pos to its destination, through blocks and entities
//spawn methods : client side. send methods : server side, particles sent to clients
public class VoluciteGlyphParticles
{
    public static void spawn(Level level, Vec3 from, Vec3 to)
    {
        level.addParticle(AerialHellParticleTypes.VOLUCITE_GLYPH.get(), from.x, from.y, from.z, to.x - from.x, to.y - from.y, to.z - from.z); //speed parameters = offset to destination
    }

    public static void send(ServerLevel level, Vec3 from, Vec3 to)
    {
        level.sendParticles(AerialHellParticleTypes.VOLUCITE_GLYPH.get(), from.x, from.y, from.z, 0, to.x - from.x, to.y - from.y, to.z - from.z, 1.0D); //count 0 : speed parameters received as they are
    }

    //from random positions around center (between minRadius and maxRadius, any direction), toward center
    public static void spawnInward(Level level, Vec3 center, float minRadius, float maxRadius, int count) {forEachInward(level.getRandom(), center, minRadius, maxRadius, count, (from, to) -> spawn(level, from, to));}
    public static void sendInward(ServerLevel level, Vec3 center, float minRadius, float maxRadius, int count) {forEachInward(level.getRandom(), center, minRadius, maxRadius, count, (from, to) -> send(level, from, to));}

    //from center, toward random positions around it (between minDistance and maxDistance, any direction)
    public static void spawnOutward(Level level, Vec3 center, float minDistance, float maxDistance, int count) {forEachOutward(level.getRandom(), center, minDistance, maxDistance, count, (from, to) -> spawn(level, from, to));}
    public static void sendOutward(ServerLevel level, Vec3 center, float minDistance, float maxDistance, int count) {forEachOutward(level.getRandom(), center, minDistance, maxDistance, count, (from, to) -> send(level, from, to));}

    //from pos, toward random positions below it (up to horizontalSpread on x and z, between minDepth and maxDepth lower)
    public static void sendDownward(ServerLevel level, Vec3 from, float horizontalSpread, float minDepth, float maxDepth, int count)
    {
        RandomSource random = level.getRandom();
        for (int i = 0; i < count; i++) {send(level, from, from.add(Mth.nextFloat(random, -horizontalSpread, horizontalSpread), -Mth.nextFloat(random, minDepth, maxDepth), Mth.nextFloat(random, -horizontalSpread, horizontalSpread)));}
    }

    private static void forEachInward(RandomSource random, Vec3 center, float minRadius, float maxRadius, int count, BiConsumer<Vec3, Vec3> spawner)
    {
        for (int i = 0; i < count; i++) {spawner.accept(center.add(randomDirection(random).scale(Mth.nextFloat(random, minRadius, maxRadius))), center);}
    }

    private static void forEachOutward(RandomSource random, Vec3 center, float minDistance, float maxDistance, int count, BiConsumer<Vec3, Vec3> spawner)
    {
        for (int i = 0; i < count; i++) {spawner.accept(center, center.add(randomDirection(random).scale(Mth.nextFloat(random, minDistance, maxDistance))));}
    }

    private static Vec3 randomDirection(RandomSource random) {return new Vec3(random.nextGaussian(), random.nextGaussian(), random.nextGaussian()).normalize();} //uniform on a sphere
}
