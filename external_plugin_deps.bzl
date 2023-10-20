load("//tools/bzl:maven_jar.bzl", "maven_jar")

AWS_SDK_VER = "2.16.19"
AWS_KINESIS_VER = "2.3.4"
JACKSON_VER = "2.10.4"
DOCKER_JAVA_VERS = "3.2.8"

def external_plugin_deps():
    maven_jar(
        name = "amazon-dynamodb",
        artifact = "software.amazon.awssdk:dynamodb:" + AWS_SDK_VER,
        sha1 = "33ec7d291973658779b5777db2a0214a5c469e81",
    )

    maven_jar(
        name = "aws-java-sdk-dynamodb",
        artifact = "com.amazonaws:aws-java-sdk-dynamodb:1.11.1006",
        sha1 = "dd2c9dff101ae8dad26197f7b09a06d4e13965ca",
    )

    maven_jar(
        name = "dynamodb-lock-client",
        artifact = "com.amazonaws:dynamodb-lock-client:1.1.0",
        sha1 = "3aadced3599f3b2fd058bc75d48dde374f66e544",
    )

    maven_jar(
        name = "amazon-regions",
        artifact = "software.amazon.awssdk:regions:" + AWS_SDK_VER,
        sha1 = "089f4f3d3ef20b2486f09e71da638c03100eab64",
    )

    maven_jar(
        name = "amazon-sdk-core",
        artifact = "software.amazon.awssdk:sdk-core:" + AWS_SDK_VER,
        sha1 = "02a60fd9c138048272ef8b6c80ae67491dd386a9",
    )

    maven_jar(
        name = "amazon-aws-core",
        artifact = "software.amazon.awssdk:aws-core:" + AWS_SDK_VER,
        sha1 = "0f50f5cf2698a0de7d2d77322cbf3fb13f76187f",
    )

    maven_jar(
        name = "amazon-utils",
        artifact = "software.amazon.awssdk:utils:" + AWS_SDK_VER,
        sha1 = "53edaa1f884682ac3091293eff3eb024ed0e36bb",
    )

    maven_jar(
        name = "aws-java-sdk-core",
        artifact = "com.amazonaws:aws-java-sdk-core:1.11.960",
        sha1 = "18b6b2a5cb83a0e2e33a593302b5dbe0ca2ade64",
    )

    maven_jar(
        name = "jackson-databind",
        artifact = "com.fasterxml.jackson.core:jackson-databind:" + JACKSON_VER,
        sha1 = "76e9152e93d4cf052f93a64596f633ba5b1c8ed9",
    )

    maven_jar(
        name = "jackson-dataformat-cbor",
        artifact = "com.fasterxml.jackson.dataformat:jackson-dataformat-cbor:" + JACKSON_VER,
        sha1 = "c854bb2d46138198cb5d4aae86ef6c04b8bc1e70",
    )

    maven_jar(
        name = "jackson-annotations",
        artifact = "com.fasterxml.jackson.core:jackson-annotations:" + JACKSON_VER,
        sha1 = "6ae6028aff033f194c9710ad87c224ccaadeed6c",
    )

    maven_jar(
        name = "jackson-core",
        artifact = "com.fasterxml.jackson.core:jackson-core:" + JACKSON_VER,
        sha1 = "8796585e716440d6dd5128b30359932a9eb74d0d",
    )

    maven_jar(
        name = "joda-time",
        artifact = "joda-time:joda-time:2.10.10",
        sha1 = "29e8126e31f41e5c12b9fe3a7eb02e704c47d70b",
    )

    maven_jar(
        name = "testcontainer-localstack",
        artifact = "org.testcontainers:localstack:1.15.2",
        sha1 = "ae3c4717bc5f37410abbb490cb46d349a77990a0",
    )

    maven_jar(
        name = "testcontainers",
        artifact = "org.testcontainers:testcontainers:1.15.3",
        sha1 = "95c6cfde71c2209f0c29cb14e432471e0b111880",
    )

    maven_jar(
        name = "docker-java-api",
        artifact = "com.github.docker-java:docker-java-api:" + DOCKER_JAVA_VERS,
        sha1 = "4ac22a72d546a9f3523cd4b5fabffa77c4a6ec7c",
    )

    maven_jar(
        name = "docker-java-transport",
        artifact = "com.github.docker-java:docker-java-transport:" + DOCKER_JAVA_VERS,
        sha1 = "c3b5598c67d0a5e2e780bf48f520da26b9915eab",
    )

    maven_jar(
        name = "duct-tape",
        artifact = "org.rnorth.duct-tape:duct-tape:1.0.8",
        sha1 = "92edc22a9ab2f3e17c9bf700aaee377d50e8b530",
    )

    maven_jar(
        name = "jna",
        artifact = "net.java.dev.jna:jna:5.5.0",
        sha1 = "0e0845217c4907822403912ad6828d8e0b256208",
    )
