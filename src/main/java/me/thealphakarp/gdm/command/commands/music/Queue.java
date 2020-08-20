package me.thealphakarp.gdm.command.commands.music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import me.thealphakarp.gdm.command.CommandContext;
import me.thealphakarp.gdm.command.ICommand;
import me.thealphakarp.gdm.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.concurrent.BlockingQueue;

public class Queue implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        if (MusicUtil.validatePermissions(ctx)) {
            TextChannel channel = ctx.getChannel();

            final BlockingQueue<AudioTrack> queue = PlayerManager.getInstance().
                    getMusicManager(ctx.getGuild()).scheduler.getQueue();

            EmbedBuilder queueEmbed = new EmbedBuilder().setTitle("Music queue");

            for (AudioTrack track: queue) {
                long minutes = (track.getDuration() / 1000) / 60;
                long seconds = (track.getDuration() / 1000) % 60;

                queueEmbed =  queueEmbed.addField(
                        track.getInfo().title,
                        minutes + ":" + seconds,
                        false
                );
            }

            channel.sendMessage(queueEmbed.build()).queue();
        }
    }

    @Override
    public String getName() {
        return "queue";
    }

    @Override
    public String getHelp() {
        return null;
    }
}
