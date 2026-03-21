package dev.nutellim.myplaceholder.integrations.types;

import dev.nutellim.myplaceholder.MyPlaceholder;
import dev.nutellim.myplaceholder.controllers.PlaceholderController;
import dev.nutellim.myplaceholder.integrations.PlaceholderAPIHook;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MyPlExpansion extends PlaceholderExpansion {

    private final MyPlaceholder plugin;
    private final PlaceholderController placeholderController;

    public MyPlExpansion(MyPlaceholder plugin) {
        this.plugin = plugin;
        this.placeholderController = plugin.getPlaceholderController();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public @NotNull String getAuthor() {
        return "Risas";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "mypl";
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier) {
        return PlaceholderAPIHook.getPlaceholderValue(player, identifier, "myplaceholder", placeholderController);
    }
}
