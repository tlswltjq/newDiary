package org.example.newdiary.listener;

import org.example.newdiary.entity.Activity;
import org.example.newdiary.entity.ActivityType;
import org.example.newdiary.entity.Do;
import org.example.newdiary.event.NewActivityEvent;
import org.example.newdiary.service.ActivityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@DisplayName("EventListener 관련 테스트")
@ExtendWith(MockitoExtension.class)
class NewActivityEventListenerTest {
    @Mock
    ActivityService activityService;

    @InjectMocks
    NewActivityEventListener eventListener;

    @Test
    void testHandleNewActivityEvent() {
        Do mockDo = mock(Do.class);
        ActivityType mockActivityType = mock(ActivityType.class);
        Activity mockActivity = mock(Activity.class);
        NewActivityEvent<Do> event = new NewActivityEvent<>(this, mockDo, mockActivityType);

        when(activityService.createActivity(any(Do.class), any(ActivityType.class))).thenReturn(mockActivity);

        eventListener.handleNewActivityEvent(event);

        verify(activityService).createActivity(any(Do.class), any(ActivityType.class));
    }
}