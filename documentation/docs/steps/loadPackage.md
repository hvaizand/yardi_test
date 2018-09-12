# loadPackage

## Description
This will load the packages using pldpkgload located on C:\Utils\ on the Jenkins server

## Prerequisites
* **<prerequisite>** - further description.

## Parameters
| parameter      | mandatory | default                           | possible values    |
| ---------------|-----------|-----------------------------------|--------------------|
| `filePackage`  | yes       |                                   |                    |
| `db_server`    | yes       |                                   |                    |
| `db_name`      | yes       |                                   |                    |
| `count`        | yes       |                                   |                    |

* `filePackage` - Map containing all the packages to be loaded.
* `db_server` - SQL Server name to connect to.
* `db_name` - SQL Database name to connect to.
* `count` - Number of packages expected to be loaded.

## Step configuration
The following parameters can also be specified as step parameters using the global configuration file:

* `pldpkgload` - File name for the pldpkgload program to use. By default it is 'pldpkgload.pkglist'

## Return value
none

## Side effects
none

## Exceptions
* `ExceptionType`
    * List of cases when exception is thrown.

## Example
```groovy
    loadPackage(filePackage: filePackage, dbo_credentials: yardiConfig.dbo_credentials, db_server: yardiConfig.db_server, db_name: yardiConfig.db_name, count: countFiles.countpkg)
```
