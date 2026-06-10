package org.kuali.rice.kns.document.authorization;
import java.util.Set;
import java.util.HashSet;
public interface DocumentPresentationController {
    boolean canRecall(org.kuali.rice.krad.document.Document document);
    default Set<String> getDocumentActions(org.kuali.rice.krad.document.Document document) { return new HashSet<>(); }
    default Set<String> getEditModes(org.kuali.rice.krad.document.Document document) { return new HashSet<>(); }
    default boolean canEdit(org.kuali.rice.krad.document.Document document) { return true; }
    default boolean canCopy(org.kuali.rice.krad.document.Document document) { return true; }
}
