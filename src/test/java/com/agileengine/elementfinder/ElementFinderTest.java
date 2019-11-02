package com.agileengine.elementfinder;

import com.agileengine.elementfinder.ElementFinder.ComparisonResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static com.agileengine.snippet.jsoup.JsoupFindByIdSnippet.findElementById;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ElementFinderTest {
    private static final String ORIGINAL_ID = "make-everything-ok-button";
    private static final String ORIGINAL_SAMPLE_PATH = "./samples/sample-0-origin.html";

    private final ElementFinder elementFinder = new ElementFinder();

    @Test
    public void testEvilGemini() throws IOException {
        Element original = getOriginalElement();
        Document document = sampleDocument("./samples/sample-1-evil-gemini.html");

        Optional<ComparisonResult> found = elementFinder.findBestMatch(original, document);

        assertThat(found.isPresent(), is(true));
        assertThat(found.get().getElement().toString(), is("<a class=\"btn btn-success\" href=\"#check-and-ok\" title=\"Make-Button\" rel=\"done\" onclick=\"javascript:window.okDone(); return false;\"> Make everything OK </a>"));
    }

    @Test
    public void testContainerAndClone() throws IOException {
        Element original = getOriginalElement();
        Document document = sampleDocument("./samples/sample-2-container-and-clone.html");

        Optional<ComparisonResult> found = elementFinder.findBestMatch(original, document);

        assertThat(found.isPresent(), is(true));
        assertThat(found.get().getElement().toString(), is("<a class=\"btn test-link-ok\" href=\"#ok\" title=\"Make-Button\" rel=\"next\" onclick=\"javascript:window.okComplete(); return false;\"> Make everything OK </a>"));
    }

    @Test
    public void testTheEscape() throws IOException {
        Element original = getOriginalElement();
        Document document = sampleDocument("./samples/sample-3-the-escape.html");

        Optional<ComparisonResult> found = elementFinder.findBestMatch(original, document);

        assertThat(found.isPresent(), is(true));
        assertThat(found.get().getElement().toString(), is("<a class=\"btn btn-success\" href=\"#ok\" title=\"Do-Link\" rel=\"next\" onclick=\"javascript:window.okDone(); return false;\"> Do anything perfect </a>"));
    }

    @Test
    public void testTheMash() throws IOException {
        Element original = getOriginalElement();
        Document document = sampleDocument("./samples/sample-4-the-mash.html");

        Optional<ComparisonResult> found = elementFinder.findBestMatch(original, document);

        assertThat(found.isPresent(), is(true));
        assertThat(found.get().getElement().toString(), is("<a class=\"btn btn-success\" href=\"#ok\" title=\"Make-Button\" rel=\"next\" onclick=\"javascript:window.okFinalize(); return false;\"> Do all GREAT </a>"));

    }

    private Element getOriginalElement() {
        File sampleFile = new File(ORIGINAL_SAMPLE_PATH);
        return findElementById(sampleFile, ORIGINAL_ID).get();
    }

    private Document sampleDocument(String path) throws IOException {
        File htmlFile = new File(path);

        return Jsoup.parse(
                htmlFile,
                UTF_8.name(),
                htmlFile.getAbsolutePath());
    }
}
