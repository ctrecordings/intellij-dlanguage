package io.github.intellij.dlanguage.psi.impl;

import static io.github.intellij.dlanguage.psi.DlangTypes.OP_COMMA;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import io.github.intellij.dlanguage.psi.DLanguageForeachType;
import io.github.intellij.dlanguage.psi.DLanguageForeachTypeList;
import java.util.List;
import org.jetbrains.annotations.NotNull;


public class DLanguageForeachTypeListImpl extends ASTWrapperPsiElement implements
    DLanguageForeachTypeList {

    public DLanguageForeachTypeListImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull DLanguageVisitor visitor) {
        visitor.visitForeachTypeList(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof DLanguageVisitor) {
            accept((DLanguageVisitor) visitor);
        } else {
            super.accept(visitor);
        }
    }

    @NotNull
    public List<DLanguageForeachType> getForeachTypes() {
        return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageForeachType.class);
    }

    @NotNull
    public List<PsiElement> getOP_COMMAs() {
        return findChildrenByType(OP_COMMA);
    }

}
