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
//public class FreezingRecipeDisplay extends AerialHellRecipeDisplay
//{
//    public static DisplaySerializer<FreezingRecipeDisplay> SERIALIZER = serializer(FreezingRecipeDisplay::new);
//
//    public FreezingRecipeDisplay(EntryStack<?> in, EntryStack<?> out) {super(in, out);}
//
//    public FreezingRecipeDisplay(EntryIngredient in, EntryIngredient out) {super(List.of(in), List.of(out));}
//
//    @Override public CategoryIdentifier<?> getCategoryIdentifier() {return AerialHellRei.FREEZING;}
//
//    @Override public DisplaySerializer<? extends Display> getSerializer() {return SERIALIZER;}
//}
