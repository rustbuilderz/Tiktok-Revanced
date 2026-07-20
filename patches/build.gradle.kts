group = "app.morphe"

patches {
    about {
        name = "TikTok Revanced Mini"
        description = "Half-ass Morphe patches for TikTok 46.0.3 (ads + downloads)."
        source = "https://github.com/rustbuilderz/Tiktok-Revanced-Mini"
        author = "rustbuilderz"
        contact = "na"
        website = "https://github.com/rustbuilderz/Tiktok-Revanced-Mini"
        license = "GNU General Public License v3.0, with additional GPL section 7 requirements"
    }
}

dependencies {
    compileOnly(libs.morphe.patcher)

    // Used by JsonGenerator.
    implementation(libs.gson)

    // Required due to smali, or build fails. Can be removed once smali is bumped.
    implementation(libs.guava)

    // Android API stubs defined here.
    compileOnly(project(":patches:stub"))
}

tasks {
    register<JavaExec>("generatePatchesList") {
        description = "Build patch with patch list"

        dependsOn(build)

        classpath = sourceSets["main"].runtimeClasspath
        mainClass.set("app.morphe.util.PatchListGeneratorKt")
    }
    // Used by gradle-semantic-release-plugin.
    publish {
        dependsOn("generatePatchesList")
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }
}
