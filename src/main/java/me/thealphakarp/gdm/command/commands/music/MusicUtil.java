package me.thealphakarp.gdm.command.commands.music;

import me.thealphakarp.gdm.command.CommandContext;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class MusicUtil {
    public static boolean validatePermissions(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        assert selfVoiceState != null;
        if (!selfVoiceState.inVoiceChannel()) {
            channel.sendMessage("I am not in a voice channel").queue();
            return false;
        }

        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        assert memberVoiceState != null;
        if (!memberVoiceState.inVoiceChannel()) {
            channel.sendMessage("You don't seem to be in a voice channel").queue();
            return false ;
        }

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            channel.sendMessage("You aren't in the same channel").queue();
            return false;
        }

        return true;
    }
}
