package fun.derjxnnik.slickymod.widgetBuilder;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.List;

public class WidgetEditorScreen extends Screen {

    private HudWidget draggingWidget = null;
    private boolean isDragging = false;

    public WidgetEditorScreen() {
        super(Text.of("Widget Editor"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Hintergrund schwarz transparent
        context.fill(0, 0, width, height, 0xCC000000);

        // Sidebar links
        context.fill(10, 10, 160, height - 10, 0xAA000000);
        context.drawText(textRenderer, "Widgets", 20, 15, 0xFFFFFF, false);

        // Widgets rechts rendern
        List<HudWidget> widgets = WidgetManager.getWidgets();
        for (HudWidget widget : widgets) {
            widget.render(context, widget.getX(), widget.getY());
        }

        super.render(context, mouseX, mouseY, delta);
    }


    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (HudWidget widget : WidgetManager.getWidgets()) {
            if (widget.isHovered(mouseX, mouseY)) {
                if (button == 1) {
                    client.setScreen(new WidgetOptionsScreen(widget));
                    return true;
                } else if (button == 0) {
                    draggingWidget = widget;
                    isDragging = true;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (isDragging && draggingWidget != null) {
            draggingWidget.setPosition((int) mouseX, (int) mouseY);
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        isDragging = false;
        draggingWidget = null;
        return true;
    }
}
