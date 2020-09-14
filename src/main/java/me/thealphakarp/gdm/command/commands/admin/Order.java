package me.thealphakarp.gdm.command.commands.admin;

import me.thealphakarp.gdm.command.CommandContext;
import me.thealphakarp.gdm.command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Order implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final Message message = ctx.getMessage();
        final Member member = ctx.getMember();
        final List<String> args = ctx.getArgs();
        
        if (!member.hasPermission(Permission.MANAGE_ROLES)) {
            channel.sendMessage("You are missing permissions to manage these roles.").queue();
            return;
        }

        final List<Member> members = ctx.getGuild().getMembers();
        // System.out.println(members.size());

        final List<Role> generalRole = ctx.getGuild().getRolesByName("Algemeen", true);

        for (var m: members) {
            /*if (m.getUser().isBot()) {
                System.out.println("user is bot");
                continue;
            }
            ;

            System.out.println("user found");
            final List<Role> collect = m.getRoles().stream().filter(item -> !item.getName().toLowerCase().equals("admin"))
                    .collect(Collectors.toList());

            ctx.getGuild().modifyMemberRoles(m, generalRole, collect).queue();
            System.out.println("user roles deleted");*/
            if (!m.getRoles().contains(ctx.getGuild().getRoleById("509647160690868224"))) {
                ctx.getGuild().modifyMemberRoles(m, generalRole, null).queue();
            }
        }

    }

    @Override
    public String getName() {
        return "order";
    }

    @Override
    public String getHelp() {
        return null;
    }
}
