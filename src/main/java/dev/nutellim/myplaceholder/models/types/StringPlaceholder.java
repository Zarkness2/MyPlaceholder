package dev.nutellim.myplaceholder.models.types;

import dev.nutellim.myplaceholder.models.Placeholder;
import org.bukkit.configuration.ConfigurationSection;

public class StringPlaceholder extends Placeholder {
    public StringPlaceholder(String id, ConfigurationSection section) {
        super(id, section);
    }
}
