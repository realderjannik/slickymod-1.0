package fun.derjxnnik.slickymod;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlickyMod implements ModInitializer {

	public static final String MOD_ID = "slickymod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		System.out.println(LOGGER + "✅ Main mod entrypoint geladen");
	}
}