package dev.nutellim.myplaceholder.commands.subcommands;

import dev.nutellim.myplaceholder.MyPlaceholder;
import dev.nutellim.myplaceholder.utilities.ChatUtil;
import dev.nutellim.myplaceholder.utilities.subcommand.SubCommand;
import net.kyori.adventure.audience.Audience;
import org.bukkit.command.CommandSender;

public class MyPlaceholderReloadCommand extends SubCommand {

    private final MyPlaceholder plugin;

    public MyPlaceholderReloadCommand(MyPlaceholder plugin) {
        super("myplaceholder.command.myplaceholder.reload", "Reload the plugin configuration.");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Audience audience = plugin.getAdventure().sender(sender);
        plugin.onReload();
        String msg = plugin.getLang().get("command-messages.reload");
        ChatUtil.sendMessage(audience, plugin.getLang().getPrefix() + msg);
    }
}
