import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    id("com.diffplug.spotless") version "6.22.0"
}

group = "com.spring"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

val springCloudVersion: String by extra("2023.0.2")


configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

ext {
    set("springCloudVersion", "2023.0.2")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    runtimeOnly("com.mysql:mysql-connector-j")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

//    OAuth
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.security:spring-security-oauth2-client")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    implementation("com.google.api-client:google-api-client:2.6.0")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    implementation("com.google.firebase:firebase-admin:9.3.0")

    implementation("com.cloudinary:cloudinary-http44:1.39.0")

    implementation("com.brevo:brevo:1.0.0")
}

tasks.bootJar {
    // Ensure resources are included in the bootJar task
    from("src/main/resources") // This should already be included, but just in case
}


dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}")
    }
}
tasks.withType<BootJar> {
    mainClass.set("com.spring.delivery.SpringMusicPlayer");
}