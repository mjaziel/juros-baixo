package com.jurosbaixo.fizzbuzz.service;

import com.jurosbaixo.fizzbuzz.client.FizzBuzzClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class DiscoverTreasureUseCaseTest  {

    @InjectMocks
    private DiscoverTreasureUseCase discoverTreasureUseCase;

    @Mock
    private FizzBuzzClient client;

    /**
     *  Sorry! I had no time to finish this test but using mokito I was trying to mock the second
     *  layer to test exclusively the code in the UseCase class.
     * @throws Exception
     */

    @Test
    public void givenGetTreasure_whanAllApisReturnOk_thenTreasureIsFound() throws Exception {

        List<Integer> list = Arrays.asList(1,5,7,9);

        when(client.getFizzBuzz()).thenReturn(list);
        when(client.postFizzBuzz(any(), any())).thenReturn(Boolean.TRUE);
        when(client.getFizzBuzzTreasure(any())).thenReturn("I'm the Treasure");

        Assert.assertEquals(discoverTreasureUseCase.getTreasure(),"I'm the Treasure");

    }
}