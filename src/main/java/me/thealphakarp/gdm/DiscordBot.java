package me.thealphakarp.gdm;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class DiscordBot {

    private DiscordBot() throws LoginException {
        WebUtils.setUserAgent("GDM Discord bot");
        EmbedUtils.setEmbedBuilder(
                () -> new EmbedBuilder()
                .setColor(0x3883d9)
                .setFooter("GDM Bot")
        );

        new JDABuilder()
                .setToken(Config.get("TOKEN"))
                .addEventListeners(new Listener())
                .setActivity(Activity.watching(Config.get("ACTIVITY")))
                .build();
    }

    public static void main(String[] args) throws LoginException {
        new DiscordBot();
    }
}