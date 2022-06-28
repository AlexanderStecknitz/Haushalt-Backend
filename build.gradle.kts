@file:Suppress("SpellCheckingInspection")
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    idea

    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSpring)
    alias(libs.plugins.kotlinAllopen)
    alias(libs.plugins.kotlinNoarg)
    alias(libs.plugins.kotlinJpa)
    alias(libs.plugins.springBoot)
}

group = "de.stecknitz"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_18

repositories {
    mavenCentral()
}

val ktlintCfg: Configuration by configurations.creating
dependencies {
    implementation(platform(libs.kotlinBom))
    implementation(platform(libs.nettyBom))
    implementation(platform(libs.springBom))
    implementation(platform(libs.springBootBom))

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.springframework.hateoas:spring-hateoas")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-tomcat")
    implementation("org.springframework.boot:spring-boot-starter-json")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    @Suppress("UnstableApiUsage")
    ktlintCfg(libs.bundles.ktlint) {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }

    constraints {
        implementation(libs.annotations)
        implementation(libs.springHateoas)
        ktlintCfg(libs.bundles.ktlint)
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "18"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

@Suppress("KDocMissingDocumentation")
val ktlint by tasks.register<JavaExec>("ktlint") {
    classpath = ktlintCfg
    mainClass.set("com.pinterest.ktlint.Main")
    // https://github.com/pinterest/ktlint/blob/master/ktlint/src/main/kotlin/com/pinterest/ktlint/Main.kt
    args = listOfNotNull(
        "--verbose",
        "--experimental",
        "--relative",
        "--color",
        "--reporter=plain",
        "--reporter=checkstyle,output=$buildDir/reports/ktlint.xml",
        "src/**/*.kt"
    )

    description = "Check Kotlin code style."
    group = "verification"
}
tasks.check { dependsOn(ktlint) }
