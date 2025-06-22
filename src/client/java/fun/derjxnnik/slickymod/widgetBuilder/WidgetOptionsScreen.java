package fun.derjxnnik.slickymod.widgetBuilder;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class WidgetOptionsScreen extends Screen {

    private final HudWidget widget;

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

        for (String widgetName : WidgetManager.getWidgets()) {
            int y = startY + i * (buttonHeight + 5); // Y-Position jedes Buttons

            // Erstelle einen Button für das Widget
            ButtonWidget button = ButtonWidget.builder(
                    Text.literal(widgetName),       // Text auf dem Button
                    btn -> {                        // Lambda bei Klick
                        this.selectedWidget = widgetName; // Merken, welches Widget gerade bearbeitet wird
                    }
            ).dimensions(sidebarX, y, 100, buttonHeight).build();

            this.addDrawableChild(button); // Füge den Button zum GUI hinzu
            i++;
        }
    }

}
