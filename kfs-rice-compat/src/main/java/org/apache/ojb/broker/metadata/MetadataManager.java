package org.apache.ojb.broker.metadata;
public class MetadataManager {
    private static MetadataManager instance = new MetadataManager();
    public static MetadataManager getInstance() { return instance; }
    public DescriptorRepository getGlobalRepository() { return new DescriptorRepository(); }
    public DescriptorRepository getRepository() { return new DescriptorRepository(); }
}
