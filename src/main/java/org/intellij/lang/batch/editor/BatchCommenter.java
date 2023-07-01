package org.intellij.lang.batch.editor;

import com.intellij.lang.Commenter;
import com.intellij.util.containers.ContainerUtil;
import org.intellij.lang.batch.settings.AppSettingsState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author wibotwi
 */
public class BatchCommenter implements Commenter {

    @Override
    public String getLineCommentPrefix() {
        AppSettingsState settings = AppSettingsState.getInstance();
        return settings.commentPrefix;
    }

    @NotNull
    @Override
    public List<String> getLineCommentPrefixes() {
        return ContainerUtil.newArrayList(getLineCommentPrefix(), ":: ");
    }

    @Override
    public String getBlockCommentPrefix() {
        return "";
    }

    @Override
    public String getBlockCommentSuffix() {
        return null;
    }

    @Nullable
    @Override
    public String getCommentedBlockCommentPrefix() {
        return null;
    }

    @Nullable
    @Override
    public String getCommentedBlockCommentSuffix() {
        return null;
    }
}
