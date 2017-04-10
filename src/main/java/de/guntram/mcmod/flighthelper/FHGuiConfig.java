package de.guntram.mcmod.flighthelper;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import static net.minecraftforge.common.config.Configuration.CATEGORY_CLIENT;
import net.minecraftforge.fml.client.config.GuiConfig;

public class FHGuiConfig extends GuiConfig {
    public FHGuiConfig(GuiScreen parent) {
        super(parent,
                new ConfigElement(ConfigurationHandler.getConfig().getCategory(CATEGORY_CLIENT)).getChildElements(),
                FlightHelper.MODID,
                false,
                false,
                "FlightHelper Configuration");
    }
}
