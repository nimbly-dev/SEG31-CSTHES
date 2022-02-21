package com.yorme.fdma.app.usermanual;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;


import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class UserManualEnglishTest extends TestCase {

    @Rule
    public ActivityTestRule<UserManualEnglish> rule = new ActivityTestRule<>(UserManualEnglish.class);

    @Test
    public void openUserManual() throws Exception{
        UserManualEnglish activityTestRule = rule.getActivity();
        assertNotNull(activityTestRule);
    }

}