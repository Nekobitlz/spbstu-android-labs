package name.ank.lab4;


import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class BibDatabaseTest {

    private BibDatabase openDatabase(String s) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(s))) {
            return new BibDatabase(reader);
        }
    }

    private BibDatabase openDatabase(String s, BibConfig cfg) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(s))) {
            return new BibDatabase(reader, cfg);
        }
    }

    @Test
    public void getFirstEntry() throws IOException {
        BibDatabase database = openDatabase("/references.bib");
        BibEntry first = database.getEntry(0);
        Assert.assertEquals(Types.ARTICLE, first.getType());
        Assert.assertEquals("The semantic web", first.getField(Keys.TITLE));
        Assert.assertNull("Field 'chapter' does not exist", first.getField(Keys.CHAPTER));
    }

    @Test
    public void normalModeDoesNotThrowException() throws IOException {
        BibDatabase database = openDatabase("/mixed.bib");
        BibConfig cfg = database.getCfg();
        cfg.strict = false;

        BibEntry first = database.getEntry(0);
        for (int i = 0; i < cfg.maxValid + 1; i++) {
            BibEntry unused = database.getEntry(0);
            assertNotNull("Should not throw any exception @" + i, first.getType());
        }
    }

    @Test
    public void canReadAllItemsFromMixed() throws IOException {
        BibDatabase database = openDatabase("/mixed.bib");

        for (int i = 0; i < database.size(); i++) {
            BibEntry entry = database.getEntry(i);
            assertNotNull(entry.getType());
        }
    }

    @Test(expected = IllegalStateException.class)
    public void strictModeThrowsException() throws IOException {
        BibDatabase database = openDatabase("/mixed.bib");
        BibConfig cfg = database.getCfg();
        cfg.strict = true;

        BibEntry first = database.getEntry(0);
        for (int i = 0; i < cfg.maxValid - 1; i++) {
            BibEntry unused = database.getEntry(0);
            Assert.assertNotNull("Should not throw any exception @" + i, first.getType());
        }

        BibEntry unused = database.getEntry(0);
        first.getType();
    }

    @Test
    public void shuffleFlag() throws IOException {
        BibDatabase database = openDatabase("/mixed.bib");

        BibConfig cfg = new BibConfig();
        cfg.shuffle = true;

        for (int count = 0; count < 100; count++) {
            BibDatabase shuffledDatabase = openDatabase("/mixed.bib", cfg);
            for (int i = 0; i < database.size(); i++) {
                if (!database.getEntry(i).getType().equals(shuffledDatabase.getEntry(i).getType())) {
                    return;
                }
            }
        }

        Assert.fail("No differences were found between databases.");
    }
}
