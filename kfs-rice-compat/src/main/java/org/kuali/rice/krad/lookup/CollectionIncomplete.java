package org.kuali.rice.krad.lookup;
import java.util.ArrayList;
import java.util.Collection;
public class CollectionIncomplete<T> extends ArrayList<T> {
    private Long actualSizeIfTruncated;
    public CollectionIncomplete() { super(); }
    public CollectionIncomplete(Collection<? extends T> c, Long actualSize) { super(c); this.actualSizeIfTruncated = actualSize; }
    public Long getActualSizeIfTruncated() { return actualSizeIfTruncated; }
    public void setActualSizeIfTruncated(Long actualSizeIfTruncated) { this.actualSizeIfTruncated = actualSizeIfTruncated; }
}
