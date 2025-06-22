package fun.derjxnnik.slickymod;

import fun.derjxnnik.slickymod.widgetBuilder.WidgetEditorScreen;
import fun.derjxnnik.slickymod.widgetBuilder.WidgetManager;
import fun.derjxnnik.slickymod.widgets.ClockWidget;
import fun.derjxnnik.slickymod.widgets.FPSWidget;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import org.lwjgl.glfw.GLFW;

public class SlickyModClient implements ClientModInitializer {

	private static KeyBinding openEditorKey;
	@Override
	public void onInitializeClient() {
		ScreenEvents.AFTER_INIT.register((client, screen, width, height) -> {
			if (!(screen instanceof GameMenuScreen)) return;

			var buttons = Screens.getButtons(screen);

			buttons.removeIf(btn -> {
				Text text = btn.getMessage();
				var content = text.getContent();

				if (content instanceof TranslatableTextContent translatable) {
					String key = translatable.getKey();
					return key.equals("menu.sendFeedback") || key.equals("menu.reportBugs");
				}
				return false;
			});

			int baseY = -1;

			for (var btn : buttons) {
				var content = btn.getMessage().getContent();
				if (content instanceof TranslatableTextContent translatable &&
						translatable.getKey().equals("gui.advancements")) {
					baseY = btn.getY();
					break;
				}
			}


			if (baseY == -1) baseY = height / 4 + 24;


			buttons.add(ButtonWidget.builder(
					Text.of("SlickyMod Options"),
					btn -> client.setScreen(new OptionsScreen())
			).dimensions(width / 2 - 102, baseY + 24, 204, 20).build());

		});

		HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {

			WidgetManager.renderAll(drawContext);
			WidgetManager.register(new FPSWidget(), 5, 5);
			WidgetManager.register(new ClockWidget(), 5, 10);
		});

		openEditorKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.slickymod.open_widget_editor",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_RIGHT_SHIFT,
				"key.categories.misc"
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (openEditorKey.wasPressed()) {
				client.setScreen(new WidgetEditorScreen());
			}
		});

	}
}
