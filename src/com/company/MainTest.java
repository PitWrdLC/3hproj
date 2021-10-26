package com.company;


import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.awt.event.InputEvent;

public class MainTest {

    @Test
    public void TestBoard() {
        StartWind realTimeActSW = new StartWind();
        realTimeActSW.StartWind();

        ArrayBoard firstTestAB = new ArrayBoard();
        firstTestAB.NGBoard();

        int time;
        time = firstTestAB.bolvanchikArrayBoard[1][2];
        Assert.assertEquals(9 ,time);
        time =firstTestAB.bolvanchikArrayBoard[5][7];
          Assert.assertEquals(9 ,time);
        time =firstTestAB.bolvanchikArrayBoard[4][2];
        Assert.assertEquals(9 ,time);
        time =firstTestAB.bolvanchikArrayBoard[1][6];
        Assert.assertEquals(9 ,time);
        time =firstTestAB.bolvanchikArrayBoard[3][4];
        Assert.assertEquals(1 ,time );
        time =firstTestAB.bolvanchikArrayBoard[4][3];
        Assert.assertEquals(1,time );
        time =firstTestAB.bolvanchikArrayBoard[4][4];
        Assert.assertEquals(-1,time );
        time =firstTestAB.bolvanchikArrayBoard[3][3];
        Assert.assertEquals(-1 ,time );





    }

}