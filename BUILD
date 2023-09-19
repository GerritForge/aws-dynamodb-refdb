load("//tools/bzl:junit.bzl", "junit_tests")
load(
    "//tools/bzl:plugin.bzl",
    "PLUGIN_DEPS",
    "PLUGIN_TEST_DEPS",
    "gerrit_plugin",
)

gerrit_plugin(
    name = "aws-dynamodb-refdb",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: aws-dynamodb-refdb",
        "Gerrit-Module: com.googlesource.gerrit.plugins.validation.dfsrefdb.dynamodb.Module",
        "Implementation-Title: AWS DynamoDB shared ref-database implementation",
        "Implementation-URL: https://gerrit-review.googlesource.com/#/admin/projects/plugins/aws-dynamodb-refdb",
    ],
    resources = glob(["src/main/resources/**/*"]),
    deps = [
        ":global-refdb-neverlink",
        "@amazon-aws-core//jar",
        "@amazon-dynamodb//jar",
        "@amazon-regions//jar",
        "@amazon-sdk-core//jar",
        "@amazon-utils//jar",
        "@aws-java-sdk-core//jar",
        "@aws-java-sdk-dynamodb//jar",
        "@dynamodb-lock-client//jar",
        "@jackson-annotations//jar",
        "@jackson-databind//jar",
        "@jackson-dataformat-cbor//jar",
        "@joda-time//jar",
    ],
)

java_library(
    name = "global-refdb-neverlink",
    neverlink = 1,
    exports = ["//plugins/global-refdb"],
)

junit_tests(
    name = "aws-dynamodb-refdb_tests",
    srcs = glob(["src/test/java/**/*.java"]),
    resources = glob(["src/test/resources/**/*"]),
    tags = ["aws-dynamodb-refdb"],
    deps = [
        ":aws-dynamodb-refdb__plugin_test_deps",
    ],
)

java_library(
    name = "aws-dynamodb-refdb__plugin_test_deps",
    testonly = 1,
    visibility = ["//visibility:public"],
    exports = PLUGIN_DEPS + PLUGIN_TEST_DEPS + [
        ":aws-dynamodb-refdb__plugin",
        "//plugins/global-refdb",
        "@amazon-regions//jar",
        "//lib/testcontainers",
        "//lib/testcontainers:docker-java-api",
        "//lib/testcontainers:docker-java-transport",
        "@testcontainer-localstack//jar",
        "@aws-java-sdk-dynamodb//jar",
    ],
)
