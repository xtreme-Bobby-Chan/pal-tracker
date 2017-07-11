package io.pivotal.pal.tracker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.actuate.health.Status;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TimeEntryHealthIndicatorTest {

    @Mock
    TimeEntryRepository timeEntryRepository;

    private TimeEntryHealthIndicator timeEntryHealthIndicator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        timeEntryHealthIndicator = new TimeEntryHealthIndicator(timeEntryRepository);
    }

    @Test
    public void timeEntriesLessThanFive_shouldReturnStatusUp() {
        when(timeEntryRepository.list()).thenReturn(Arrays.asList(new TimeEntry(), new TimeEntry(), new TimeEntry(), new TimeEntry()));

        assertTrue(timeEntryHealthIndicator.health().getStatus() == Status.UP);
    }

    @Test
    public void timeEntriesGreaterThanFour_shouldReturnStatusDown() {
        when(timeEntryRepository.list()).thenReturn(Arrays.asList(new TimeEntry(), new TimeEntry(), new TimeEntry(), new TimeEntry(), new TimeEntry()));

        assertTrue(timeEntryHealthIndicator.health().getStatus() == Status.DOWN);
    }
}