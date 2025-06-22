package fun.derjxnnik.slickymod.widgets;

import fun.derjxnnik.slickymod.widgetBuilder.HudWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public class ClockWidget implements HudWidget {

    private int x, y;

    @Override
    public void render(DrawContext context, int x, int y) {
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer renderer = client.textRenderer;
        int hour = (int) (System.currentTimeMillis() / 1000L % 24);
        int minute = (int) (System.currentTimeMillis() / 60000L % 60);
        context.drawText(renderer, "Clock: " + hour + ":" + minute, x, y, 0xFFFFFF, true);

    }

    @Override
    public String getId() {
        return "clock";
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void setPosition(int x, int y) {

    }

    @Override
    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= x && mouseY >= y && mouseX <= x + 100 && mouseY <= y + 20;    }
}
