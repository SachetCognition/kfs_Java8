/**
 * KFS Rice Adapter Package.
 *
 * <p>This package contains KFS-owned interfaces and adapter classes that wrap
 * Kuali Rice services and constants. The goal is to minimize direct coupling
 * between KFS application code and the Rice framework, enabling future
 * decoupling from Rice without touching every KFS source file.</p>
 *
 * <h3>Migration Pattern</h3>
 * <ul>
 *   <li>For Rice constants (KRADConstants, KewApiConstants, KimConstants):
 *       Use the KFS adapter classes (KfsRiceConstants, KfsKewConstants, KfsKimConstants)
 *       which delegate to the original Rice constants.</li>
 *   <li>For Rice services (BusinessObjectService, DocumentService, etc.):
 *       Use the KFS adapter interfaces and inject the adapter implementations
 *       (e.g., KfsBusinessObjectService instead of BusinessObjectService).</li>
 *   <li>For Rice utilities (ObjectUtils, GlobalVariables):
 *       Use KfsObjectUtils and KfsGlobalVariables which delegate to Rice.</li>
 * </ul>
 *
 * <h3>Spring Wiring</h3>
 * <p>Adapter implementations are wired in
 * {@code spring-kfs-rice-adapters.xml} and delegate to the underlying
 * Rice service beans imported via {@code spring-kfs-imported-rice-beans.xml}.</p>
 */
package org.kuali.kfs.sys.rice;
