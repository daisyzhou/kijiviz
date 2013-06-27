package controllers

import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._

import org.kiji.express.modeling.ModelDefinition

object ModelDef extends Controller {
  val protocolVersion = ModelDefinition.MAX_MODEL_DEF_VER

  def modelDefFromText(
      name: String,
      version: String,
      extractor: String,
      scorer: String): ModelDefinition = {
    val modelDefTemplate =
        "\"name\": \"%s\", \"version\":\"%s\", \"extractor_class\":\"%s\"," +
        "\"scorer_class\":\"%s\",\"protocol_version\":\"%s\""
    val modelDefJSON = modelDefTemplate.format(name, version, extractor, scorer, protocolVersion)
    ModelDefinition.fromJson(modelDefJSON)
  }

  def modelDefToText(modelDef: ModelDefinition): Option[(String, String, String, String)] = {
    Some((
        modelDef.name,
        modelDef.version,
        modelDef.extractorClass.toString,
        modelDef.scorerClass.toString))
  }

  val createModelDefForm = Form(
    mapping("name" -> nonEmptyText,
        "version" -> nonEmptyText,
        "extractor class name" -> nonEmptyText,
        "scorer class name" -> nonEmptyText)(modelDefFromText)(modelDefToText))

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
