package com.agileengine.elementfinder;

import org.apache.commons.text.similarity.FuzzyScore;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.*;
import java.util.function.Consumer;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparingDouble;

public class ElementFinder {
    private static final Set<String> FUZZY_MATCH_ATTRS = caseInsensitiveSet("class", "onclick");
    private FuzzyScore fuzzyScore = new FuzzyScore(Locale.ENGLISH);

    public Optional<ComparisonResult> findBestMatch(Element target, Document document) {
        return findBestMatch(target, document, r -> {});
    }

    public Optional<ComparisonResult> findBestMatch(Element target, Document document, Consumer<ComparisonResult> peeker) {
        List<Element> searchPool = searchPool(target, document);

        return searchPool.stream()
                .map(candidate -> compare(target, candidate))
                .peek(peeker)
                .max(comparingDouble(ComparisonResult::getScore));
                //.map(ComparisonResult::getElement);
    }

    private ComparisonResult compare(Element target, Element candidate) {
        ComparisonResult result = new ComparisonResult(candidate);

        target.attributes().forEach(attribute -> {
            String attrKey = attribute.getKey();
            String expectedValue = attribute.getValue();

            if (candidate.hasAttr(attrKey)) {
                String attrValue = candidate.attr(attrKey);

                if (FUZZY_MATCH_ATTRS.contains(attrKey)) {
                    double score = normalizedFuzzyScore(attrValue, expectedValue);
                    result.reportScore(attrKey + " (fuzzy match)", score);
                } else {
                    result.reportScore(attrKey, attrValue.equals(expectedValue) ? 1d : 0d);
                }
            }

            if (target.text() != null && candidate.text() != null) {
                result.reportScore("Text (fuzzy match)", normalizedFuzzyScore(candidate.text(), target.text()));
            }
        });

        return result;
    }

    private double normalizedFuzzyScore(String attrValue, String expectedValue) {
        return fuzzyScore.fuzzyScore(attrValue, expectedValue).doubleValue() / fuzzyScore.fuzzyScore(expectedValue, expectedValue).doubleValue();
    }

    // This could be extracted to an interface, so that different strategies to narrow down the group of
    // possible matches could be easily plugged into the main algorithm.
    private List<Element> searchPool(Element target, Document document) {
        // This strategy assumes the element we are looking for has the same tag as the original
        // (i.e. if original is <a> then let's only look at <a> elements for a match)
        String tag = target.tagName();
        return document.select(tag);
    }

    private static Set<String> caseInsensitiveSet(String... elems) {
        TreeSet<String> set = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        set.addAll(asList(elems));
        return set;
    }

    // Ideally this would be immutable and have a builder :)
    public static class ComparisonResult {
        private final Element element;
        private final Map<String, Double> components = new HashMap<>();

        private ComparisonResult(Element element) {
            this.element = element;
        }

        public double getScore() {
            return components.values()
                    .stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();
        }

        public Element getElement() {
            return element;
        }

        private void reportScore(String name, Double value) {
            components.put(name, value);
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder("Total score:");
            builder.append(getScore());
            builder.append(" candidate: ");
            builder.append(element);
            builder.append("\n");
            components.forEach((k,v) -> {
                builder.append("\t");
                builder.append("criteria: ");
                builder.append(k);
                builder.append(" score: ");
                builder.append(v);
                builder.append("\n");
            });

            return builder.toString();
        }
    }
}
