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
        "Implementation-Title: AWS DynamoDB shared ref-database implementation",
        "Implementation-URL: https://github.com/GerritForge/aws-dynamodb-refdb",
    ],
    resources = glob(["src/main/resources/**/*"]),
    deps = [
        ":global-refdb-neverlink",
        "@aws_dynamodb_refdb_plugin_deps//:com_amazonaws_aws_java_sdk_core",
        "@aws_dynamodb_refdb_plugin_deps//:com_amazonaws_aws_java_sdk_dynamodb",
        "@aws_dynamodb_refdb_plugin_deps//:com_amazonaws_dynamodb_lock_client",
        "@aws_dynamodb_refdb_plugin_deps//:com_fasterxml_jackson_core_jackson_annotations",
        "@aws_dynamodb_refdb_plugin_deps//:com_fasterxml_jackson_core_jackson_core",
        "@aws_dynamodb_refdb_plugin_deps//:com_fasterxml_jackson_core_jackson_databind",
        "@aws_dynamodb_refdb_plugin_deps//:com_fasterxml_jackson_dataformat_jackson_dataformat_cbor",
        "@aws_dynamodb_refdb_plugin_deps//:joda_time_joda_time",
        "@aws_dynamodb_refdb_plugin_deps//:software_amazon_awssdk_aws_core",
        "@aws_dynamodb_refdb_plugin_deps//:software_amazon_awssdk_dynamodb",
        "@aws_dynamodb_refdb_plugin_deps//:software_amazon_awssdk_regions",
        "@aws_dynamodb_refdb_plugin_deps//:software_amazon_awssdk_sdk_core",
        "@aws_dynamodb_refdb_plugin_deps//:software_amazon_awssdk_utils",
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
        "@aws_dynamodb_refdb_plugin_deps//:com_github_docker_java_docker_java_api",
        "@aws_dynamodb_refdb_plugin_deps//:com_github_docker_java_docker_java_transport",
        "@aws_dynamodb_refdb_plugin_deps//:net_java_dev_jna_jna",
        "@aws_dynamodb_refdb_plugin_deps//:org_rnorth_duct_tape_duct_tape",
        "@aws_dynamodb_refdb_plugin_deps//:org_rnorth_visible_assertions_visible_assertions",
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
