package controllers

import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._

object ModelDef extends Controller {
  // todo make them all required
  // todo default for protocol version ?
  val createModelDefForm = Form(
    tuple("name" -> text,
        "version" -> text/**, // todo add the other fields
        "extractor class name" -> text,
        "scorer class name" -> text,
        "protocol version" -> text*/))

  def form = Action {
    Ok(views.html.modeldef(createModelDefForm))
  }

  def submit = Action { implicit request =>
    createModelDefForm.bindFromRequest.fold(
      errors => BadRequest(views.html.modeldef(errors)),
      // todo: have a "success" page
      modeldef => Ok(views.html.modeldef(createModelDefForm))
    )
  }

}
