import java.text.SimpleDateFormat
import java.util.*

plugins {
    java
    id("net.neoforged.moddev") version "2.1.20"
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

val mod_id: String by project
val mod_name: String by project
val mod_license: String by project
val mod_version: String by project
val mod_authors: String by project
val mod_description: String by project
val mod_group_id: String by project
val minecraft_version: String by project
val neo_version: String by project

group = mod_group_id
version = mod_version

base {
    archivesName.set(mod_id)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

neoForge {
    version.set(neo_version)

    parchment {
        mappingsVersion.set(project.property("parchment_mappings_version").toString())
        minecraftVersion.set(project.property("parchment_minecraft_version").toString())
    }

    runs {
        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")
            systemProperty("forge.logging.console.level", "debug")
        }

        create("client") {
            client()
            gameDirectory.set(project.file("run"))
        }

        create("server") {
            server()
            gameDirectory.set(project.file("run"))
        }
    }

    mods {
        create(mod_id) {
            sourceSet(sourceSets.main.get())
        }
    }
}

val generateModMetadata = tasks.register<ProcessResources>("generateModMetadata") {
    inputs.properties(mapOf(
        "mod_id" to mod_id,
        "mod_name" to mod_name,
        "mod_version" to mod_version,
        "mod_description" to mod_description,
        "mod_authors" to mod_authors,
        "mod_license" to mod_license,
        "minecraft_version_range" to project.property("minecraft_version_range"),
        "neo_version_range" to project.property("neo_version_range"),
        "loader_version_range" to project.property("loader_version_range")
    ))
    from(sourceSets.main.get().resources.srcDirs) {
        include("META-INF/neoforge.mods.toml")
        expand(inputs.properties)
    }
    into(layout.buildDirectory.dir("generated/sources/modMetadata"))
}

sourceSets.main.get().resources.srcDirs(generateModMetadata.map { it.destinationDir })

tasks.withType<ProcessResources>().configureEach {
    exclude("META-INF/neoforge.mods.toml")
}