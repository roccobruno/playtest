name := """manibreak"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"


libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.3.9" % "provided",
  "uk.gov.hmrc" %% "play-reactivemongo" % "4.0.2",
  "uk.gov.hmrc" %% "simple-reactivemongo" % "3.1.2",
  "uk.gov.hmrc" %% "mongo-lock" % "1.0.0",
  "org.scalatest" %% "scalatest" % "2.2.1" % Test,
  "uk.gov.hmrc" %% "hmrctest" % "1.2.0" % Test,
  "org.pegdown" % "pegdown" % "1.4.2" % Test
)

resolvers += Resolver.bintrayRepo("hmrc", "releases")
resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.


