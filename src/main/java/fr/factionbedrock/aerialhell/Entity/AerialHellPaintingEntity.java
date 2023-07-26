package fr.factionbedrock.aerialhell.Entity;

import com.google.common.collect.Lists;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellPaintingVariants;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class AerialHellPaintingEntity extends Painting
{
    public AerialHellPaintingEntity(EntityType<? extends AerialHellPaintingEntity> type, Level worldIn) {super(type, worldIn);}

    /*public AerialHellPaintingEntity(Level worldIn, BlockPos pos, Direction facing)
    {
        //super(worldIn, pos, facing);
        List<Holder<PaintingVariant>> list = Lists.newArrayList();
        list.add(AerialHellPaintingVariants.HOSTILE_PARADISE.getHolder().get());
        list.add(AerialHellPaintingVariants.SPOOKY_ISLANDS.getHolder().get());
        list.add(AerialHellPaintingVariants.BROWN_SHROOM_ISLAND.getHolder().get());
        list.add(AerialHellPaintingVariants.LIVING_ISLAND.getHolder().get());
        list.add(AerialHellPaintingVariants.FLOADING_ISLANDS_LANDSCAPE.getHolder().get());
        list.add(AerialHellPaintingVariants.CUTE_SHROOMS.getHolder().get());
        list.add(AerialHellPaintingVariants.MAGICAL_ISLAND.getHolder().get());
        list.add(AerialHellPaintingVariants.FOGGY_PEAKS.getHolder().get());
        list.add(AerialHellPaintingVariants.SHROOM_AND_VEGETATION_ISLANDS.getHolder().get());
        list.add(AerialHellPaintingVariants.SHROOM_ISLAND_PIXELART.getHolder().get());
        list.add(AerialHellPaintingVariants.MAGICAL_SHROOM_ISLAND.getHolder().get());
        list.add(AerialHellPaintingVariants.MYSTERY_ISLANDS.getHolder().get());
        list.add(AerialHellPaintingVariants.CYAN_SHROOM_ISLAND.getHolder().get());

        int largestPaintingSurface = 0;

        Iterator<Holder<PaintingVariant>> invalidPaintingRemoverIterator = list.iterator();
        while(invalidPaintingRemoverIterator.hasNext())
        {
            Holder<PaintingVariant> paintingVariant = invalidPaintingRemoverIterator.next();

            if (isPaintingValid(paintingVariant, facing))
            {
                int newPaintingSurface = getPaintingSurface(paintingVariant.value());
                if (newPaintingSurface > largestPaintingSurface) {largestPaintingSurface = newPaintingSurface;}
            }
            else {invalidPaintingRemoverIterator.remove();}
        }

        if (!list.isEmpty()) //if there are valid painting types*
        {
            Iterator<Holder<PaintingVariant>> tooSmallPaintingsRemoverIterator = list.iterator();
            while(tooSmallPaintingsRemoverIterator.hasNext())
            {
                PaintingVariant valid_painting_type = tooSmallPaintingsRemoverIterator.next().value();
                if (isTooSmall(valid_painting_type, largestPaintingSurface)) {tooSmallPaintingsRemoverIterator.remove();}
            }
            this.setVariant(list.get(this.random.nextInt(list.size())));
        }
        this.setDirection(facing);
    }*/

    public boolean isTooSmall(PaintingVariant painting, int paintingSurface) {return getPaintingSurface(painting) < paintingSurface;}
    public int getPaintingSurface(PaintingVariant painting) {return painting.getWidth() * painting.getHeight();}

    public boolean isPaintingValid(Holder<PaintingVariant> painting, Direction facing)
    {
        this.setVariant(painting);
        this.setDirection(facing);
        if (!this.survives()) {return false;}
        else {return true;}
    }

    @Override
    public void dropItem(@Nullable Entity brokenEntity)
    {
        if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
            this.playSound(SoundEvents.PAINTING_BREAK, 1.0F, 1.0F);
            if (brokenEntity instanceof Player)
            {
                Player playerentity = (Player)brokenEntity;
                if (playerentity.getAbilities().instabuild) {return;}
            }
            //this.spawnAtLocation(AerialHellBlocksAndItems.AERIAL_HELL_PAINTING.get()); TODO : register the item
        }
    }
}
