package me.thealphakarp.gdm.command.commands.gdm;

import me.thealphakarp.gdm.command.CommandContext;
import me.thealphakarp.gdm.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class Register implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final Member member = ctx.getMember();

        final String firstName = ctx.getArgs().get(0);
        final String lastName = ctx.getArgs().get(1);
        final List<String> requestedRoles = ctx.getArgs().subList(2, ctx.getArgs().size());

        final List<Role> memberRoles = member.getRoles();
        final List<Role> roleMentions = ctx.getGuild().getRoles();

        System.out.println(firstName);
        System.out.println(lastName);

        for (String r: requestedRoles) {
            System.out.println(r);
        }

        /*
           arne
           six
           <@&504307761673273346>
        */

        channel.sendMessage("Look in console").queue();
    }

    @Override
    public String getName() {
        return "register";
    }

    public String parseRole(String mentionedRole) {
        return mentionedRole.substring(3);
    }


    @Override
    public String getHelp() {
        return "Register for a role in the discord server.";
    }
}
