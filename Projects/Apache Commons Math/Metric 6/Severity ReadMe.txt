Issue Severity

Each issue has one of five severities:

BLOCKER
Bug with a high probability to impact the behaviour of the application in production: memory leak, unclosed JDBC connection, .... The code MUST be immediately fixed.

CRITICAL
Either a bug with a low probability to impact the behaviour of the application in production or an issue which represents a security flaw: empty catch block, SQL injection, ... The code MUST be immediately reviewed.

MAJOR
Quality flaw which can highly impact the developer productivity: uncovered piece of code, duplicated blocks, unused parameters, ...

MINOR
Quality flaw which can slightly impact the developer productivity: lines should not be too long, "switch" statements should have at least 3 cases, ...

INFO
Neither a bug nor a quality flaw, just a finding.