package pl.seleniumbot;

import com.stripe.util.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import static java.lang.StringTemplate.STR;

public class Hello {
    public static void main(String[] args) {
        String text = """
                <div id="build_list">
                    <div class="build_list__column">
                        <h3>resources</h3>
                    <div id="gid-9" data-gid="9" class="build_list__item"><img class="icon--scalable building g9" src="img/x.gif">Bakery</div><div id="gid-6" data-gid="6" class="build_list__item"><img class="icon--scalable building g6" src="img/x.gif">Brickyard</div><div id="gid-2" data-gid="2" class="build_list__item"><img class="icon--scalable building g2" src="img/x.gif">Clay Pit<span class="level">10</span></div><div id="gid-4" data-gid="4" class="build_list__item"><img class="icon--scalable building g4" src="img/x.gif">Cropland<span class="level">10</span></div><div id="gid-8" data-gid="8" class="build_list__item"><img class="icon--scalable building g8" src="img/x.gif">Grain Mill<span class="level">5</span></div><div id="gid-11" data-gid="11" class="build_list__item"><img class="icon--scalable building g11" src="img/x.gif">Granary<span class="level">1</span></div><div id="gid-39" data-gid="39" class="build_list__item"><img class="icon--scalable building g39" src="img/x.gif">Great Granary</div><div id="gid-38" data-gid="38" class="build_list__item"><img class="icon--scalable building g38" src="img/x.gif">Great Warehouse</div><div id="gid-7" data-gid="7" class="build_list__item"><img class="icon--scalable building g7" src="img/x.gif">Iron Foundry</div><div id="gid-3" data-gid="3" class="build_list__item"><img class="icon--scalable building g3" src="img/x.gif">Iron Mine<span class="level">10</span></div><div id="gid-5" data-gid="5" class="build_list__item"><img class="icon--scalable building g5" src="img/x.gif">Sawmill</div><div id="gid-10" data-gid="10" class="build_list__item"><img class="icon--scalable building g10" src="img/x.gif">Warehouse<span class="level">1</span></div><div id="gid-45" data-gid="45" class="build_list__item"><img class="icon--scalable building g45" src="img/x.gif">Waterworks</div><div id="gid-1" data-gid="1" class="build_list__item"><img class="icon--scalable building g1" src="img/x.gif">Woodcutter<span class="level">10</span></div></div>
                    <div class="build_list__column">
                        <h3>military</h3>
                    <div id="gid-48" data-gid="48" class="build_list__item" style="display: none;"><img class="icon--scalable building g48" src="img/x.gif"></div><div id="gid-22" data-gid="22" class="build_list__item"><img class="icon--scalable building g22" src="img/x.gif">Academy<span class="level">10</span></div><div id="gid-13" data-gid="13" class="build_list__item"><img class="icon--scalable building g13" src="img/x.gif">Smithy<span class="level">3</span></div><div id="gid-19" data-gid="19" class="build_list__item"><img class="icon--scalable building g19" src="img/x.gif">Barracks<span class="level">20</span></div><div id="gid-12" data-gid="12" class="build_list__item" style="display: none;"><img class="icon--scalable building g12" src="img/x.gif">Blacksmith</div><div id="gid-31" data-gid="31" class="build_list__item"><img class="icon--scalable building g31" src="img/x.gif">City Wall</div><div id="gid-32" data-gid="32" class="build_list__item"><img class="icon--scalable building g32" src="img/x.gif">Earth Wall</div><div id="gid-29" data-gid="29" class="build_list__item"><img class="icon--scalable building g29" src="img/x.gif">Great Barracks</div><div id="gid-30" data-gid="30" class="build_list__item"><img class="icon--scalable building g30" src="img/x.gif">Great Stable</div><div id="gid-37" data-gid="37" class="build_list__item"><img class="icon--scalable building g37" src="img/x.gif">Hero's Mansion<span class="level">10</span></div><div id="gid-46" data-gid="46" class="build_list__item" style=""><img class="icon--scalable building g46" src="img/x.gif">Hospital</div><div id="gid-43" data-gid="43" class="build_list__item"><img class="icon--scalable building g43" src="img/x.gif">Makeshift Wall</div><div id="gid-33" data-gid="33" class="build_list__item"><img class="icon--scalable building g33" src="img/x.gif">Palisade</div><div id="gid-16" data-gid="16" class="build_list__item"><img class="icon--scalable building g16" src="img/x.gif">Rally Point<span class="level">1</span></div><div id="gid-20" data-gid="20" class="build_list__item"><img class="icon--scalable building g20" src="img/x.gif">Stable<span class="level">20</span></div><div id="gid-42" data-gid="42" class="build_list__item"><img class="icon--scalable building g42" src="img/x.gif">Stone Wall</div><div id="gid-34" data-gid="34" class="build_list__item"><img class="icon--scalable building g34" src="img/x.gif">Stonemason</div><div id="gid-14" data-gid="14" class="build_list__item"><img class="icon--scalable building g14" src="img/x.gif">Tournament Square</div><div id="gid-36" data-gid="36" class="build_list__item"><img class="icon--scalable building g36" src="img/x.gif">Trapper</div><div id="gid-47" data-gid="47" class="build_list__item" style="display: none;"><img class="icon--scalable building g47" src="img/x.gif">Watchtower</div><div id="gid-21" data-gid="21" class="build_list__item"><img class="icon--scalable building g21" src="img/x.gif">Workshop</div></div>
                    <div class="build_list__column">
                        <h3>infrastructure</h3>
                    <div id="gid-35" data-gid="35" class="build_list__item"><img class="icon--scalable building g35" src="img/x.gif">Brewery</div><div id="gid-44" data-gid="44" class="build_list__item"><img class="icon--scalable building g44" src="img/x.gif">Command Center</div><div id="gid-23" data-gid="23" class="build_list__item"><img class="icon--scalable building g23" src="img/x.gif">Cranny</div><div id="gid-18" data-gid="18" class="build_list__item"><img class="icon--scalable building g18" src="img/x.gif">Embassy<span class="level">1</span></div><div id="gid-41" data-gid="41" class="build_list__item"><img class="icon--scalable building g41" src="img/x.gif">Horse Drinking Trough</div><div id="gid-15" data-gid="15" class="build_list__item"><img class="icon--scalable building g15" src="img/x.gif">Main Building<span class="level">5</span></div><div id="gid-17" data-gid="17" class="build_list__item"><img class="icon--scalable building g17" src="img/x.gif">Marketplace<span class="level">20</span></div><div id="gid-26" data-gid="26" class="build_list__item"><img class="icon--scalable building g26" src="img/x.gif">Palace</div><div id="gid-25" data-gid="25" class="build_list__item"><img class="icon--scalable building g25" src="img/x.gif">Residence</div><div id="gid-24" data-gid="24" class="build_list__item"><img class="icon--scalable building g24" src="img/x.gif">Town Hall</div><div id="gid-28" data-gid="28" class="build_list__item"><img class="icon--scalable building g28" src="img/x.gif">Trade Office</div><div id="gid-27" data-gid="27" class="build_list__item"><img class="icon--scalable building g27" src="img/x.gif">Treasury</div><div id="gid-40" data-gid="40" class="build_list__item"><img class="icon--scalable building g40" src="img/x.gif">Wonder of the World</div></div>
                <div></div></div>
                """;
        Document doc = Jsoup.parse(text);
        doc.getElementsByClass("build_list__item").stream()
                .map(item -> {
                    String buildingName = item.text()
                            .replace(" ", "_")
                            .toUpperCase();
                    String id = item.attr("data-gid");
                    return STR."\{buildingName}(\{id}), ";
                })
                .forEach(System.out::println);

    }
}
