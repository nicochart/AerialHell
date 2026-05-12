package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Util.ClientHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.List;

public class GuideBookScreen extends Screen
{
    private float textScale;

    private static final Identifier BOOK_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/guide_book_page.png");
    private static final Identifier NAVIGATION_ARROW_PREVIOUS_PAGE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_arrow_previous_page.png");
    private static final Identifier NAVIGATION_ARROW_PREVIOUS_PAGE_HOVERED = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_arrow_previous_page_hovered.png");
    private static final Identifier NAVIGATION_ARROW_NEXT_PAGE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_arrow_next_page.png");
    private static final Identifier NAVIGATION_ARROW_NEXT_PAGE_HOVERED = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/navigation_arrow_next_page_hovered.png");

    private static class Page
    {
        private final String pageName;
        private final int pageIndex;
        private final Identifier backgroundTexture;
        List<Paragraph> paragraphs;

        private Page(String pageName, Identifier backgroundTexture, int pageIndex)
        {
            this.pageName = pageName;
            this.backgroundTexture = backgroundTexture;
            this.pageIndex = pageIndex;
            this.paragraphs = new ArrayList<>();
        }

        private void render(Font font, GuiGraphics graphics, float scale, List<Line> Lines, int bookLeft, int bookTop)
        {
            graphics.blit(RenderPipelines.GUI_TEXTURED, backgroundTexture, bookLeft, bookTop, 0f, 0f, BOOK_TEXTURE_WIDTH, BOOK_TEXTURE_HEIGHT, BOOK_TEXTURE_WIDTH, BOOK_TEXTURE_HEIGHT);

            for (Paragraph paragraph : this.paragraphs)
            {
                String paragraphText = Language.getInstance().getOrDefault(paragraph.key);
                int currentLineIndex = paragraph.startLineIndex;

                List<String> textLines = ClientHelper.wrapTextForBook(paragraphText, font, (int) (LINE_WIDTH_NO_MARGIN / scale));
                for (int i = 0; i < textLines.size() && currentLineIndex < MAX_LINES_PER_TECHNICAL_PAGE - 1; i++)
                {
                    int startX = paragraph.centered ? Lines.get(currentLineIndex).centerX(textLines.get(i), font, scale) : Lines.get(currentLineIndex).startX;

                    ClientHelper.renderText(font, graphics, Component.literal(textLines.get(i)), startX, Lines.get(currentLineIndex).startY, paragraph.color, scale);
                    currentLineIndex++;
                }
            }
        }

        private String pageName() {return this.pageName;}
        private int pageIndex() {return this.pageIndex;}

        private Page addParagraph(int startLineIndex, boolean centered, String paragraphName)
        {
            this.paragraphs.add(new Paragraph(startLineIndex, centered, 0xFF7A5C3A, "aerialhell.guide_book."+ pageName +"."+paragraphName));
            return this;
        }

        private Page addParagraph(int startLineIndex, boolean centered, int color, String paragraphName)
        {
            this.paragraphs.add(new Paragraph(startLineIndex, centered, color, "aerialhell.guide_book."+ pageName +"."+paragraphName));
            return this;
        }
    }

    private record Paragraph(int startLineIndex, boolean centered, int color, String key) {}

