resolvers += Classpaths.typesafeReleases

addSbtPlugin("io.spray" % "sbt-revolver" % "0.8.0") 

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.4")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.0.0")

addSbtPlugin("com.heroku" % "sbt-heroku" % "1.0.0")
