//package fr.factionbedrock.aerialhell.Integration.REI;
//
//import me.shedaniel.rei.api.common.category.CategoryIdentifier;
//import me.shedaniel.rei.api.common.display.Display;
//import me.shedaniel.rei.api.common.display.DisplaySerializer;
//import me.shedaniel.rei.api.common.entry.EntryIngredient;
//import me.shedaniel.rei.api.common.entry.EntryStack;
//
//import java.util.List;
//
//public class OscillatingRecipeDisplay extends AerialHellRecipeDisplay
//{
//    public static DisplaySerializer<OscillatingRecipeDisplay> SERIALIZER = serializer(OscillatingRecipeDisplay::new);
//
//    public OscillatingRecipeDisplay(EntryStack<?> in, EntryStack<?> out) {super(in, out);}
//
//    public OscillatingRecipeDisplay(EntryIngredient in, EntryIngredient out) {super(List.of(in), List.of(out));}
//
//    @Override public CategoryIdentifier<?> getCategoryIdentifier() {return AerialHellRei.OSCILLATING;}
//
//    @Override public DisplaySerializer<? extends Display> getSerializer() {return SERIALIZER;}
//}
