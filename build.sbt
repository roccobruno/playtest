name := """manibreak"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  "ws.securesocial" %% "securesocial" % "3.0-M3",
  cache,
  ws,
  specs2 % Test
)

resolvers += Resolver.sonatypeRepo("releases")
resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
