package fr.factionbedrock.aerialhell.Integration.REI;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;

public class OscillatingDisplay extends BasicDisplay
{
    public static final DisplaySerializer<OscillatingDisplay> SERIALIZER = DisplaySerializer.of(
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    EntryIngredient.codec().fieldOf("input").forGetter(OscillatingDisplay::getIn),
                    EntryIngredient.codec().fieldOf("output").forGetter(OscillatingDisplay::getOut)
            ).apply(instance, OscillatingDisplay::new)),
            StreamCodec.composite(
                    EntryIngredient.streamCodec(),
                    OscillatingDisplay::getIn,
                    EntryIngredient.streamCodec(),
                    OscillatingDisplay::getOut,
                    OscillatingDisplay::new
            ));

    public OscillatingDisplay(EntryStack<?> in, EntryStack<?> out) {
        this(List.of(EntryIngredient.of(in)), List.of(EntryIngredient.of(out)));
    }

    public OscillatingDisplay(EntryIngredient in, EntryIngredient out) {
        this(List.of(in), List.of(out));
    }

    public OscillatingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    public final EntryIngredient getIn() {
        return getInputEntries().get(0);
    }

    public final EntryIngredient getOut() {
        return getOutputEntries().get(0);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return AerialHellRei.OSCILLATING;
    }

    @Override
    public DisplaySerializer<? extends Display> getSerializer() {
        return SERIALIZER;
    }
}
