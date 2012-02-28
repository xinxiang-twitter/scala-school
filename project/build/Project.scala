import sbt._
import com.twitter.sbt._

class Project(info: ProjectInfo) extends StandardProject(info) with SubversionPublisher {
  val jackson = "org.codehaus.jackson" % "jackson-core-asl" % "1.6.1"
  val specs = "org.scala-tools.testing" % "specs_2.8.0" % "1.6.5" % "test"

  lazy val print = task {log.info("a test action"); None}
  //lazy val print = task {log.info("a test action"); None}.dependsOn(compile) describedAs("prints a line after compile")

  //override def subversionRepository = Some("http://svn.local.twitter.com/maven/")
}
