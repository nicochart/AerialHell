package fr.factionbedrock.aerialhell.Entity;

import com.google.common.collect.Lists;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellPaintingType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.PaintingEntity;
import net.minecraft.entity.item.PaintingType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class AerialHellPaintingEntity extends PaintingEntity
{
    public AerialHellPaintingEntity(EntityType<? extends AerialHellPaintingEntity> type, World worldIn) {super(type, worldIn);}

    public AerialHellPaintingEntity(World worldIn, BlockPos pos, Direction facing)
    {
        super(worldIn, pos, facing);
        List<PaintingType> list = Lists.newArrayList();
        list.add(AerialHellPaintingType.HOSTILE_PARADISE.get());
        list.add(AerialHellPaintingType.SPOOKY_ISLANDS.get());
        list.add(AerialHellPaintingType.BROWN_SHROOM_ISLAND.get());
        list.add(AerialHellPaintingType.LIVING_ISLAND.get());
        list.add(AerialHellPaintingType.FLOADING_ISLANDS_LANDSCAPE.get());
        list.add(AerialHellPaintingType.CUTE_SHROOMS.get());
        list.add(AerialHellPaintingType.MAGICAL_ISLAND.get());
        list.add(AerialHellPaintingType.FOGGY_PEAKS.get());
        list.add(AerialHellPaintingType.SHROOM_AND_VEGETATION_ISLANDS.get());
        list.add(AerialHellPaintingType.SHROOM_ISLAND_PIXELART.get());
        list.add(AerialHellPaintingType.MAGICAL_SHROOM_ISLAND.get());
        list.add(AerialHellPaintingType.MYSTERY_ISLANDS.get());
        list.add(AerialHellPaintingType.CYAN_SHROOM_ISLAND.get());

        int largestPaintingSurface = 0;

        Iterator<PaintingType> invalidPaintingRemoverIterator = list.iterator();
        while(invalidPaintingRemoverIterator.hasNext())
        {
            PaintingType paintingType = invalidPaintingRemoverIterator.next();

            if (isPaintingValid(paintingType, facing))
            {
                int newPaintingSurface = getPaintingSurface(paintingType);
                if (newPaintingSurface > largestPaintingSurface) {largestPaintingSurface = newPaintingSurface;}
            }
            else {invalidPaintingRemoverIterator.remove();}
        }

        if (!list.isEmpty()) /*if there are valid painting types*/
        {
            Iterator<PaintingType> tooSmallPaintingsRemoverIterator = list.iterator();
            while(tooSmallPaintingsRemoverIterator.hasNext())
            {
                PaintingType valid_painting_type = tooSmallPaintingsRemoverIterator.next();
                if (isTooSmall(valid_painting_type, largestPaintingSurface)) {tooSmallPaintingsRemoverIterator.remove();}
            }
            this.art = list.get(this.rand.nextInt(list.size()));
        }
        this.updateFacingWithBoundingBox(facing);
    }

    public boolean isTooSmall(PaintingType painting, int paintingSurface) {return getPaintingSurface(painting) < paintingSurface;}
    public int getPaintingSurface(PaintingType painting) {return painting.getWidth() * painting.getHeight();}

    public boolean isPaintingValid(PaintingType painting, Direction facing)
    {
        this.art = painting;
        this.updateFacingWithBoundingBox(facing);
        if (!this.onValidSurface()) {return false;}
        else {return true;}
    }

    @Override
    public void onBroken(@Nullable Entity brokenEntity)
    {
        if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
            this.playSound(SoundEvents.ENTITY_PAINTING_BREAK, 1.0F, 1.0F);
            if (brokenEntity instanceof PlayerEntity)
            {
                PlayerEntity playerentity = (PlayerEntity)brokenEntity;
                if (playerentity.abilities.isCreativeMode) {return;}
            }
            this.entityDropItem(AerialHellBlocksAndItems.AERIAL_HELL_PAINTING.get());
        }
    }
}
