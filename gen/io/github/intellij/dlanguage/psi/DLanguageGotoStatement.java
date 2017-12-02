package io.github.intellij.dlanguage.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;


public interface DLanguageGotoStatement extends PsiElement {

    @Nullable
    public DLanguageIdentifier getIdentifier();

    @Nullable
    public DLanguageExpression getExpression();

    @Nullable
    public PsiElement getKW_DEFAULT();

    @Nullable
    public PsiElement getKW_CASE();

    @Nullable
    public PsiElement getKW_GOTO();

    @Nullable
    public PsiElement getOP_SCOLON();

}
