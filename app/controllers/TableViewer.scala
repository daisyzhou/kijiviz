package controllers

import scala.collection.JavaConverters._

import play.api._
import play.api.mvc._

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration

import org.kiji.schema.KConstants
// import org.kiji.schema.Kiji
// import org.kiji.schema.KijiURI

object TableViewer extends Controller {

  def index = Action {
    // val conf: Configuration = HBaseConfiguration.create()

    // // TODO: Parameterize the kiji instance used.
    // val kiji: Kiji = Kiji.Factory.open(
    //     KijiURI.newBuilder().withInstanceName(KConstants.DEFAULT_INSTANCE_NAME).build(),
    //     conf)

    // // List the tables available in the view.
    // val tableNames: Seq[String] = kiji.getTableNames().asScala

    // kiji.release()

    Ok(views.html.tableviewer_index(Seq[String]()))
  }

}
