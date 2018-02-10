plugins {
    application
    kotlin("jvm") version "1.2.21"
}

application {
    mainClassName = "com.flynnsam.HelloWorldKt"
}

dependencies {
    compile(kotlin("stdlib"))
}

repositories {
    jcenter()
}