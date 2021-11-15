package com.giangvt.unittest.inject;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ArticleManagerTest {

    @InjectMocks
    private ArticleManager manager;

    @Mock
    private User user;

    @Mock
    private ArticleDatabase articleDatabase;

    @Test
    void ensureInjectMockWorks() {
        // calls addListener with an instance of ArticleListener
        manager.initialize();
        // verify that addListener was called with any (instance) of ArticleListener.class
        verify(articleDatabase).addListener(any(ArticleListener.class));
        verify(articleDatabase).setUser(user);
    }
}
