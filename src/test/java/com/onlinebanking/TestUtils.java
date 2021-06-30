package com.onlinebanking;

import com.onlinebanking.constant.ProfileTypeConstants;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * This class holds common test functionalities to be used with other Test.
 *
 * @author George on 6/25/2021
 * @version 1.0
 * @since 1.0
 */
@ActiveProfiles(ProfileTypeConstants.TEST)
public class TestUtils {
    private static final String[] IGNORED_FIELDS = {"id", "createdAt", "createdBy", "updatedAt", "updatedBy"};

    public static Collection<String> getIgnoredFields() {
        return Collections.unmodifiableCollection(Arrays.asList(IGNORED_FIELDS));
    }

}
