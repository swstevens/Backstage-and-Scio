import sbt._
import Keys._
// see https://github.com/spotify/scio/blob/v0.14.2/build.sbt
val scioVersion = "0.14.2"
val beamVersion = "2.54.0"
val slf4jVersion = "1.7.30"
val sparkVersion = "3.5.0"

lazy val commonSettings = Def.settings(
  organization := "swstevens",
  // Semantic versioning http://semver.org/
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.13.13",
  scalacOptions ++= Seq(
    "-release", "8",
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Ymacro-annotations"
  ),
  javacOptions ++= Seq("--release", "8"),
  // add extra resolved and remove exclude if you need kafka
  // resolvers += "confluent" at "https://packages.confluent.io/maven/",
  excludeDependencies += "org.apache.beam" % "beam-sdks-java-io-kafka"
)

lazy val root: Project = project
  .in(file("."))
  .settings(commonSettings)
  .settings(
    name := "scio-personal",
    description := "scio-personal",
    publish / skip := true,
    fork := true,
    run / outputStrategy := Some(OutputStrategy.StdoutOutput),
    libraryDependencies ++= Seq(
      "com.spotify" %% "scio-core" % scioVersion,
      "org.slf4j" % "slf4j-api" % slf4jVersion,
      "org.apache.beam" % "beam-runners-spark-3" % beamVersion % Runtime,
      "org.apache.spark" %% "spark-core" % sparkVersion % Runtime,
      "org.apache.spark" %% "spark-streaming" % sparkVersion % Runtime,
      "org.apache.beam" % "beam-runners-direct-java" % beamVersion % Test,
      "com.spotify" %% "scio-test" % scioVersion % Test,
      "org.slf4j" % "slf4j-simple" % slf4jVersion % Test
    ),
  )

lazy val repl: Project = project
  .in(file(".repl"))
  .settings(commonSettings)
  .settings(
    name := "repl",
    description := "Scio REPL for scio-personal",
    libraryDependencies ++= Seq(
      "com.spotify" %% "scio-repl" % scioVersion
    ),
    Compile / mainClass := Some("com.spotify.scio.repl.ScioShell"),
    publish / skip := true,
    fork := false,
  )
  .dependsOn(root)
