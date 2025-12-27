//package fr.factionbedrock.aerialhell.Integration.REI;
//
//import com.mojang.serialization.codecs.RecordCodecBuilder;
//import me.shedaniel.rei.api.common.display.DisplaySerializer;
//import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
//import me.shedaniel.rei.api.common.entry.EntryIngredient;
//import me.shedaniel.rei.api.common.entry.EntryStack;
//import net.minecraft.network.codec.PacketCodec;
//
//import java.util.List;
//
//public abstract class AerialHellRecipeDisplay extends BasicDisplay
//{
//    protected static <D extends AerialHellRecipeDisplay> DisplaySerializer<D> serializer(Constructor<D> constructor)
//    {
//        return DisplaySerializer.of(
//                RecordCodecBuilder.mapCodec(instance -> instance.group(
//                        EntryIngredient.codec().fieldOf("input").forGetter(D::getIn),
//                        EntryIngredient.codec().fieldOf("output").forGetter(D::getOut)
//                ).apply(instance, constructor::create)),
//                PacketCodec.tuple(
//                        EntryIngredient.streamCodec(), D::getIn,
//                        EntryIngredient.streamCodec(), D::getOut,
//                        constructor::create
//                ));
//    }
//
//    public AerialHellRecipeDisplay(EntryStack<?> in, EntryStack<?> out)
//    {
//        this(List.of(EntryIngredient.of(in)), List.of(EntryIngredient.of(out)));
//    }
//
//    public AerialHellRecipeDisplay(EntryIngredient in, EntryIngredient out) {this(List.of(in), List.of(out));}
//
//    public AerialHellRecipeDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {super(inputs, outputs);}
//
//    public final EntryIngredient getIn() {return getInputEntries().get(0);}
//
//    public final EntryIngredient getOut() {return getOutputEntries().get(0);}
//
//    protected interface Constructor<T extends AerialHellRecipeDisplay>
//    {
//        T create(EntryIngredient in, EntryIngredient out);
//    }
//}
