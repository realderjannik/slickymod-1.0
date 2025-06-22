package fun.derjxnnik.slickymod;

import fun.derjxnnik.slickymod.widgetBuilder.WidgetOptionsScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class OptionsScreen extends Screen {
    protected OptionsScreen() {
        // The parameter is the title of the screen,
        // which will be narrated when you enter the screen.
        super(Text.literal("Test 123"));
    }


    public ButtonWidget widgets;
    public ButtonWidget button2;

    @Override
    protected void init() {
        widgets = ButtonWidget.builder(
                        Text.of("Widgets"),
                        button -> client.setScreen(new WidgetOptionsScreen()) // Nur Action
                )
                .dimensions(this.width / 2 - 100, this.height / 4 + 120, 200, 20)
                .build();

        addDrawableChild(widgets);

        button2 = ButtonWidget.builder(Text.of("Button test"), button -> System.out.println("You clicked button2!"))
                .dimensions(width / 2 + 5, 20, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button2")))
                .build();

        addDrawableChild(widgets);
        addDrawableChild(button2);
    }
}
