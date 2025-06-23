package fun.derjxnnik.slickymod.widgetBuilder;

import net.minecraft.client.gui.DrawContext;

public interface HudWidget {
    void render(DrawContext context, int x, int y);
    String getId();
    String getName(); // 👈 NEU
    int getX();
    int getY();
    void setPosition(int x, int y);
    boolean isHovered(double mouseX, double mouseY);
}
