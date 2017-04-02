package de.livingfire.kanboard.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.mockito.BDDMockito.given;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.livingfire.kanboard.util.KanboardUtilDate;

public class KanboardServiceTaskDefaultTest {

    private KanboardServiceTaskDefault service;

    @Mock
    private KanboardUtilDate utilDate;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        given(this.utilDate.getTimeZone()).willReturn(TimeZone.getTimeZone("UTC"));

        this.service = new KanboardServiceTaskDefault(this.utilDate);
    }

    @Test
    public void testGetDeadlineDefault() throws Exception {
        List<Date> deadlinesActual = this.service.getDeadlineDefault();

        assertThat(deadlinesActual, is(not(nullValue())));
        assertThat(deadlinesActual.size(), is(3));

        long first = deadlinesActual.get(0)
                                    .getTime();
        long second = deadlinesActual.get(1)
                                     .getTime();
        long third = deadlinesActual.get(2)
                                    .getTime();

        long oneDay = 86400000L;

        assertThat(second - first, is(oneDay));
        assertThat(third - second, is(2 * oneDay));
    }

}
