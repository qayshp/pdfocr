// Metadata

name := "pdfocr"

version := "0.0.2"

homepage := Some(url("https://github.com/overview/pdfocr"))

organization := "org.overviewproject"

organizationName := "Overview Services Inc."

organizationHomepage := Some(url("https://www.overviewdocs.com"))

description := "Library that shells to Tesseract to make PDFs searchable"

licenses += "AGPLv3" -> url("http://www.gnu.org/licenses/agpl-3.0.html")

fork in (Compile, run) := true // Main calls System.exit() to give proper return codes


// Compile settings

scalaVersion := "2.11.7"

scalacOptions += "-deprecation"

libraryDependencies ++= Seq(
  "org.bouncycastle" % "bcmail-jdk15" % "1.44", // https://pdfbox.apache.org/1.8/dependencies.html
  "org.bouncycastle" % "bcprov-jdk15" % "1.44", // https://pdfbox.apache.org/1.8/dependencies.html
  "com.github.jai-imageio" % "jai-imageio-core" % "1.3.1", // for TIFF support
  "com.github.jai-imageio" % "jai-imageio-jpeg2000" % "1.3.0", // for JPEG2000 support
  "com.levigo.jbig2" % "levigo-jbig2-imageio" % "1.6.5",
  "org.apache.pdfbox" % "pdfbox" % "2.0.1",
  "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test",
  "org.mockito" % "mockito-core" % "1.10.19" % "test",
  "org.slf4j" % "jcl-over-slf4j" % "1.7.12" % "test", // So we can mute warnings during testing
  "org.slf4j" % "slf4j-simple" % "1.7.12" % "test"    // So we can mute warnings during testing
)


// Test settings

fork in Test := true

javaOptions in Test ++= Seq("-Dorg.slf4j.simpleLogger.defaultLogLevel=INFO")

testOptions in Test += Tests.Argument("-oDF")


// Publish settings

publishTo <<= version { v: String =>
  if (v.endsWith("SNAPSHOT")) {
    Some("snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
  } else {
    Some("releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
  }
}

publishMavenStyle := true

pomIncludeRepository := { _ => false }

pomExtra := (
  <scm>
    <connection>scm:git:github.com/overview/pdfocr.git</connection>
    <developerConnection>scm:git:git@github.com:overview/pdfocr.git</developerConnection>
    <url>github.com/overview/pdfocr.git</url>
  </scm>
  <developers>
    <developer>
      <id>adam@adamhooper.com</id>
      <name>Adam Hooper</name>
      <url>http://adamhooper.com</url>
    </developer>
  </developers>
)
