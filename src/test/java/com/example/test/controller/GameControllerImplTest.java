package com.example.test.controller;

import com.example.test.model.CardModel;
import com.example.test.model.PlayerModel;
import com.example.test.service.GameLogicService;
import com.example.test.controller.impl.GameControllerImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@Ignore//TODO: Tests will be implemented
@RunWith(MockitoJUnitRunner.class)
public class GameControllerImplTest {


    @InjectMocks
    GameControllerImpl gameController;

    @Mock
    GameLogicService gameLogicService;

    @Mock
    PlayerModel playerOne;

    @Mock
    PlayerModel playerTwo;

    @Mock
    CardModel oneManaCard;


    @Before
    public void init() {
//        gameController = new GameControllerImpl();
//        Mockito.when(oneManaCard.getManaCost()).thenReturn(1);
//        Mockito.when(oneManaCard.getDamage()).thenReturn(1);
//        Mockito.when(playerOne.getOpponent()).thenReturn(playerTwo);
//        Mockito.when(playerTwo.getOpponent()).thenReturn(playerOne);
    }

    @Test
    public void testRun_NormalCase() {


    }

    @Test
    public void testRun_EndTurn() {
//        try (InputStream inputStream = new ByteArrayInputStream(GameControllerImpl.END_TURN_INPUT.getBytes())) {
//            System.setIn(inputStream);
//            gameController.run();
//        } catch (IOException e) {
//            System.out.println(String.format("ERROR: Test failed due to %s:\n%s", e.getLocalizedMessage(), e));
//        }
    }

    @Test
    public void testRun_InvalidInput() {

    }

    @Test
    public void testRun_InvalidIndex() {

    }
}
