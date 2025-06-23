package fun.derjxnnik.slickymod.widgetBuilder;

import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class WidgetManager {
    private static final List<HudWidget> hudWidgets = new ArrayList<>();
    private static final List<String> widgets = List.of("fps", "clock");

    public static void register(HudWidget widget, int x, int y) {
        widget.setPosition(x, y);
        hudWidgets.add(widget);
    }

    public static void renderAll(DrawContext context) {
        hudWidgets.forEach(widget -> widget.render(context, widget.getX(), widget.getY()));
    }



    public static List<HudWidget> getWidgets() {
        return hudWidgets;
    }
}

