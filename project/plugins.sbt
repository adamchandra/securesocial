// Comment to get more information during initialization
logLevel := Level.Warn

resolvers += "IESL Public Releases" at "http://dev-iesl.cs.umass.edu/nexus/content/groups/public"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("play" %% "sbt-plugin" % "2.1.2")

addSbtPlugin("edu.umass.cs.iesl" %% "iesl-sbt-base" % "latest.release")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "latest.release")

