package pl.seleniumbot.webdriver;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Construction {
    private int constructionId;
    private int currentLevel;
    private int levelAfterBuild;
}
