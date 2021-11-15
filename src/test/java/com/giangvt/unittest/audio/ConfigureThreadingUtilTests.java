package com.giangvt.unittest.audio;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ConfigureThreadingUtilTests {
    @Mock
    MyApplication myApplication;

    @Test
    void ensureThatThreadPoolCanBeConfigured() {
        // mock MyApplication
//        MyApplication myApplication = new MyApplication();
//        myApplication = mock(MyApplication.class);
        // call ConfigureThreadingUtil.configureThreadPool
        ConfigureThreadingUtil.configureThreadPool(myApplication);
        // verify that getNumberOfThreads was the only one called on app
        verify(myApplication, only()).getNumberOfThreads();
    }
}
