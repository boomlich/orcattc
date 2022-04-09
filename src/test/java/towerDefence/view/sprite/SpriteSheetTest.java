package towerDefence.view.sprite;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpriteSheetTest {


    @Test
    void convertIndexToRowColTest(){
        SpriteSheet spriteSheet = new SpriteSheet(5, 4);

        assertEquals(new Point(0, 0), spriteSheet.convertIndexToRowCol(0));
        assertEquals(new Point(0, 3), spriteSheet.convertIndexToRowCol(3));
        assertEquals(new Point(3, 1), spriteSheet.convertIndexToRowCol(13));
        assertEquals(new Point(4, 2), spriteSheet.convertIndexToRowCol(18));
    }

    @Test
    void testTest() {


        HashMap<Integer, List<String>> testHash = new HashMap<Integer, List<String>>();

        testHash.put(0, new ArrayList<>(Arrays.asList("My first entery", "Second")));
        testHash.put(1, new ArrayList<>());
        testHash.put(70000, new ArrayList<>(Arrays.asList("Skipped")));

        int testVal = 70000;
        if (testHash.containsKey(testVal)) {
            System.out.println(testHash.get(testVal));
        }

//        for (int i = 0; i < testHash.size(); i++) {
//            for (int j = 0; j < testHash.get(i).size(); j++) {
//                System.out.println(testHash.get(i).get(j));
//            }
//        }
    }
}
