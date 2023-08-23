
class AvoidUnoptimizedVectorImages {
    AvoidUnoptimizedVectorImages(AvoidUnoptimizedVectorImages mc) {
    }

    private String validSvg = "<svg xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "    <circle cx=\"50\" cy=\"50\" r=\"40\"/>\n" +
            "</svg>";

    private String unoptimizedSvgWithComments = "<svg xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "    <!-- This is a comment -->\n" +
            "    <circle cx=\"50\" cy=\"50\" r=\"40\"/>\n" + // Noncompliant
            "</svg>";

    private String unoptimizedSvgWithMultipleLayers = "<svg xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "    <g></g>\n" +
            "    <g></g>\n" +
            "</svg>";

    private String unoptimizedSvgWithWrongNamespace = "<svg xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "    <circle xmlns:wrong=\"http://www.wrong-namespace.com\" cx=\"50\" cy=\"50\" r=\"40\"/>\n" +
            "</svg>";

    private String unoptimizedSvgWithMetadata = "<svg xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "    <metadata></metadata>\n" +
            "</svg>";

    private String unoptimizedSvgWithoutClosingSvgTag = "<svg xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "    <circle cx=\"50\" cy=\"50\" r=\"40\"/>\n"; // Noncompliant

}