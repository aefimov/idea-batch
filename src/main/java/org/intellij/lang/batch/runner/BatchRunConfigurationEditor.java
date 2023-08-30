package org.intellij.lang.batch.runner;

import com.intellij.ide.DataManager;
import com.intellij.ide.macro.MacroManager;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author wibotwi
 */
public class BatchRunConfigurationEditor extends SettingsEditor<BatchRunConfiguration> {

    private BatchRunConfigurationForm myForm;

    public BatchRunConfigurationEditor(BatchRunConfiguration batchRunConfiguration) {
        this.myForm = new BatchRunConfigurationForm(batchRunConfiguration);
        DataManager.getInstance().getDataContextFromFocusAsync().then(dataContext -> {
            MacroManager.getInstance().cacheMacrosPreview(dataContext);
            return dataContext;
        });
    }

    @Override
    protected void resetEditorFrom(@NotNull BatchRunConfiguration runConfiguration) {
        BatchRunConfiguration.copyParams(runConfiguration, myForm);
    }

    @Override
    protected void applyEditorTo(@NotNull BatchRunConfiguration runConfiguration) {
        BatchRunConfiguration.copyParams(myForm, runConfiguration);
    }

    @Override
    @NotNull
    protected JComponent createEditor() {
        return myForm.getRootPanel();
    }

    @Override
    protected void disposeEditor() {
        myForm = null;
    }
}
