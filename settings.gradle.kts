plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "basic-back-end"
include("internal-api-module")
include("core-module")
include("storage-module")
include("external-api-module")
include("storage-module:db-jpa")
findProject(":storage-module:db-jpa")?.name = "db-jpa"
