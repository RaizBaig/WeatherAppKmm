
plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.0.2").apply(false)
    id("com.android.library").version("8.0.2").apply(false)
    kotlin("android").version("1.8.21").apply(false)
    kotlin("multiplatform").version("1.8.21").apply(false)
}
buildscript {
    dependencies{
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.0")
        classpath("com.android.tools.build:gradle:7.0.2")
        classpath("com.google.gms:google-services:4.3.14")

    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
