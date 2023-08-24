public class AvoidUnoptimizedVectorImages {
    private static final String UNOPTIMIZED_SVG = "<svg><g>...</g></g></svg>"; // Noncompliant
    private static final String MULTIPLE_LAYERS_SVG = "<svg><g>...</g><g>...</g></svg>"; // Noncompliant
    private static final String COMMENTS_SVG = "<svg><!-- ... --></svg>";
    private static final String NAMESPACE_SVG = "<svg xmlns:custom=\"...\">...</svg>"; // Noncompliant
    private static final String METADATA_SVG = "<svg><metadata>...</metadata></svg>"; // Noncompliant

    public void testMethod() {
        String unoptimizedImage = UNOPTIMIZED_SVG;
        String multipleLayersImage = MULTIPLE_LAYERS_SVG;
        String commentsImage = COMMENTS_SVG;
        String namespaceImage = NAMESPACE_SVG;
        String metadataImage = METADATA_SVG;
    }
}
