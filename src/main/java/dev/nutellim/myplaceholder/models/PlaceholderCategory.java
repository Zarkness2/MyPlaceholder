package dev.nutellim.myplaceholder.models;

import dev.nutellim.myplaceholder.MyPlaceholder;
import dev.nutellim.myplaceholder.utilities.FileConfig;
import lombok.Getter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Getter
public class PlaceholderCategory {

    private final String id;
    private final FileConfig fileConfig;
    private final Map<String, Placeholder> placeholders;

    public PlaceholderCategory(MyPlaceholder plugin, String id, File file) {
        this.id = id;
        this.fileConfig = new FileConfig(plugin, file);
        this.placeholders = new HashMap<>();
    }

    public Placeholder getPlaceholder(String id) {
        return placeholders.get(id);
    }

    public void savePlaceholder(String placeholder, String value) {
        fileConfig.set("placeholders." + placeholder + ".value", value);
        fileConfig.save();
    }
}
