package de.guntram.mcmod.flighthelper;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import de.guntram.mcmod.fabrictools.ConfigurationProvider;
import de.guntram.mcmod.fabrictools.GuiModOptions;

public class MMConfigurationHandler implements ModMenuApi
{
    @Override
    public ConfigScreenFactory getModConfigScreenFactory() {
        return screen -> GuiModOptions.getGuiModOptions(screen, FlightHelper.MODNAME, ConfigurationProvider.getHandler(FlightHelper.MODNAME));
    }
}
