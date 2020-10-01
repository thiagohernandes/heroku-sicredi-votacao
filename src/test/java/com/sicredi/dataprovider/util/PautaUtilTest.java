package com.sicredi.dataprovider.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PautaUtilTest {

    @Test
    public void minutosToSegundos__Success__Test() {
        Integer segundos = PautaUtil.minutosToSegundos(1);
        Assert.assertTrue(segundos == 60);
    }
}
