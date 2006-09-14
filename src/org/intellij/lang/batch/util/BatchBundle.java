package org.intellij.lang.batch.util;

import com.intellij.CommonBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.PropertyKey;

import java.util.ResourceBundle;

public final class BatchBundle {
    @NonNls
    private static final String BUNDLE_NAME = "org.intellij.lang.batch.util.BatchBundle";
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private BatchBundle() {
    }

    public static String message(@PropertyKey(resourceBundle = BUNDLE_NAME)String key, Object... params) {
        return CommonBundle.message(BUNDLE, key, params);
    }
}
