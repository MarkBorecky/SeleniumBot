package pl.seleniumbot.configuration;

import lombok.Getter;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Configuration {
    private static final String configurationFileName = "./src/main/resources/account.yaml";
    @Getter
    private Account account;

    public Configuration() {
        Yaml yaml = new Yaml(new Constructor(Account.class, new LoaderOptions()));
        try {
            InputStream inputStream = Files.newInputStream(Paths.get(configurationFileName));
            this.account = yaml.load(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot read configuration file", e);
        }
    }
}
