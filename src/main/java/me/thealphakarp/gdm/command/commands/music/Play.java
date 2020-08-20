package me.thealphakarp.gdm.command.commands.music;

import me.thealphakarp.gdm.command.CommandContext;
import me.thealphakarp.gdm.command.ICommand;
import me.thealphakarp.gdm.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class Play implements ICommand {
    @SuppressWarnings("ConstantConditions")
    @Override
    public void handle(CommandContext ctx) {
        if (MusicUtil.validatePermissions(ctx)) {
            final TextChannel channel = ctx.getChannel();

            final List<String> args = ctx.getArgs();
            final String songUrl = args.get(0);

            PlayerManager.getInstance()
                    .loadAndPlay(channel, songUrl);
        }
    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getHelp() {
        return null;
    }
}
