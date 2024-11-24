# Gerrit DynamoDB ref-db

This plugin provides an implementation of the Gerrit global ref-db backed by
[AWS DynamoDB](https://aws.amazon.com/dynamodb/).

Requirements for using this plugin are:

- Gerrit v3.3 or later
- DynamoDB provisioned in AWS

## Typical use-case

The global ref-db is a typical use-case of a Gerrit multi-master scenario
in a multi-site setup. Refer to the
[Gerrit multi-site plugin](https://gerrit.googlesource.com/plugins/multi-site/+/master/DESIGN.md)
for more details on the high level architecture.

## Gotchas

### Project deletions

When a project is deleted in Gerrit, this plugin must search through the entire ref table to
identify all items related to that project.

This process, called a scan, systematically checks every item in the table to find matches for the
deleted project.

This process can take time to complete, especially for large tables with many items.

Additionally, scans consume a significant amount of read capacity, measured in Read Capacity Units (
RCUs), which directly impacts the cost and performance of the database.

However, if project deletions are infrequent, this approach is typically acceptable, as the
occasional delay and resource consumption will be manageable in most use cases.
