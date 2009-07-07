package org.intellij.lang.batch.editor;

import com.intellij.lang.CodeDocumentationAwareCommenter;
import com.intellij.psi.PsiComment;
import com.intellij.psi.tree.IElementType;
import org.intellij.lang.batch.BatchTokenTypes;

/**
 * @author wibotwi
 */
public class BatchCommenter implements CodeDocumentationAwareCommenter {


    public IElementType getLineCommentTokenType() {
        return BatchTokenTypes.COMMENT;
    }

    public IElementType getBlockCommentTokenType() {
        return null;
    }

    public IElementType getDocumentationCommentTokenType() {
        return null;
    }

    public String getDocumentationCommentPrefix() {
        return null;
    }

    public String getDocumentationCommentLinePrefix() {
        return null;
    }

    public String getDocumentationCommentSuffix() {
        return null;
    }

    public boolean isDocumentationComment(PsiComment element) {
        return false;
    }

    public String getLineCommentPrefix() {
        return "rem ";
    }

    public String getBlockCommentPrefix() {
        return null;
    }

    public String getBlockCommentSuffix() {
        return null;
    }
}
