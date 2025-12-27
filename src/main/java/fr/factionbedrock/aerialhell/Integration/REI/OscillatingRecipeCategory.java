//package fr.factionbedrock.aerialhell.Integration.REI;
//
//import fr.factionbedrock.aerialhell.AerialHell;
//import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
//import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
//import me.shedaniel.math.Point;
//import me.shedaniel.math.Rectangle;
//import me.shedaniel.rei.api.client.gui.Renderer;
//import me.shedaniel.rei.api.client.gui.widgets.Widget;
//import me.shedaniel.rei.api.client.gui.widgets.Widgets;
//import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
//import me.shedaniel.rei.api.common.category.CategoryIdentifier;
//import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
//import me.shedaniel.rei.api.common.util.EntryStacks;
//import net.minecraft.text.Text;
//import net.minecraft.util.Identifier;
//
//import java.util.LinkedList;
//import java.util.List;
//
//public class OscillatingRecipeCategory implements DisplayCategory<BasicDisplay>
//{
//	public final static Identifier TEXTURE = AerialHell.id("textures/gui/container/oscillator.png");
//
//    @Override public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {return AerialHellRei.OSCILLATING;}
//
//    @Override public Text getTitle() {return Text.translatable("block.aerialhell.oscillator");}
//
//    @Override public Renderer getIcon() {return EntryStacks.of(AerialHellBlocks.OSCILLATOR.asItem().getDefaultStack());}
//
//    @Override public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds)
//    {
//        final Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);
//        List<Widget> widgets = new LinkedList<>();
//        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 175, 82)));
//
//        widgets.add(Widgets.createSlot(new Point(startPoint.x + 56, startPoint.y + 17)).entries(display.getInputEntries().get(0)));
//
//        widgets.add(Widgets.createSlot(new Point(startPoint.x + 56, startPoint.y + 53)).entries(List.of(EntryStacks.of(AerialHellItems.FLUORITE))));
//
//        widgets.add(Widgets.createSlot(new Point(startPoint.x + 115, startPoint.y + 34)).backgroundEnabled(false).entries(display.getOutputEntries().get(0)));
//
//        return widgets;
//    }
//
//    @Override public int getDisplayHeight() {return 90;}
//}