Configuration
=========================

The dynamodb-refdb plugin is configured by adding a plugin stanza in the
`gerrit.config` file, for example:

```text
[plugin "aws-dynamodb-refdb"]
    region = us-east-1
    endpoint = http://localhost:4566
    locksTableName = lockTable
    refsDbTableName = refsDb
    profileName = aws-dynamodb-refdb
```

`plugin.aws-dynamodb-refdb.region`
:   Optional. Which AWS region to connect to.
Default: When not specified this value is provided via the default Region
Provider Chain, as explained [here](https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html)

`plugin.aws-dynamodb-refdb.endpoint`
:   Optional. When defined, it will override the default dynamodb endpoint
will connect to it, rather than connecting to AWS. This is useful when
developing or testing, in order to connect locally.
See [localstack](https://github.com/localstack/localstack) to understand
more about how run dynamodb stack outside AWS.
Default: <empty>

`plugin.aws-dynamodb-refdb.locksTableName`
:   Optional. The name of the dynamoDB table used to store distribute locking
See [DynamoDB lock client](https://github.com/awslabs/amazon-dynamodb-lock-client)

`plugin.aws-dynamodb-refdb.locksTableName`
:   Optional. The name of the dynamoDB table used to store git refs and their
associated sha1.

`plugin.aws-dynamodb-refdb.profileName`
:   Optional. The name of the aws configuration and credentials profile used to
connect to the DynamoDb. See [Configuration and credential file settings](https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-files.html)
Default: When not specified credentials are provided via the Default Credentials
Provider Chain, as explained [here](https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html)

### Project Deletion

This plugin introduces a caching mechanism to reduce the number of requests to
DynamoDB by storing the current live version of a project.

Checking the current live version of a project directly from DynamoDB can be
costly. To minimise the number of DynamoDb operations that are unlikely to
provide a different result, avoiding excessive charges for reading continuously
unchanged data, the plugin employs a cache with a time-to-live (TTL) of `60
seconds`.

> **IMPORTANT NOTE** When a project is deleted, it cannot be recreated with the
> same name for the subsequent 60 seconds, because of the of the _live version_
> caching mechanism described above.  Any attempt to recreate the same project
> with the same name *before 60 seconds* will result in a failure.

