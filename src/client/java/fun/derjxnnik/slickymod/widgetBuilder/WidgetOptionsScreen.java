package fun.derjxnnik.slickymod.widgetBuilder;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class WidgetOptionsScreen extends Screen {

    private final HudWidget widget;
    private String selectedWidget = null;


    // Standard-Konstruktor für allgemeinen Zugriff
    public WidgetOptionsScreen() {
        super(Text.of("Widgets bearbeiten"));
        this.widget = null;
    }

    // Konstruktor für spezifisches Widget
    public WidgetOptionsScreen(HudWidget widget) {
        super(Text.of("Widgets bearbeiten"));
        this.widget = widget;
    }



    @Override
    protected void init() {
        super.init(); // wichtig, damit Basis-Funktionen von Screen initiiert werden

        int sidebarX = this.width / 8; // X-Position der Sidebar (linksbündig)
        int startY = this.height / 6;  // Startpunkt für die Buttonliste (leicht nach unten versetzt)
        int buttonHeight = 20;         // Höhe eines Buttons
        int i = 0;                     // Zähler für Positionierung

        for (HudWidget widget : WidgetManager.getWidgets()) {
            int y = startY + i * (buttonHeight + 5); // Y-Position jedes Buttons

            ButtonWidget button = ButtonWidget.builder(
                    Text.literal(widget.getName()), // getName() z. B. „FPS“, „Clock“
                    btn -> {
                        this.selectedWidget = widget.getName();
                        // Optional: neuen Screen öffnen:
                        // this.client.setScreen(new WidgetEditorScreen(widget));
                    }
            ).dimensions(sidebarX, y, 100, buttonHeight).build();

            this.addDrawableChild(button);
            i++;
        }

    }

}
