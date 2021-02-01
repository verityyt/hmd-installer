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

    implementation("com.github.vatbub:mslinks:1.0.6")
    compile(files("D:\\Development\\Utils\\APIs\\json-simple-1.1.jar"))
}
