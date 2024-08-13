@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        mavenLocal()

        maven {
            name = "lss233MirrorForgedev"
            url = uri("https://crystal.app.lss233.com/repositories/forgedev")
        }
        maven {
            name = "lss233MirrorReleases"
            url = uri("https://crystal.app.lss233.com/repositories/releases")
        }
        maven {
            name = "lss233MirrorMinecraft"
            url = uri("https://crystal.app.lss233.com/repositories/minecraft")
        }
        maven {
            name = "lss233MirrorSnapshots"
            url = uri("https://crystal.app.lss233.com/repositories/snapshots")
        }
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/jcenter") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        // Floodgate, Cumulus etc.
        maven("https://repo.opencollab.dev/main")

        // Paper, Velocity
        maven("https://repo.papermc.io/repository/maven-public")
        // Spigot
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots") {
            mavenContent { snapshotsOnly() }
        }

        // BungeeCord
        maven("https://oss.sonatype.org/content/repositories/snapshots") {
            mavenContent { snapshotsOnly() }
        }

        // NeoForge
        maven("https://maven.neoforged.net/releases") {
            mavenContent { releasesOnly() }
        }

        // Minecraft
        maven("https://libraries.minecraft.net") {
            name = "minecraft"
            mavenContent { releasesOnly() }
        }

        mavenCentral()

        // ViaVersion
        maven("https://repo.viaversion.com") {
            name = "viaversion"
        }

        maven("https://jitpack.io") {
            content { includeGroupByRegex("com\\.github\\..*") }
        }

        // For Adventure snapshots
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}

pluginManagement {
    repositories {
        maven ("https://maven.aliyun.com/repository/gradle-plugin")
        gradlePluginPortal()
        maven("https://repo.opencollab.dev/maven-snapshots/")
        maven("https://maven.fabricmc.net/")
        maven("https://maven.architectury.dev/")
        maven("https://maven.neoforged.net/releases")
    }
    includeBuild("build-logic")
}

rootProject.name = "geyser-parent"

include(":ap")
include(":api")
include(":bungeecord")
include(":fabric")
include(":neoforge")
include(":mod")
include(":spigot")
include(":standalone")
include(":velocity")
include(":viaproxy")
include(":common")
include(":core")

// Specify project dirs
project(":bungeecord").projectDir = file("bootstrap/bungeecord")
project(":fabric").projectDir = file("bootstrap/mod/fabric")
project(":neoforge").projectDir = file("bootstrap/mod/neoforge")
project(":mod").projectDir = file("bootstrap/mod")
project(":spigot").projectDir = file("bootstrap/spigot")
project(":standalone").projectDir = file("bootstrap/standalone")
project(":velocity").projectDir = file("bootstrap/velocity")
project(":viaproxy").projectDir = file("bootstrap/viaproxy")
