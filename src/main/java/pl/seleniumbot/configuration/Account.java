package pl.seleniumbot.configuration;

import lombok.Data;

@Data
public class Account {
    private String login;
    private String password;
    private String url;
}
