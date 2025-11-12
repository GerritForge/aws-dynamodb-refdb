# Gerrit DynamoDB ref-db

This plugin provides an implementation of the Gerrit global ref-db backed by
[AWS DynamoDB](https://aws.amazon.com/dynamodb/).

## License

This project is licensed under the **Business Source License 1.1** (BSL 1.1).
This is a "source-available" license that balances free, open-source-style access to the code
with temporary commercial restrictions.

* The full text of the BSL 1.1 is available in the [LICENSE.md](LICENSE.md) file in this
  repository.
* If your intended use case falls outside the **Additional Use Grant** and you require a
  commercial license, please contact [GerritForge Sales](https://gerritforge.com/contact).


## Requirements

Requirements for using this plugin are:

- Gerrit v3.3 or later
- DynamoDB provisioned in AWS

## Typical use-case

The global ref-db is a typical use-case of a Gerrit multi-master scenario
in a multi-site setup. Refer to the
[Gerrit multi-site plugin](https://github.com/GerritForge/multi-site/blob/master/DESIGN.md)
for more details on the high level architecture.
