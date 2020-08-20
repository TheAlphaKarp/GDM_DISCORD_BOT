package me.thealphakarp.gdm.command.commands.music;

import me.thealphakarp.gdm.command.CommandContext;
import me.thealphakarp.gdm.command.ICommand;
import me.thealphakarp.gdm.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.TextChannel;

public class Skip implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        if (MusicUtil.validatePermissions(ctx)) {
            final TextChannel channel = ctx.getChannel();

            PlayerManager.getInstance().getMusicManager(ctx.getGuild()).scheduler.nextTrack();
            channel.sendMessage("Song will be skipped");
        }
    }

    @Override
    public String getName() {
        return "skip";
    }


    @Override
    public String getHelp() {
        return null;
    }
}
