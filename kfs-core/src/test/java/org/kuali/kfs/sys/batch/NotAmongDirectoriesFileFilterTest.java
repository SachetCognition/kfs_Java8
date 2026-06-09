package org.kuali.kfs.sys.batch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class NotAmongDirectoriesFileFilterTest extends KfsUnitTestBase {

    @TempDir
    File tempDir;

    private NotAmongDirectoriesFileFilter filter;
    private File avoidDir;
    private File otherDir;

    @BeforeEach
    void setUp() throws Exception {
        avoidDir = new File(tempDir, "avoid");
        avoidDir.mkdirs();
        otherDir = new File(tempDir, "other");
        otherDir.mkdirs();

        FilePurgeCustomAge customAge = new FilePurgeCustomAge();
        customAge.setDirectory(avoidDir.getAbsolutePath());

        List<FilePurgeCustomAge> customAges = new ArrayList<>();
        customAges.add(customAge);

        filter = new NotAmongDirectoriesFileFilter(customAges);
    }

    @Test
    void accept_fileInAvoidedDir_returnsFalse() throws Exception {
        File fileInAvoid = new File(avoidDir, "data.txt");
        fileInAvoid.createNewFile();
        assertThat(filter.accept(fileInAvoid)).isFalse();
    }

    @Test
    void accept_fileInOtherDir_returnsTrue() throws Exception {
        File fileInOther = new File(otherDir, "data.txt");
        fileInOther.createNewFile();
        assertThat(filter.accept(fileInOther)).isTrue();
    }

    @Test
    void accept_directory_alwaysReturnsTrue() {
        assertThat(filter.accept(avoidDir)).isTrue();
    }

    @Test
    void accept_withDirAndFilename_usesDirectoryName() {
        // accept(File, String) uses directory.getName() not full path
        // directoriesToAvoid has the full path, so getName() won't match
        assertThat(filter.accept(avoidDir, "data.txt")).isTrue();
    }

    @Test
    void accept_withDirAndFilename_matchesByName() {
        // When directoriesToAvoid has just the directory name (not full path)
        FilePurgeCustomAge age = new FilePurgeCustomAge();
        age.setDirectory("avoid");
        List<FilePurgeCustomAge> ages = new ArrayList<>();
        ages.add(age);
        NotAmongDirectoriesFileFilter nameFilter = new NotAmongDirectoriesFileFilter(ages);

        assertThat(nameFilter.accept(avoidDir, "data.txt")).isFalse();
    }

    @Test
    void accept_withDirAndFilename_otherDir_returnsTrue() {
        FilePurgeCustomAge age = new FilePurgeCustomAge();
        age.setDirectory("avoid");
        List<FilePurgeCustomAge> ages = new ArrayList<>();
        ages.add(age);
        NotAmongDirectoriesFileFilter nameFilter = new NotAmongDirectoriesFileFilter(ages);

        assertThat(nameFilter.accept(otherDir, "data.txt")).isTrue();
    }

    @Test
    void emptyCustomAges_acceptsAll() throws Exception {
        NotAmongDirectoriesFileFilter emptyFilter = new NotAmongDirectoriesFileFilter(new ArrayList<>());
        File f = new File(avoidDir, "file.txt");
        f.createNewFile();
        assertThat(emptyFilter.accept(f)).isTrue();
    }

    @Test
    void multipleAvoidedDirs() throws Exception {
        File avoidDir2 = new File(tempDir, "avoid2");
        avoidDir2.mkdirs();

        FilePurgeCustomAge ca1 = new FilePurgeCustomAge();
        ca1.setDirectory(avoidDir.getAbsolutePath());
        FilePurgeCustomAge ca2 = new FilePurgeCustomAge();
        ca2.setDirectory(avoidDir2.getAbsolutePath());

        List<FilePurgeCustomAge> ages = new ArrayList<>();
        ages.add(ca1);
        ages.add(ca2);

        NotAmongDirectoriesFileFilter multiFilter = new NotAmongDirectoriesFileFilter(ages);

        File f1 = new File(avoidDir, "a.txt");
        f1.createNewFile();
        File f2 = new File(avoidDir2, "b.txt");
        f2.createNewFile();
        File f3 = new File(otherDir, "c.txt");
        f3.createNewFile();

        assertThat(multiFilter.accept(f1)).isFalse();
        assertThat(multiFilter.accept(f2)).isFalse();
        assertThat(multiFilter.accept(f3)).isTrue();
    }
}
