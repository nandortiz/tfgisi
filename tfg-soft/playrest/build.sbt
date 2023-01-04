name := """PlayRestfullAPI"""
organization := "com.example"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava,SwaggerPlugin)

scalaVersion := "2.13.2"

libraryDependencies += guice

libraryDependencies += "org.webjars" % "swagger-ui" % "2.2.0"

swaggerDomainNameSpaces := Seq("entities")
swaggerPlayJava := true
swaggerPrettyJson := true

libraryDependencies ++= Seq(
  javaJdbc
)
libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "8.0.29"
)


libraryDependencies ++= Seq(
  "org.freemarker" % "freemarker" % "2.3.31"

)
