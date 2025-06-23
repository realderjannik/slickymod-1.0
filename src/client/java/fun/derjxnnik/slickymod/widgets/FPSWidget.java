package fun.derjxnnik.slickymod.widgets;

import fun.derjxnnik.slickymod.widgetBuilder.HudWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class FPSWidget implements HudWidget {

    private int x, y;

    @Override
    public void render(DrawContext context, int x, int y) {
        int fps = MinecraftClient.getInstance().getCurrentFps();
        context.drawText(MinecraftClient.getInstance().textRenderer, "FPS: " + fps, x, y, 0xFFFFFF, true);
    }

    @Override
    public String getId() {
        return "fps";
    }

    @Override
    public String getName() {
        return "fps";
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= x && mouseY >= y && mouseX <= x + 100 && mouseY <= y + 20;
    }
}
