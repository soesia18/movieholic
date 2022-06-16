package at.htlkaindorf.mh.hp;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <b>Movieholic</b><br><br>
 * HomePage Class to hold the userID, amountMovies und a list og the homepageItems <br>
 * @author Simon
 * @version 1.0
 * @since last update: 23.05.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HomePage {
    private String userUID;
    private int amountMovies;
    private List<String> homePageItems;

    /**
     * creates a new {@link HomePageBuilder} instance
     * @return {@link HomePageBuilder} instance
     */
    public static HomePage.HomePageBuilder builder() {
        return new HomePage.HomePageBuilder();
    }

    public static class HomePageBuilder {
        private String userUID;
        private int amountMovies;
        private List<String> homePageItems = new ArrayList<>();

        /**
         * sets the userUID
         * @param userUID id from a user
         * @return {@link HomePageBuilder} instance
         */
        public HomePageBuilder userUID(String userUID) {
            this.userUID = userUID;
            return this;
        }

        /**
         * sets the amountMovies
         * @param amountMovies amount of movies
         * @return {@link HomePageBuilder} instance
         */
        public HomePageBuilder amountMovies(int amountMovies) {
            this.amountMovies = amountMovies;
            return this;
        }

        /**
         * sets the homePageItems
         * @param homePageItems
         * @return
         */
        public HomePageBuilder homePageItems(List<String> homePageItems) {
            this.homePageItems = homePageItems;
            return this;
        }

        /**
         * add a single homePage item
         * @param homePageItems homePage item
         * @return {@link HomePageBuilder} instance
         */
        public HomePageBuilder addHomePageItems(String... homePageItems) {
            this.homePageItems.addAll(Arrays.asList(homePageItems));
            return this;
        }

        /**
         * builds the {@link HomePage} instance
         * @return {@link HomePage} instance
         */
        public HomePage build() {
            return new HomePage(this.userUID, this.amountMovies, this.homePageItems);
        }
    }
}
