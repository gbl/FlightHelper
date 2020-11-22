package de.guntram.mcmod.flighthelper;

import de.guntram.mcmod.fabrictools.ConfigurationProvider;
import de.guntram.mcmod.fabrictools.GuiModOptions;
import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;

public class MMConfigurationHandler implements ModMenuApi
{
    @Override
    public ConfigScreenFactory getModConfigScreenFactory() {
        return screen -> new GuiModOptions(screen, FlightHelper.MODNAME, ConfigurationProvider.getHandler(FlightHelper.MODNAME));
    }
}
