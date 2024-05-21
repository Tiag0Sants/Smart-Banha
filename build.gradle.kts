buildscript{
    dependencies {
        classpath("com.google.gms:google-services:4.4.1")
    }
}
plugins {
    id("com.android.application") version "8.4.0" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
}