package org.intellij.lang.batch.editor;

import com.intellij.lang.Commenter;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiFile;
import com.intellij.psi.templateLanguages.MultipleLangCommentProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class MultiLangCommentProvider implements MultipleLangCommentProvider, Commenter {
  @Nullable
  @Override
  public Commenter getLineCommenter(@NotNull PsiFile file,
                                    @NotNull Editor editor,
                                    @NotNull Language lineStartLanguage,
                                    @NotNull Language lineEndLanguage) {

    return new BatchCommenter();
  }

  @Override
  public boolean canProcess(@NotNull PsiFile file, @NotNull FileViewProvider viewProvider) {
      // FileType fileType = file.getFileType();
      // boolean b = fileType == BatchFileType.BATCH_FILE_TYPE;
      String name = file.getViewProvider().getVirtualFile().getName();
      boolean isBatch = name.endsWith(".bat") || name.endsWith(".cmd");

      return isBatch;
  }

  @Nullable
  @Override
  public String getLineCommentPrefix() {
    return null;
  }

  @Nullable
  @Override
  public String getBlockCommentPrefix() {
    return "";
  }

  @Nullable
  @Override
  public String getBlockCommentSuffix() {
    return "";
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
