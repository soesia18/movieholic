package at.htlkaindorf.mh.hp;

import java.util.List;

public class Tester {
    public static void main(String[] args) {
        HomePage hp = HomePage.builder().userUID("Test").addHomePageItems("/discover/test", "/search/test").build();

        System.out.println(hp);
    }
}
