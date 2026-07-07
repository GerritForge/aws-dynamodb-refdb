load(
    "@com_googlesource_gerrit_bazlets//:gerrit_plugin.bzl",
    "gerrit_plugin",
    "gerrit_plugin_tests",
)
load("@rules_java//java:defs.bzl", "java_library")

gerrit_plugin(
    name = "aws-dynamodb-refdb",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: aws-dynamodb-refdb",
        "Gerrit-Module: com.gerritforge.gerrit.plugins.validation.dfsrefdb.dynamodb.Module",
        "Gerrit-HttpModule: com.gerritforge.gerrit.plugins.bsl.HttpModule",
        "Implementation-Title: AWS DynamoDB shared ref-database implementation",
        "Implementation-URL: https://github.com/GerritForge/aws-dynamodb-refdb",
    ],
    resources = glob(["src/main/resources/**/*"]),
    deps = [
        ":global-refdb-neverlink",
        "//plugins/gerrit-bsl-license",
        "@aws_dynamodb_refdb_plugin_deps//:com_amazonaws_aws_java_sdk_core",
        "@aws_dynamodb_refdb_plugin_deps//:com_amazonaws_aws_java_sdk_dynamodb",
        "@aws_dynamodb_refdb_plugin_deps//:com_amazonaws_aws_java_sdk_sts",
        "@aws_dynamodb_refdb_plugin_deps//:com_amazonaws_dynamodb_lock_client",
        "@aws_dynamodb_refdb_plugin_deps//:software_amazon_awssdk_regions",
    ],
)

gerrit_plugin_tests(
    name = "aws-dynamodb-refdb_tests",
    timeout = "long",
    srcs = glob(["src/test/java/**/*.java"]),
    plugin = "aws-dynamodb-refdb",
    tags = ["aws-dynamodb-refdb"],
    deps = [
        "//plugins/global-refdb",
        "@aws_dynamodb_refdb_plugin_deps//:com_amazonaws_aws_java_sdk_dynamodb",
        "@aws_dynamodb_refdb_plugin_deps//:org_testcontainers_localstack",
        "@aws_dynamodb_refdb_plugin_deps//:org_testcontainers_testcontainers",
        "@aws_dynamodb_refdb_plugin_deps//:software_amazon_awssdk_regions",
    ],
)

java_library(
    name = "global-refdb-neverlink",
    neverlink = 1,
    exports = ["//plugins/global-refdb"],
)
