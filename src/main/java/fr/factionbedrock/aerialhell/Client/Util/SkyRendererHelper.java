package fr.factionbedrock.aerialhell.Client.Util;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class SkyRendererHelper
{
    private static final float SKY_DISC_RADIUS = 512.0F;

    public static void buildSkyDisc(VertexConsumer buffer, float y)
    {
        float f = Math.signum(y) * SKY_DISC_RADIUS;
        buffer.addVertex(0.0F, y, 0.0F);

        for (int i = -180; i <= 180; i += 45)
        {
            buffer.addVertex(f * Mth.cos((float)i * (float) (Math.PI / 180.0)), y, SKY_DISC_RADIUS * Mth.sin((float)i * (float) (Math.PI / 180.0)));
        }
    }

    public static void buildStars(VertexConsumer buffer)
    {
        RandomSource rand = RandomSource.create(10842L);

        //vecx & vecz are angular coordinates, vecy is distance - lens effect (0.0 looks like the cluster is closer, bigger. 1.0 or -1.0 is far away, smaller)
        //vecx is horizontal angle and vecz vertical angle. vecx = 0 -> vertically aligned with the moon
        buildStarCluster(buffer, 500, new Vector3f(0.3F, -0.7F, 0.25F), new Vector3f(0.5F, 0.5F, 0.8F), 0.1F, 0.6F, rand);
        buildStarCluster(buffer, 600, new Vector3f(-0.3F, -0.6F, -0.7F), new Vector3f(0.5F, 0.5F, 0.6F), 0.2F, 0.5F, rand);
        buildStarCluster(buffer, 400, new Vector3f(0.2F, 0.2F, -0.7F), new Vector3f(0.5F, 0.3F, 0.5F), 0.1F, 0.4F, rand);
        buildStarCluster(buffer, 600, new Vector3f(0.65F, 0.7F, 0.45F), new Vector3f(0.7F, 0.6F, 0.7F), 0.3F, 0.6F, rand);
        buildStarCluster(buffer, 700, new Vector3f(-0.8F, 0.1F, -0.5F), new Vector3f(0.8F, 0.7F, 0.7F), 0.1F, 0.6F, rand);
        buildStarCluster(buffer, 500, new Vector3f(0.7F, 0.75F, -0.4F), new Vector3f(0.6F, 0.5F, 0.6F), 0.15F, 0.4F, rand);
        buildScatteredStars(buffer, 2000, 0.01F, 0.4F, rand);
    }

    private static void buildScatteredStars(VertexConsumer buffer, int starNumber, float bigChance, float bigSizeBonus, RandomSource rand)
    {
        for (int i = 0; i < starNumber; i++)
        {
            Vector3f starVec = createRandomStar(rand);
            float starSize = 0.15F + rand.nextFloat() * 0.1F;
            if (rand.nextFloat() < bigChance) {starSize += rand.nextFloat() * bigSizeBonus;}

            float lengthSquared = Mth.lengthSquared(starVec.x, starVec.y, starVec.z);
            if (lengthSquared > 0.010000001F && lengthSquared < 1.0F)
            {
                buildStar(buffer, starVec.normalize(100.0F), starSize);
            }
        }
    }

    private static void buildStarCluster(VertexConsumer buffer, int starNumber, Vector3f origin, Vector3f size, float bigChance, float bigSizeBonus, RandomSource rand)
    {
        for (int i = 0; i < starNumber; i++)
        {
            Vector3f starVec = createRandomStar(origin, size, rand);
            float starSize = 0.15F + rand.nextFloat() * 0.1F;
            if (rand.nextFloat() < bigChance) {starSize += rand.nextFloat() * bigSizeBonus;}

            float lengthSquared = Mth.lengthSquared(starVec.x, starVec.y, starVec.z);
            if (lengthSquared > 0.010000001F && lengthSquared < 1.0F)
            {
                if (isStarInsideCluster(origin, starVec, new Vector3f(size).mul(0.70F)))
                {
                    buildStar(buffer, starVec.normalize(100.0F), starSize);
                }
                else if (isStarInsideCluster(origin, starVec, size))
                {
                    if (rand.nextFloat() < 0.6F) {
                        buildStar(buffer, starVec.normalize(100.0F), starSize);}
                }
                else if (isStarInsideCluster(origin, starVec, new Vector3f(size).mul(1.15F)))
                {
                    if (rand.nextFloat() < 0.2F) {
                        buildStar(buffer, starVec.normalize(100.0F), starSize);}
                }
            }
        }
    }

    private static void buildStar(VertexConsumer buffer, Vector3f normalizedStarVec, float starSize)
    {
        Quaternionf quaternionf = new Quaternionf().rotateTo(new Vector3f(0.0F, 0.0F, -1.0F), normalizedStarVec);
        buffer.addVertex(normalizedStarVec.add(new Vector3f(starSize, -starSize, 0.0F).rotate(quaternionf)));
        buffer.addVertex(normalizedStarVec.add(new Vector3f(starSize, starSize, 0.0F).rotate(quaternionf)));
        buffer.addVertex(normalizedStarVec.add(new Vector3f(-starSize, starSize, 0.0F).rotate(quaternionf)));
        buffer.addVertex(normalizedStarVec.add(new Vector3f(-starSize, -starSize, 0.0F).rotate(quaternionf)));
    }

    private static Vector3f createRandomStar(Vector3f origin, Vector3f size, RandomSource rand)
    {
        return new Vector3f(origin.x + size.x * (rand.nextFloat() - 0.5F), origin.y + size.y * (rand.nextFloat() - 0.5F), origin.z + size.z * (rand.nextFloat() - 0.5F));
    }

    private static Vector3f createRandomStar(RandomSource rand)
    {
        return new Vector3f(rand.nextFloat() * 2.0F - 1.0F, rand.nextFloat() * 2.0F - 1.0F, rand.nextFloat() * 2.0F - 1.0F);
    }

    protected static boolean isStarInsideCluster(Vector3f clusterCenter, Vector3f star, Vector3f clusterSize)
    {
        float x = star.x - clusterCenter.x, y = star.y - clusterCenter.y, z = star.z - clusterCenter.z;
        float sizex = clusterSize.x/2, sizey = clusterSize.y/2, sizez = clusterSize.z/2;
        return x*x/(sizex*sizex) + y*y/(sizey*sizey) + z*z/(sizez*sizez) < 1.0F;
    }
}
