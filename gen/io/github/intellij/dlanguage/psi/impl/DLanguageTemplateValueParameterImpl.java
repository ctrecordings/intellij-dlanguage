package io.github.intellij.dlanguage.psi.impl;

import static io.github.intellij.dlanguage.psi.DlangTypes.OP_COLON;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import io.github.intellij.dlanguage.psi.DLanguageAssignExpression;
import io.github.intellij.dlanguage.psi.DLanguageTemplateValueParameter;
import io.github.intellij.dlanguage.psi.DLanguageTemplateValueParameterDefault;
import io.github.intellij.dlanguage.psi.DLanguageType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class DLanguageTemplateValueParameterImpl extends ASTWrapperPsiElement implements
    DLanguageTemplateValueParameter {

    public DLanguageTemplateValueParameterImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull DLanguageVisitor visitor) {
        visitor.visitTemplateValueParameter(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof DLanguageVisitor) {
            accept((DLanguageVisitor) visitor);
        } else {
            super.accept(visitor);
        }
    }

    @Nullable
    public DLanguageType getType() {
        return PsiTreeUtil.getChildOfType(this, DLanguageType.class);
    }

    @Nullable
    public DLanguageIdentifier getIdentifier() {
        return PsiTreeUtil.getChildOfType(this, DLanguageIdentifier.class);
    }

    @Nullable
    public PsiElement getOP_COLON() {
        return findChildByType(OP_COLON);
    }

    @Nullable
    public DLanguageAssignExpression getAssignExpression() {
        return PsiTreeUtil.getChildOfType(this, DLanguageAssignExpression.class);
    }

    @Nullable
    public DLanguageTemplateValueParameterDefault getTemplateValueParameterDefault() {
        return PsiTreeUtil.getChildOfType(this, DLanguageTemplateValueParameterDefault.class);
    }
}