    private static final List<Page> ALL_PAGES = List.of(
            new Page("summary", BOOK_TEXTURE, 0)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "welcome_text"),
            new Page("mobs_1", BOOK_TEXTURE, 1)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1"),
            new Page("mobs_2", BOOK_TEXTURE, 2)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1"),
            new Page("mobs_3", BOOK_TEXTURE, 3)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1"),
            new Page("bosses_1", BOOK_TEXTURE, 4)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1"),
            new Page("bosses_2", BOOK_TEXTURE, 5)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(4, false, "content_1"),
            new Page("items_1", BOOK_TEXTURE, 6)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1"),
            new Page("items_2", BOOK_TEXTURE, 7)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1"),
            new Page("items_3", BOOK_TEXTURE, 8)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1")
                    .addParagraph(16, false, "content_2"),
            new Page("items_4", BOOK_TEXTURE, 9)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1"),
            new Page("items_5", BOOK_TEXTURE, 10)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(19, true, 0xFFFF0000, "content_1"),
            new Page("armors_1", BOOK_TEXTURE, 11)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1"),
            new Page("armors_2", BOOK_TEXTURE, 12)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1"),
            new Page("armors_3", BOOK_TEXTURE, 13)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1"),
            new Page("armors_4", BOOK_TEXTURE, 14)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, true, 0xFFFF0000, "content_1"),
            new Page("tools_1", BOOK_TEXTURE, 15)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1"),
            new Page("tools_2", BOOK_TEXTURE, 16)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1"),
            new Page("tools_3", BOOK_TEXTURE, 17)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1"),
            new Page("tools_4", BOOK_TEXTURE, 18)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1"),
            new Page("tools_5", BOOK_TEXTURE, 19)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1"),
            new Page("utilities_1", BOOK_TEXTURE, 20)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1"),
            new Page("utilities_2", BOOK_TEXTURE, 21)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(5, false, "content_1"),
            new Page("utilities_3", BOOK_TEXTURE, 22)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1"),
            new Page("utilities_4", BOOK_TEXTURE, 23)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1"),
            new Page("utilities_5", BOOK_TEXTURE, 24)
                    .addParagraph(0, true, 0xFF5C3A1E, "title")
                    .addParagraph(2, false, "content_1")
    );

    private static class Tab
    {
        private final String key;
        private final int color;
        private final int pageIndex;

        private Tab(String name, int color, int pageIndex)
        {
            this.key = "aerialhell.guide_book.tab." + name;
            this.color = color;
            this.pageIndex = pageIndex;
        }
    }

    private static final List<Tab> TABS_LEFT = List.of(
            new Tab("mobs",  0xFF4CAF50, 1),
            new Tab("bosses",  0xFFE53935, 4),
            new Tab("items", 0xFFFFB300, 6));

    private static final List<Tab> TABS_RIGHT = List.of(
            new Tab("armors",  0xFF1E88E5, 11),
            new Tab("tools",    0xFFFF6D00, 15),
            new Tab("utilities", 0xFF8E24AA, 20));

    //book position
    private int bookLeft, bookRight, bookTop, bookBottom, leftPageLeft;
    //navigation arrows position
    private int navigationArrowTop;
    private int navigationArrowBottom;
    private int leftNavigationArrowLeft;
    private int leftNavigationArrowRight;
    private int rightNavigationArrowLeft;
    private int rightNavigationArrowRight;

    //book dimensions
    private static final int BOOK_TEXTURE_WIDTH = 384;
    private static final int BOOK_TEXTURE_HEIGHT = 192;
    //tabs dimensions
    private static final int TAB_WIDTH = 18;
    private static final int TAB_HEIGHT = 36;
    private static final int TAB_GAP = 10;
    //navigation arrow dimension
    private static final int NAVIGATION_ARROW_SIZE = 20;

    //page
    private int firstLineY;
    private int leftPageLineX, rightPageLineX;
    private int leftPageCenterX, rightPageCenterX;
    private static final int LINE_HEIGHT = 10;
    private static final int MARGIN_WIDTH = 10;
    private static final int LINE_WIDTH = 178;
    private static final int LINE_WIDTH_NO_MARGIN = LINE_WIDTH - 2 * MARGIN_WIDTH;
    private static final int MAX_LINES_PER_VISUAL_PAGE = 17;
    private static final int MAX_LINES_PER_TECHNICAL_PAGE = MAX_LINES_PER_VISUAL_PAGE * 2;

    private record Line(int index, int startX, int centerX, int startY)
    {
        private int centerX(String textToCenter, Font font, float textScale) {return this.centerX - (int) (font.width(textToCenter) * textScale / 2.0F);}
    }
    private final List<Line> lines = new ArrayList<>();

    //state
    private static final int PAGE_SUMMARY_INDEX = 0;
    private int currentPage = PAGE_SUMMARY_INDEX;

    public GuideBookScreen() {super(Component.empty());}

    @Override protected void init()
    {
        super.init();
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
            this.lines.add(new Line(lineIndex, isLeftPageLine ? this.leftPageLineX : this.rightPageLineX, isLeftPageLine ? this.leftPageCenterX : this.rightPageCenterX, this.firstLineY + (lineIndex % MAX_LINES_PER_VISUAL_PAGE) * LINE_HEIGHT));
        }
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
        for (int i = 0; i < TABS_LEFT.size(); i++)
        {
            if (this.isHoveringTab(event.x(), event.y(), i, true))
            {
                this.navigateToTab(TABS_LEFT.get(i));
                return true;
            }
        }
        for (int i = 0; i < TABS_RIGHT.size(); i++)
        {
            if (this.isHoveringTab(event.x(), event.y(), i, false))
            {
                this.navigateToTab(TABS_RIGHT.get(i));
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

    private boolean isHoveringTab(double mouseX, double mouseY, int index, boolean isLeft)
    {
        int[] pos = getTabPos(index, isLeft);
        return mouseX >= pos[0] && mouseX <= pos[0] + TAB_WIDTH && mouseY >= pos[1] && mouseY <= pos[1] + TAB_HEIGHT;
    }

    @Override public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        for (int i = 0; i < TABS_LEFT.size(); i++) {this.renderTab(guiGraphics, i, true, mouseX, mouseY);}
        for (int i = 0; i < TABS_RIGHT.size(); i++) {this.renderTab(guiGraphics, i, false, mouseX, mouseY);}

        this.renderPageContent(guiGraphics);
        this.renderNavigationButtons(guiGraphics, mouseX, mouseY);

        super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
    }

    private void renderTab(GuiGraphics graphics, int index, boolean isLeft, int mouseX, int mouseY)
    {
        int[] pos = getTabPos(index, isLeft);
        int x = pos[0];
        int y = pos[1];
        Tab tab = isLeft ? TABS_LEFT.get(index) : TABS_RIGHT.get(index);
        boolean hovered = isHoveringTab(mouseX, mouseY, index, isLeft);

        int tabWidth = TAB_WIDTH + (hovered ? 4 : 0);
        int xDraw = isLeft ? x - (hovered ? 4 : 0) : x;

        graphics.fill(xDraw, y, xDraw + tabWidth, y + TAB_HEIGHT, tab.color);

        //border
        graphics.fill(xDraw, y, xDraw + tabWidth, y + 1, 0xFF1A1A1A);
        graphics.fill(xDraw, y + TAB_HEIGHT - 1, xDraw + tabWidth, y + TAB_HEIGHT, 0xFF1A1A1A);
        graphics.fill(xDraw, y, xDraw + 1, y + TAB_HEIGHT, 0xFF1A1A1A);
        graphics.fill(xDraw + tabWidth - 1, y, xDraw + tabWidth, y + TAB_HEIGHT, 0xFF1A1A1A);

        //hover text
        if (hovered)
        {
            graphics.setTooltipForNextFrame(this.font, Component.translatable(tab.key), mouseX, mouseY);
        }
    }

    private int[] getTabPos(int tabIndex, boolean isLeft)
    {
        int totalH = TABS_LEFT.size() * TAB_HEIGHT + (TABS_LEFT.size() - 1) * TAB_GAP;
        int startY = bookTop + (BOOK_TEXTURE_HEIGHT - totalH) / 2;
        int y = startY + tabIndex * (TAB_HEIGHT + TAB_GAP);
        int x = isLeft ? bookLeft - TAB_WIDTH : bookRight;
        return new int[]{x, y};
    }

    private void renderPageContent(GuiGraphics graphics)
    {
        Page currentPage = null;
        for (Page page : ALL_PAGES) {if (page.pageIndex() == this.currentPage) currentPage = page;}
        if (currentPage == null) {return;}

        currentPage.render(this.font, graphics, this.textScale, lines, this.bookLeft, this.bookTop);
    }

    private void renderNavigationButtons(GuiGraphics graphics, int mouseX, int mouseY)
    {
        //previous page arrow
        if (this.currentPage != 0)
        {
            Identifier previousArrowTexture = this.isHoveringPrevArrow(mouseX, mouseY) ? NAVIGATION_ARROW_PREVIOUS_PAGE_HOVERED : NAVIGATION_ARROW_PREVIOUS_PAGE;
            graphics.blit(RenderPipelines.GUI_TEXTURED, previousArrowTexture, this.leftNavigationArrowLeft, this.navigationArrowTop, 0f, 0f, NAVIGATION_ARROW_SIZE, NAVIGATION_ARROW_SIZE, NAVIGATION_ARROW_SIZE, NAVIGATION_ARROW_SIZE);
        }

        //next page arrow
        if (this.currentPage != ALL_PAGES.size() - 1)
        {
            Identifier nextArrowTexture = this.isHoveringNextArrow(mouseX, mouseY) ? NAVIGATION_ARROW_NEXT_PAGE_HOVERED : NAVIGATION_ARROW_NEXT_PAGE;
            graphics.blit(RenderPipelines.GUI_TEXTURED, nextArrowTexture, this.rightNavigationArrowLeft, this.navigationArrowTop, 0f, 0f, NAVIGATION_ARROW_SIZE, NAVIGATION_ARROW_SIZE, NAVIGATION_ARROW_SIZE, NAVIGATION_ARROW_SIZE);
        }
    }

    private void navigateToTab(Tab tab) {this.currentPage = tab.pageIndex;}

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