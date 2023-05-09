package fr.factionbedrock.aerialhell.Entity;

import com.google.common.collect.Lists;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMotive;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraft.world.entity.decoration.Painting;
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

    public AerialHellPaintingEntity(Level worldIn, BlockPos pos, Direction facing)
    {
        super(worldIn, pos, facing);
        List<Motive> list = Lists.newArrayList();
        list.add(AerialHellMotive.HOSTILE_PARADISE.get());
        list.add(AerialHellMotive.SPOOKY_ISLANDS.get());
        list.add(AerialHellMotive.BROWN_SHROOM_ISLAND.get());
        list.add(AerialHellMotive.LIVING_ISLAND.get());
        list.add(AerialHellMotive.FLOADING_ISLANDS_LANDSCAPE.get());
        list.add(AerialHellMotive.CUTE_SHROOMS.get());
        list.add(AerialHellMotive.MAGICAL_ISLAND.get());
        list.add(AerialHellMotive.FOGGY_PEAKS.get());
        list.add(AerialHellMotive.SHROOM_AND_VEGETATION_ISLANDS.get());
        list.add(AerialHellMotive.SHROOM_ISLAND_PIXELART.get());
        list.add(AerialHellMotive.MAGICAL_SHROOM_ISLAND.get());
        list.add(AerialHellMotive.MYSTERY_ISLANDS.get());
        list.add(AerialHellMotive.CYAN_SHROOM_ISLAND.get());

        int largestPaintingSurface = 0;

        Iterator<Motive> invalidPaintingRemoverIterator = list.iterator();
        while(invalidPaintingRemoverIterator.hasNext())
        {
            Motive paintingType = invalidPaintingRemoverIterator.next();

            if (isPaintingValid(paintingType, facing))
            {
                int newPaintingSurface = getPaintingSurface(paintingType);
                if (newPaintingSurface > largestPaintingSurface) {largestPaintingSurface = newPaintingSurface;}
            }
            else {invalidPaintingRemoverIterator.remove();}
        }

        if (!list.isEmpty()) /*if there are valid painting types*/
        {
            Iterator<Motive> tooSmallPaintingsRemoverIterator = list.iterator();
            while(tooSmallPaintingsRemoverIterator.hasNext())
            {
                Motive valid_painting_type = tooSmallPaintingsRemoverIterator.next();
                if (isTooSmall(valid_painting_type, largestPaintingSurface)) {tooSmallPaintingsRemoverIterator.remove();}
            }
            this.motive = list.get(this.random.nextInt(list.size()));
        }
        this.setDirection(facing);
    }

    public boolean isTooSmall(Motive painting, int paintingSurface) {return getPaintingSurface(painting) < paintingSurface;}
    public int getPaintingSurface(Motive painting) {return painting.getWidth() * painting.getHeight();}

    public boolean isPaintingValid(Motive painting, Direction facing)
    {
        this.motive = painting;
        this.setDirection(facing);
        if (!this.survives()) {return false;}
        else {return true;}
    }

    @Override
    public void dropItem(@Nullable Entity brokenEntity)
    {
        if (this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
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
