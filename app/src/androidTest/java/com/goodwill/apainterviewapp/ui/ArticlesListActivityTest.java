package com.goodwill.apainterviewapp.ui;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;


import com.goodwill.apainterviewapp.R;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class ArticlesListActivityTest {

    @Test
    public void isActivityInView() {
     ActivityScenario<ArticlesListActivity> activity=   ActivityScenario.launch(ArticlesListActivity.class);
     onView(withId(R.id.list_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void isProgressBarVisible() {
        ActivityScenario<ArticlesListActivity> activity=   ActivityScenario.launch(ArticlesListActivity.class);
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()));
    }
}