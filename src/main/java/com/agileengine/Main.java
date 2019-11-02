package com.agileengine;

import com.agileengine.elementfinder.ElementFinder;
import org.apache.log4j.BasicConfigurator;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Stack;

import static com.agileengine.snippet.jsoup.JsoupFindByIdSnippet.findElementById;
import static com.agileengine.snippet.jsoup.JsoupFindByIdSnippet.parse;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure();

        if (args.length < 2 || args.length > 3) {
            logger.warn("java -cp ElementFinder.jar <input_origin_file_path> <input_other_sample_file_path> [element_id]\n" +
                    "Where:\n" +
                    "<input_origin_file_path> - origin sample path to find the desired element and collect all the required information;\n" +
                    "<input_other_sample_file_path> - path to diff-case HTML file to search a similar element;" +
                    "[element_id] - Optional id of the element to find, default is make-everything-ok-button;");
        }

        File originalFile = new File(args[0]);
        File diffFile = new File(args[1]);
        String id = args.length == 3 ? args[2] : "make-everything-ok-button";

        Optional<Element> needleOpt = findElementById(originalFile, id);

        if (!needleOpt.isPresent()) {
            logger.warn("No element with id={} found in file={}", id, originalFile.getPath());
            return;
        }

        Element needle = needleOpt.get();
        Document haystack = parse(diffFile);

        logger.info("Looking for elements similar to {} \nin file={}\nReporting analysis results...", needle, diffFile.getPath());

        ElementFinder elementFinder = new ElementFinder();

        String output = elementFinder
                        .findBestMatch(
                                needle,
                                haystack,
                                comparisonResult -> {
                                    if (comparisonResult.getScore() > 0.5)
                                        logger.info(comparisonResult.toString());
                                })
                        .map(result -> "Highest scoring match -> Score: " + result.getScore() + " path: " + getPath(result.getElement()))
                        .orElse("No matches found!");

        logger.info(output);
    }

    private static String getPath(Element element) {
        Stack<String> stack = new Stack<>();

        for (Element current = element; current != null; current = current.parent()) {
            StringBuilder sb = new StringBuilder(current.tagName());
            sb.append(current.siblingIndex() != 0  && current.siblingIndex() != 1 ? "[" + current.siblingIndex() + "]" : "");
            stack.push(sb.toString());
        }

        StringBuilder path = new StringBuilder();
        while (!stack.empty()) {
            path.append(stack.pop());
            path.append(stack.empty() ? "" : " > ");
        }

        return path.toString();
    }
}
