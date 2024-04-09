package pl.seleniumbot.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.ArrayUtils.isEmpty;

// Class name demand improvement
public class MyDocument {
    private final Element element;

    private MyDocument(Element element) {
        this.element = element;
    }

    public static MyDocument createFrom(String html) {
        return new MyDocument(Jsoup.parse(html));
    }

    public MyDocument fetchFirstByClass(String head, String... tail) {
        Element firstElementWithHeadClass = this.element.getElementsByClass(head).getFirst();
        MyDocument newDocument = new MyDocument(firstElementWithHeadClass);
        return isEmpty(tail)
                ? newDocument
                : newDocument.fetchFirstByClass(tail[0], Arrays.copyOfRange(tail, 1, tail.length));
    }

    public String getHtml() {
        return this.element.html();
    }

    public Integer getHtmlValueAsNumber() {
        return this.element.html()
                .transform(this::clearNotDigitCharacter)
                .transform(Integer::parseInt);
    }

    private String clearNotDigitCharacter(String warehouseCapacityString) {
        return warehouseCapacityString.chars()
                .filter(Character::isDigit)
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
    }

    public MyDocument findById(String id) {
        return new MyDocument(this.element.getElementById(id));
    }
}
