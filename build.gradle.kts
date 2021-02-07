plugins {
    java
    kotlin("jvm") version "1.4.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testCompile("junit", "junit", "4.12")

    compile("com.github.vatbub:mslinks:1.0.6")
    compile("com.googlecode.json-simple:json-simple:1.1")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    jar {
        archiveName = "HMD-Installer.jar"

        from(sourceSets.main.get().output)
        dependsOn(configurations.runtimeClasspath)

        from({
            configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
        })

        manifest {
            attributes["Main-Class"] = "HMDInstaller"
        }

        exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
    }
}

tasks.register("export") {
    group = "build"
    description = "Exports the project as jar file"

    dependsOn("jar")
}
