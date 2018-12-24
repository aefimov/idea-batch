package org.intellij.lang.batch.editor.colors;

import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.openapi.util.io.FileUtil;
import org.intellij.lang.batch.editor.BatchHighlighterColors;
import org.intellij.lang.batch.fileTypes.BatchSyntaxHighlighter;
import org.intellij.lang.batch.util.BatchIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sun.reflect.Reflection;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

final class BatchColorPage implements ColorSettingsPage {
    private static final ColorDescriptor[] EMPTY_COLOR_DESCRIPTOR_ARRAY = new ColorDescriptor[]{};
    @NonNls
    private static final String SAMPLE = extractIdeaScript();

    private final Set<AttributesDescriptor> attributeDescriptors = new HashSet<>();

    public BatchColorPage() {
        attributeDescriptors.add(new AttributesDescriptor("Brackets", BatchHighlighterColors.BRACKETS));
        attributeDescriptors.add(new AttributesDescriptor("Braces", BatchHighlighterColors.BRACES));
        attributeDescriptors.add(new AttributesDescriptor("Parenthesis", BatchHighlighterColors.PARENTHS));
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

    private static String extractIdeaScript() {
        String binPath = PathManager.getBinPath();
        try {
            File file = new File(binPath, "idea.bat");

            InputStreamReader streamReader;
            if (file.exists()) {
                streamReader = new InputStreamReader(new FileInputStream(file));
            } else {
                streamReader = new InputStreamReader(Reflection.getCallerClass(1).getResourceAsStream("/examples/demo.bat"));
            }

            return FileUtil.loadTextAndClose(streamReader).replaceAll("\\r", "");
        } catch (IOException e) {
            Logger.getInstance(BatchColorPage.class.getName()).error(e);
        }
        return "";
    }

    @Nullable
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    public AttributesDescriptor[] getAttributeDescriptors() {
        return attributeDescriptors.toArray(new AttributesDescriptor[0]);
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

    @NotNull
    public String getDisplayName() {
        return "Batch Script";
    }

    @NotNull
    public SyntaxHighlighter getHighlighter() {
        return new BatchSyntaxHighlighter();
    }

    @Nullable
    public Icon getIcon() {
        return BatchIcons.BATCH_FILE_ICON;
    }

}
