import sbt._
import Keys._
import PlayProject._
import edu.umass.cs.iesl.sbtbase.Dependencies
import edu.umass.cs.iesl.sbtbase.IeslProject._

object ApplicationBuild extends Build {
  implicit val allDeps: Dependencies = new Dependencies()
  import allDeps._
  // override def settings = super.settings ++ org.sbtidea.SbtIdeaPlugin.ideaSettings


  val appName         = "securesocial"
  val appVersion      = "0.1-SNAPSHOT"
  val organization    = "net.openreview"

  val appDependencies = Seq(
    "org.apache.commons" % "commons-email" % "latest.release",
    "org.mindrot" % "jbcrypt" % "latest.release"
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
