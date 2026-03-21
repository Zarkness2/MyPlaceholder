package dev.nutellim.myplaceholder.commands.subcommands;

import dev.nutellim.myplaceholder.MyPlaceholder;
import dev.nutellim.myplaceholder.controllers.PlaceholderController;
import dev.nutellim.myplaceholder.models.Placeholder;
import dev.nutellim.myplaceholder.models.PlaceholderCategory;
import dev.nutellim.myplaceholder.utilities.ChatUtil;
import dev.nutellim.myplaceholder.utilities.JavaUtil;
import dev.nutellim.myplaceholder.utilities.subcommand.SubCommand;
import net.kyori.adventure.audience.Audience;
import org.bukkit.command.CommandSender;

public class MyPlaceholderConfigCommand extends SubCommand {

    private final MyPlaceholder plugin;
    private final PlaceholderController placeholderController;

    public MyPlaceholderConfigCommand(MyPlaceholder plugin) {
        super(
                "myplaceholder.command.myplaceholder.config",
                "Set a placeholder value.",
                "<file>", "<placeholder>", "<value>"
        );
        this.plugin = plugin;
        this.placeholderController = plugin.getPlaceholderController();
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Audience audience = plugin.getAdventure().sender(sender);
        String prefix = plugin.getLang().getPrefix();

        if (args.length < 4) {
            ChatUtil.sendMessage(audience, "&cUsage: /" + label + " config <file> <placeholder> <value>");
            return;
        }

        String category = args[1];
        PlaceholderCategory placeholderCategory = placeholderController.getPlaceholderCategory(category);

        if (placeholderCategory == null) {
            ChatUtil.sendMessage(audience, prefix + "&cCategory '" + category + "' not found.");
            return;
        }

        String placeholder = args[2];
        Placeholder placeholderObject = placeholderCategory.getPlaceholder(placeholder);

        if (placeholderObject == null) {
            ChatUtil.sendMessage(audience, prefix + "&cPlaceholder '" + placeholder + "' not found in '" + category + "'.");
            return;
        }

        if (placeholderObject.isLocked()) {
            String msg = plugin.getLang().get("command-messages.config-locked")
                    .replace("%placeholder%", placeholder);
            ChatUtil.sendMessage(audience, prefix + msg);
            return;
        }

        String value = JavaUtil.join(args, 3);
        placeholderObject.setValue(value);
        placeholderCategory.savePlaceholder(placeholder, value);

        String msg = plugin.getLang().get("command-messages.config-set")
                .replace("%placeholder%", placeholder)
                .replace("%value%", value);
        ChatUtil.sendMessage(audience, prefix + msg);
    }
}
