load(
    "@com_googlesource_gerrit_bazlets//:gerrit_plugin.bzl",
    "gerrit_plugin",
    "gerrit_plugin_tests",
)
load("@rules_java//java:defs.bzl", "java_library")

PLUGIN = "aws-dynamodb-refdb"

EXT_DEPS = [
    "com.amazonaws:aws-java-sdk-core",
    "com.amazonaws:aws-java-sdk-dynamodb",
    "com.amazonaws:dynamodb-lock-client",
    "software.amazon.awssdk:regions",
]

TEST_EXT_DEPS = EXT_DEPS + [
    "org.testcontainers:localstack",
    "org.testcontainers:testcontainers",
]

gerrit_plugin(
    srcs = glob(["src/main/java/**/*.java"]),
    ext_deps = EXT_DEPS,
    manifest_entries = [
        "Gerrit-PluginName: " + PLUGIN,
        "Gerrit-Module: com.gerritforge.gerrit.plugins.validation.dfsrefdb.dynamodb.Module",
        "Implementation-Title: AWS DynamoDB shared ref-database implementation",
        "Implementation-URL: https://github.com/GerritForge/aws-dynamodb-refdb",
    ],
    plugin = PLUGIN,
    resources = glob(["src/main/resources/**/*"]),
    deps = [":global-refdb-neverlink"],
)

gerrit_plugin_tests(
    timeout = "long",
    srcs = glob(["src/test/java/**/*.java"]),
    ext_deps = TEST_EXT_DEPS,
    plugin = PLUGIN,
    deps = ["//plugins/global-refdb"],
)

java_library(
    name = "global-refdb-neverlink",
    neverlink = True,
    exports = ["//plugins/global-refdb"],
)
