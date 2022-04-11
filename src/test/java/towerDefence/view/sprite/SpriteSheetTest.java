package towerDefence.view.sprite;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.*;
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
        HashSet<Integer> mySet = new HashSet<>();

        mySet.add(5);
        mySet.add(5);
        mySet.add(3);

        System.out.println(mySet);
    }


}
