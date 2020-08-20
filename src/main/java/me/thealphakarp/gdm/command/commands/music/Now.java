package me.thealphakarp.gdm.command.commands.music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import me.thealphakarp.gdm.command.CommandContext;
import me.thealphakarp.gdm.command.ICommand;
import me.thealphakarp.gdm.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.TextChannel;

public class Now implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        if (MusicUtil.validatePermissions(ctx)) {
            final TextChannel channel = ctx.getChannel();

            final AudioTrack playingTrack = PlayerManager.getInstance()
                    .getMusicManager(ctx.getGuild()).audioPlayer.getPlayingTrack();

            channel.sendMessage(String.format("Currently playing `%s`", playingTrack.getInfo().title)).queue();
        }
    }

    @Override
    public String getName() {
        return "np";
    }

    @Override
    public String getHelp() {
        return null;
    }
}
