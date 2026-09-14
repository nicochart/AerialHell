package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content;

public record ElementPositionInfo(int lineIndex, Alignment alignment, boolean centerVerticallyOnLine, int horizontalOffset, int verticalOffset)
{
    public static class Builder
    {
        private int lineIndex;
        private Alignment alignment;
        private boolean centerVerticallyOnLine;
        private int horizontalOffset;
        private int verticalOffset;

        public Builder()
        {
            this.lineIndex = 0;
            this.alignment = Alignment.LEFT;
            this.centerVerticallyOnLine = false;
            this.horizontalOffset = 0;
            this.verticalOffset = 0;
        }

        public Builder lineIndex(int lineIndex) {this.lineIndex = lineIndex; return this;}
        public Builder alignment(Alignment alignment) {this.alignment = alignment; return this;}
        public Builder centerVerticallyOnLine() {return this.centerVerticallyOnLine(true);}
        public Builder centerVerticallyOnLine(boolean centerVerticallyOnLine) {this.centerVerticallyOnLine = centerVerticallyOnLine; return this;}
        public Builder horizontalOffset(int horizontalOffset) {this.horizontalOffset = horizontalOffset; return this;}
        public Builder verticalOffset(int verticalOffset) {this.verticalOffset = verticalOffset; return this;}

        public ElementPositionInfo build()
        {
            return new ElementPositionInfo(this.lineIndex, this.alignment, this.centerVerticallyOnLine, this.horizontalOffset, this.verticalOffset);
        }
    }
}
