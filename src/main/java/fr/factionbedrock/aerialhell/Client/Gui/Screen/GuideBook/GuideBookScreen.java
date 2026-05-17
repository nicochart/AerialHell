package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.*;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.RecipeDisplay.CraftingTableRecipeDisplay;
import fr.factionbedrock.aerialhell.Client.Util.TextureInfo;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class GuideBookScreen extends Screen
{
    //book dimensions
    private static final int BOOK_TEXTURE_WIDTH = 384;
    private static final int BOOK_TEXTURE_HEIGHT = 192;
    //navigation arrow dimension
    private static final int NAVIGATION_ARROW_SIZE = 20;

    //tabs dimensions
    private static final int TAB_MARGIN = 4; //margin (gap) from top to first tab, or from last tab to bottom
    private static final int TAB_WIDTH = 18;
    private static final int HOVERED_TAB_EXTRA_WIDTH = 4;
    private static final int TAB_HEIGHT = 24;
    private static final int TAB_GAP = 8;

    //page
    private static final int LINE_HEIGHT = 10;
    private static final int MARGIN_WIDTH = 10;
    private static final int LINE_WIDTH = 178;
    private static final int LINE_WIDTH_NO_MARGIN = LINE_WIDTH - 2 * MARGIN_WIDTH;
    private static final int MAX_LINES_PER_VISUAL_PAGE = 17;
    private static final int MAX_LINES_PER_TECHNICAL_PAGE = MAX_LINES_PER_VISUAL_PAGE * 2;

    private static final TextureInfo BOOK_TEXTURE = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/guide_book_page.png"), BOOK_TEXTURE_WIDTH, BOOK_TEXTURE_HEIGHT);
    private static final TextureInfo NAVIGATION_ARROW_PREVIOUS_PAGE = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_arrow_previous_page.png"), NAVIGATION_ARROW_SIZE, NAVIGATION_ARROW_SIZE);
    private static final TextureInfo NAVIGATION_ARROW_PREVIOUS_PAGE_HOVERED = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_arrow_previous_page_hovered.png"), NAVIGATION_ARROW_SIZE, NAVIGATION_ARROW_SIZE);
    private static final TextureInfo NAVIGATION_ARROW_NEXT_PAGE = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_arrow_next_page.png"), NAVIGATION_ARROW_SIZE, NAVIGATION_ARROW_SIZE);
    private static final TextureInfo NAVIGATION_ARROW_NEXT_PAGE_HOVERED = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_arrow_next_page_hovered.png"), NAVIGATION_ARROW_SIZE, NAVIGATION_ARROW_SIZE);

    private float textScale;

    private static final List<Page> ALL_PAGES = List.of(
            new Page("summary", BOOK_TEXTURE, 0)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "welcome_text")
                    .addItemTexture(4, Alignment.LEFT, 2.0F, AerialHellItems.VOLUCITE_PICKAXE, true)
                    .addItemTexture(7, Alignment.CENTER, 2.0F, AerialHellItems.ARSONIST_PICKAXE, false)
                    .addItemTexture(10, Alignment.RIGHT, 2.0F, AerialHellItems.VOLUCITE_ORE, true)
                    .addTextureDisplay(18, Alignment.CENTER, 2.0F, "environment/celestial/aerial_hell_sun", 32, 32),
            new Page("mobs_1", BOOK_TEXTURE, 1)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.RIGHT, "content_1")
                    .addTextureDisplay(18, Alignment.CENTER, 2.0F, "block/freezer_side_on", 16, 48),
            new Page("mobs_2", BOOK_TEXTURE, 2)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("mobs_3", BOOK_TEXTURE, 3)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("bosses_1", BOOK_TEXTURE, 4)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("bosses_2", BOOK_TEXTURE, 5)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(4, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("items_1", BOOK_TEXTURE, 6)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1")
                    .addCraftingTableRecipeDisplay(18, Alignment.CENTER, 1.5F, new CraftingTableRecipeDisplay.Ingredients(
                            () -> null, AerialHellItems.RUBY, () -> null,
                            () -> null, AerialHellItems.RUBY, () -> null,
                            () -> null, AerialHellItems.SKY_STICK, () -> null
                    ), AerialHellItems.RUBY_SWORD, true),
            new Page("items_2", BOOK_TEXTURE, 7)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1")
                    .addSmeltingRecipeDisplay(18, Alignment.CENTER, 1.5F, AerialHellItems.RAW_RUBY, AerialHellItems.OVERHEATED_RUBY, true),
            new Page("items_3", BOOK_TEXTURE, 8)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1")
                    .addParagraph(16, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_2")
                    .addFreezingRecipeDisplay(18, Alignment.CENTER, 1.5F, AerialHellItems.OVERHEATED_RUBY, AerialHellItems.RUBY, true),
            new Page("items_4", BOOK_TEXTURE, 9)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1")
                    .addOscillatingRecipeDisplay(18, Alignment.CENTER, 1.5F, AerialHellItems.RAW_RUBY, AerialHellItems.RUBY, true),
            new Page("items_5", BOOK_TEXTURE, 10)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(19, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFFFF0000, "content_1"),
            new Page("armors_1", BOOK_TEXTURE, 11)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("armors_2", BOOK_TEXTURE, 12)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("armors_3", BOOK_TEXTURE, 13)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("armors_4", BOOK_TEXTURE, 14)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFFFF0000, "content_1"),
            new Page("tools_1", BOOK_TEXTURE, 15)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("tools_2", BOOK_TEXTURE, 16)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("tools_3", BOOK_TEXTURE, 17)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("tools_4", BOOK_TEXTURE, 18)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("tools_5", BOOK_TEXTURE, 19)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("utilities_1", BOOK_TEXTURE, 20)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("utilities_2", BOOK_TEXTURE, 21)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(5, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("utilities_3", BOOK_TEXTURE, 22)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("utilities_4", BOOK_TEXTURE, 23)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1"),
            new Page("utilities_5", BOOK_TEXTURE, 24)
                    .addParagraph(0, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.CENTER, 0xFF5C3A1E, "title")
                    .addParagraph(2, MAX_LINES_PER_TECHNICAL_PAGE - 1, LINE_WIDTH_NO_MARGIN, Alignment.LEFT, "content_1")
    );

    //The guide book is designed to contain 6 tabs on each side.
    //Each tab is 24 pixels high.
    //Tabs are separated by an 8-pixel gap.
    //The first and last tabs are separated from the top/bottom edges by a 4-pixel margin.
    //Layout:
    //   book top
    //      4 px margin
    //      24 px tab
    //      8 px gap
    //      ...
    //      4 px margin
    //   book bottom
    private static class TabList
    {
        private final List<Tab> tabs;
        private final boolean isLeft;
        private final Supplier<Integer> bookLeft;
        private final Supplier<Integer> bookTop;
        private int nextTabYOffsetFromBookTop;

        private TabList(boolean isLeft, Supplier<Integer> bookLeft, Supplier<Integer> bookTop)
        {
            this.tabs = new ArrayList<>();
            this.bookLeft = bookLeft;
            this.bookTop = bookTop;
            this.isLeft = isLeft;
            this.nextTabYOffsetFromBookTop = TAB_MARGIN;
        }

        public List<Tab> getTabs() {return this.tabs;}

        private TabList add(String name, int pageIndex)
        {
            int currentTabYOffset = this.nextTabYOffsetFromBookTop;
            this.tabs.add(new Tab(
                    name,
                    (isHovered) -> isHovered ? TAB_WIDTH + HOVERED_TAB_EXTRA_WIDTH : TAB_WIDTH,
                    (isHovered) -> TAB_HEIGHT,
                    bookLeft,
                    bookTop,
                    this.isLeft ? (isHovered) -> - TAB_WIDTH - (isHovered ? HOVERED_TAB_EXTRA_WIDTH : 0) : (isHovered) -> BOOK_TEXTURE_WIDTH,
                    (isHovered) -> currentTabYOffset,
                    this.isLeft ? (isHovered) -> 0.0F : (isHovered) -> isHovered ? 0.0F : 4.0F, //blitU : left tab is offset by default (due to relativeXPos moving). right tab : offset when not hovered, to give the impression that we are "pulling the tab" when hovered, like left one
                    (isHovered) -> 0.0F, //blitY : always 0.0F, no matter if isHovered
                    pageIndex
            ));
            this.nextTabYOffsetFromBookTop += TAB_HEIGHT + TAB_GAP;
            return this;
        }
    }

    private TabList leftTabs, rightTabs;

    //book position
    private int bookLeft, bookRight, bookTop, bookBottom, leftPageLeft;
    //navigation arrows position
    private int navigationArrowTop;
    private int navigationArrowBottom;
    private int leftNavigationArrowLeft;
    private int leftNavigationArrowRight;
    private int rightNavigationArrowLeft;
    private int rightNavigationArrowRight;

    //page
    private int firstLineY;
    private int leftPageLineX, rightPageLineX;
    private int leftPageCenterX, rightPageCenterX;
    private final List<Line> lines = new ArrayList<>();

    //state
    private static final int PAGE_SUMMARY_INDEX = 0;
    private int currentPage = PAGE_SUMMARY_INDEX;

    public GuideBookScreen() {super(Component.empty());}

    @Override protected void init()
    {
        super.init();
        this.createTabs();

        this.textScale = Minecraft.getInstance().options.forceUnicodeFont().get() ? 1.0F : 0.8F;

        this.bookLeft = (this.width - BOOK_TEXTURE_WIDTH) / 2;
        this.bookTop  = (this.height - BOOK_TEXTURE_HEIGHT) / 2;
        this.bookRight = this.bookLeft + BOOK_TEXTURE_WIDTH;
        this.bookBottom = this.bookTop + BOOK_TEXTURE_HEIGHT;
        this.navigationArrowBottom = this.bookBottom - 5;
        this.navigationArrowTop = this.navigationArrowBottom - NAVIGATION_ARROW_SIZE;
        this.leftNavigationArrowLeft = this.bookLeft + 5;
        this.leftNavigationArrowRight = this.leftNavigationArrowLeft + NAVIGATION_ARROW_SIZE;
        this.rightNavigationArrowRight = this.bookRight - 5;
        this.rightNavigationArrowLeft = this.rightNavigationArrowRight - NAVIGATION_ARROW_SIZE;

        this.leftPageLeft = this.bookLeft + 206;
        this.firstLineY = this.bookTop + 9;
        this.leftPageLineX = this.bookLeft + MARGIN_WIDTH;
        this.rightPageLineX = this.leftPageLeft + MARGIN_WIDTH;
        this.leftPageCenterX = this.leftPageLineX + LINE_WIDTH_NO_MARGIN / 2;
        this.rightPageCenterX = this.rightPageLineX + LINE_WIDTH_NO_MARGIN / 2;

        for (int lineIndex = 0; lineIndex < MAX_LINES_PER_TECHNICAL_PAGE; lineIndex++)
        {
            boolean isLeftPageLine = lineIndex < MAX_LINES_PER_VISUAL_PAGE;
            this.lines.add(new Line(lineIndex, isLeftPageLine ? this.leftPageLineX : this.rightPageLineX, isLeftPageLine ? this.leftPageCenterX : this.rightPageCenterX, isLeftPageLine ? this.leftPageLineX + LINE_WIDTH_NO_MARGIN : this.rightPageLineX + LINE_WIDTH_NO_MARGIN, this.firstLineY + (lineIndex % MAX_LINES_PER_VISUAL_PAGE) * LINE_HEIGHT));
        }
    }

    protected void createTabs()
    {
        this.leftTabs = new TabList(true, () -> this.bookLeft, () -> this.bookTop)
                .add("journey", 1)
                .add("crafting", 4)
                .add("materials", 6)
                .add("effects", 6)
                .add("enchanting", 6)
                .add("exploration", 6);

        this.rightTabs = new TabList(false, () -> this.bookLeft, () -> this.bookTop)
                .add("bestiary", 11)
                .add("bosses", 15)
                .add("structures", 20)
                .add("dungeons", 20)
                .add("shadow_and_light", 20)
                .add("items", 20);
    }

    @Override public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick)
    {
        //navigation arrows
        if (this.isHoveringPrevArrow(event.x(), event.y()))
        {
            this.navigateToPreviousPage();
            return true;
        }

        if (this.isHoveringNextArrow(event.x(), event.y()))
        {
            this.navigateToNextPage();
            return true;
        }

        //tabs
        for (Tab tab : leftTabs.getTabs())
        {
            if (tab.isHovered(event.x(), event.y()))
            {
                this.navigateToTab(tab);
                return true;
            }
        }
        for (Tab tab : rightTabs.getTabs())
        {
            if (tab.isHovered(event.x(), event.y()))
            {
                this.navigateToTab(tab);
                return true;
            }
        }
        return super.mouseClicked(event, doubleClick);
    }

    private boolean isHoveringPrevArrow(double mouseX, double mouseY)
    {
        return mouseX >= this.leftNavigationArrowLeft && mouseX <= this.leftNavigationArrowRight  && mouseY >= this.navigationArrowTop && mouseY <= this.navigationArrowBottom;
    }

    private boolean isHoveringNextArrow(double mouseX, double mouseY)
    {
        return mouseX >= this.rightNavigationArrowLeft && mouseX <= this.rightNavigationArrowRight && mouseY >= this.navigationArrowTop && mouseY <= this.navigationArrowBottom;
    }

    @Override public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick)
    {
        for (Tab tab : leftTabs.getTabs()) {tab.render(graphics, this.font, mouseX, mouseY);}
        for (Tab tab : rightTabs.getTabs()) {tab.render(graphics, this.font, mouseX, mouseY);}

        this.renderPageContent(graphics, mouseX, mouseY);
        this.renderNavigationButtons(graphics, mouseX, mouseY);

        super.extractBackground(graphics, mouseX, mouseY, partialTick);
    }

    private void renderPageContent(GuiGraphicsExtractor graphics, int mouseX, int mouseY)
    {
        Page currentPage = null;
        for (Page page : ALL_PAGES) {if (page.pageIndex() == this.currentPage) currentPage = page;}
        if (currentPage == null) {return;}

        currentPage.render(this.font, graphics, this.textScale, lines, this.bookLeft, this.bookTop, mouseX, mouseY);
    }

    private void renderNavigationButtons(GuiGraphicsExtractor graphics, int mouseX, int mouseY)
    {
        //previous page arrow
        if (this.currentPage != 0)
        {
            TextureInfo previousArrowTexture = this.isHoveringPrevArrow(mouseX, mouseY) ? NAVIGATION_ARROW_PREVIOUS_PAGE_HOVERED : NAVIGATION_ARROW_PREVIOUS_PAGE;
            graphics.blit(RenderPipelines.GUI_TEXTURED, previousArrowTexture.texture(), this.leftNavigationArrowLeft, this.navigationArrowTop, previousArrowTexture.u(), previousArrowTexture.v(), previousArrowTexture.width(), previousArrowTexture.height(), previousArrowTexture.textureWidth(), previousArrowTexture.textureHeight());
        }

        //next page arrow
        if (this.currentPage != ALL_PAGES.size() - 1)
        {
            TextureInfo nextArrowTexture = this.isHoveringNextArrow(mouseX, mouseY) ? NAVIGATION_ARROW_NEXT_PAGE_HOVERED : NAVIGATION_ARROW_NEXT_PAGE;
            graphics.blit(RenderPipelines.GUI_TEXTURED, nextArrowTexture.texture(), this.rightNavigationArrowLeft, this.navigationArrowTop, nextArrowTexture.u(), nextArrowTexture.v(), nextArrowTexture.width(), nextArrowTexture.height(), nextArrowTexture.textureWidth(), nextArrowTexture.textureHeight());
        }
    }

    private void navigateToTab(Tab tab) {this.currentPage = tab.pageIndex();}

    private void navigateToPage(Page page) {this.currentPage = page.pageIndex();}

    private void navigateToPreviousPage()
    {
        int nextIndex = getCurrentIndex() - 1;
        if (nextIndex >= 0 && nextIndex < ALL_PAGES.size()) {this.navigateToPage(ALL_PAGES.get(nextIndex));}
    }

    private void navigateToNextPage()
    {
        int nextIndex = getCurrentIndex() + 1;
        if (nextIndex >= 0 && nextIndex < ALL_PAGES.size()) {this.navigateToPage(ALL_PAGES.get(nextIndex));}
    }

    private int getCurrentIndex()
    {
        for (int i = 0; i < ALL_PAGES.size(); i++)
        {
            if (ALL_PAGES.get(i).pageIndex() == this.currentPage) {return i;}
        }
        return -1;
    }

    @Override public boolean isPauseScreen() {return false;}
}