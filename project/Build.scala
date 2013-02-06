import sbt._
import Keys._
import PlayProject._
import edu.umass.cs.iesl.sbtbase.Dependencies
import edu.umass.cs.iesl.sbtbase.IeslProject._

object ApplicationBuild extends Build {
  implicit val allDeps: Dependencies = new Dependencies()
  import allDeps._
  override def settings = super.settings ++ org.sbtidea.SbtIdeaPlugin.ideaSettings


  val appName         = "securesocial"
  val appVersion      = "0.1-SNAPSHOT"
  val organization    = "net.openreview"

  val appDependencies = Seq(
    // "com.typesafe" %% "play-plugins-util" % "2.0.3",
    // "com.typesafe" %% "play-plugins-mailer" % "2.0.4",
    "org.apache.commons" % "commons-email" % "1.2",
    // "com.typesafe" %% "play-plugins-util" % "2.0.3-08072012"
    "org.mindrot" % "jbcrypt" % "0.3m"
  )


  // val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA)
  val main = (PlayProject(appName, appVersion, appDependencies, path = file("module-code"), mainLang = SCALA)
    .ieslSetup(appVersion, appDependencies, Public, WithSnapshotDependencies, org = organization, conflict = ConflictStrict)
    .settings(resolvers ++= Seq(
      "jBCrypt Repository" at "http://repo1.maven.org/maven2/org/",
      "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
    )
  ))

}
