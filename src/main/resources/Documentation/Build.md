# Build

The aws-dynamodb-refdb plugin can be build as a regular 'in-tree' plugin. That means
that is required to clone a Gerrit source tree first and then to have the plugin
source directory into the `/plugins` path.

Additionally, the `plugins/external_plugin_deps.bzl` file needs to be updated to
match the aws-dynamodb-refdb plugin one.

```shell script
git clone --recursive https://gerrit.googlesource.com/gerrit
cd gerrit
git clone "https://review.gerrithub.io/GerritForge/aws-dynamodb-refdb" plugins/aws-dynamodb-refdb
ln -sf plugins/aws-dynamodb-refdb/external_plugin_deps.bzl plugins/.
bazelisk build plugins/aws-dynamodb-refdb
```

The output is created in

```
bazel-genfiles/plugins/aws-dynamodb-refdb/aws-dynamodb-refdb.jar
```