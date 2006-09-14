package org.intellij.lang.batch.editor.colors;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.options.colors.ColorSettingsPages;
import org.jetbrains.annotations.NonNls;

final class BatchEditorColorsManager implements ApplicationComponent {
    @NonNls
    public String getComponentName() {
        return "BatchEditorColorsManager";
    }

    public void initComponent() {
        ColorSettingsPages.getInstance().registerPage(new BatchColorPage());
    }

    public void disposeComponent() {
    }
}
