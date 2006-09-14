package org.intellij.lang.batch.editor.colors;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.diagnostic.Logger;
import org.intellij.lang.batch.editor.BatchHighlighterColors;
import org.intellij.lang.batch.fileTypes.BatchFileTypeManager;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.io.File;
import java.io.IOException;

final class BatchColorPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] EMPTY_ATTRIBUTES_DESCRIPTOR_ARRAY = new AttributesDescriptor[]{};
    private static final ColorDescriptor[] EMPTY_COLOR_DESCRIPTOR_ARRAY = new ColorDescriptor[]{};
    @NonNls
    private static final String SAMPLE = extractIdeaScript();

    private final Set<AttributesDescriptor> attributeDescriptors = new HashSet<AttributesDescriptor>();

    public BatchColorPage() {
        attributeDescriptors.add(new AttributesDescriptor("Brackets", BatchHighlighterColors.BRACKETS));
        attributeDescriptors.add(new AttributesDescriptor("Braces", BatchHighlighterColors.BRACES));
        attributeDescriptors.add(new AttributesDescriptor("Parenths", BatchHighlighterColors.PARENTHS));
        attributeDescriptors.add(new AttributesDescriptor("Comment", BatchHighlighterColors.COMMENT));
        attributeDescriptors.add(new AttributesDescriptor("Operator", BatchHighlighterColors.OPERATION_SIGN));
        attributeDescriptors.add(new AttributesDescriptor("Keyword", BatchHighlighterColors.KEYWORD));
        attributeDescriptors.add(new AttributesDescriptor("String", BatchHighlighterColors.STRING));
        attributeDescriptors.add(new AttributesDescriptor("Number", BatchHighlighterColors.NUMBER));
        attributeDescriptors.add(new AttributesDescriptor("Label", BatchHighlighterColors.LABEL));
        attributeDescriptors.add(new AttributesDescriptor("Label Reference", BatchHighlighterColors.LABEL_REFERENCE));
        attributeDescriptors.add(new AttributesDescriptor("Environment Variable", BatchHighlighterColors.ENVIRONMENT_VARIABLE));
        attributeDescriptors.add(new AttributesDescriptor("Environment Definition", BatchHighlighterColors.ENVIRONMENT_VARIABLE_DEFINITION));
        attributeDescriptors.add(new AttributesDescriptor("Variable", BatchHighlighterColors.VARIABLE));
        attributeDescriptors.add(new AttributesDescriptor("Expression", BatchHighlighterColors.EXPRESSION));
    }

    @Nullable
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    public AttributesDescriptor[] getAttributeDescriptors() {
        return attributeDescriptors.toArray(EMPTY_ATTRIBUTES_DESCRIPTOR_ARRAY);
    }

    @NotNull
    public ColorDescriptor[] getColorDescriptors() {
        return EMPTY_COLOR_DESCRIPTOR_ARRAY;
    }

    @NonNls
    @NotNull
    public String getDemoText() {
        return SAMPLE;
    }

    private static String extractIdeaScript() {
        String binPath = PathManager.getBinPath();
        try {
            char[] chars = FileUtil.loadFileText(new File(binPath, "idea.bat"));
            return new String(chars);
        } catch (IOException e) {
            Logger.getInstance(BatchColorPage.class.getName()).error(e);
        }
        return "";
    }

    @NotNull
    public String getDisplayName() {
        return "Batch Script";
    }

    @NotNull
    public SyntaxHighlighter getHighlighter() {
        return BatchFileTypeManager.getInstance().getFileType().getLanguage().getSyntaxHighlighter(null, null);
    }

    @Nullable
    public Icon getIcon() {
        return BatchFileTypeManager.getInstance().getFileType().getIcon();
    }

}
