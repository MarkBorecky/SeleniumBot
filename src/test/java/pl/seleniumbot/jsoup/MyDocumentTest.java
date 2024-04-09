package pl.seleniumbot.jsoup;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyDocumentTest {

    @Test
    void shouldFetchElementByClass() {
        //given
        String html = """
                <html>
                    <div class="first">
                        first
                        <div class="second">second</div>
                    </div>
                </html>
                """;
        MyDocument myDocument = MyDocument.createFrom(html);

        //when
        MyDocument result = myDocument.fetchFirstByClass("second");

        //then
        assertThat(result.getHtml()).isEqualTo("second");
    }

    @Test
    void shouldFindElementByClassChain() {
        //given
        String html = """
                <div>
                    <div class="third">
                        not expected content
                    </div>
                    <div class="first">
                        first
                        <div class="second">
                            second
                            <div class="third">
                                expected content
                            </div>
                        </div>
                    </div>
                </div>
                """;
        MyDocument myDocument = MyDocument.createFrom(html);

        //when
        MyDocument result = myDocument.fetchFirstByClass("first", "second", "third");

        //then
        assertThat(result.getHtml()).isEqualTo("expected content");
    }
}