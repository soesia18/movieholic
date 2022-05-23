package at.htlkaindorf.mh.hp;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HomePage {
    private String userUID;
    private int amountMovies;
    private List<String> homePageItems;

    public static HomePage.HomePageBuilder builder() {
        return new HomePage.HomePageBuilder();
    }

    public static class HomePageBuilder {
        private String userUID;
        private int amountMovies;
        private List<String> homePageItems = new ArrayList<>();

        public HomePageBuilder userUID (String userUID){
            this.userUID = userUID;
            return this;
        }

        public HomePageBuilder amountMovies (int amountMovies){
            this.amountMovies = amountMovies;
            return this;
        }

        public HomePageBuilder homePageItems (List<String> homePageItems){
            this.homePageItems = homePageItems;
            return this;
        }

        public HomePageBuilder addHomePageItems (String ... homePageItems){
            this.homePageItems.addAll(Arrays.asList(homePageItems));
            return this;
        }

        public HomePage build () {
            return new HomePage(this.userUID, this.amountMovies, this.homePageItems);
        }
    }
}
