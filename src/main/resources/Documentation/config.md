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

### Project Version Cache

This plugin introduces a caching mechanism to reduce the number of requests to DynamoDB by storing
the current live version of a project.

When a project is deleted, the plugin increments a pointer in DynamoDB representing the current live
version of the project. This ensures that if a new project with the same name is created, only
references belonging to the current version are checked for consistency, while references to
previous versions are ignored.

Checking the current live version of a project directly from DynamoDB can be costly, particularly
since project deletions are relatively rare. To optimize performance, the plugin employs a cache
with a configurable time-to-live (TTL).

For the duration of the cache's TTL, the project version is served directly from the cache,
minimizing the number of reads from DynamoDB.

*This implies an important restriction*: If a project is deleted, it cannot be recreated until the
cache TTL expires. This ensures consistency but introduces a delay for recreating projects with the
same name.

You can adjust the cache policies based on your application's requirements:

* A shorter TTL improves accuracy by reducing the time a stale version might be served, at the cost
  of increased DynamoDB requests.
* A longer TTL reduces RCU (Read Capacity Unit) consumption but may delay the availability of
  accurate project version information.

By tuning these parameters, you can optimize the trade-off between DynamoDB costs and the immediacy
of project version updates.

To achieve this, this plugin makes use of Gerrit built-in cache mechanism as
described [here](https://gerrit-review.googlesource.com/Documentation/config-gerrit.html#cache).
As an example, you could add the following stanza to the `gerrit.config` file:

```
[cache "aws-dynamodb-refdb.projectVersion"]
        maxAge = 1 hour
```

Defaults:
* `maxAge`: 60 seconds
