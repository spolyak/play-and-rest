package controllers

import play.api.data.Form
import play.api.data.Forms.{ single, nonEmptyText }
import play.api.mvc.{ Action, Controller }
import anorm.NotAssigned
//import dispatch._

import models.Bar
import com.codahale.jerkson.Json

object Application extends Controller {

  val barForm = Form(
    single("name" -> nonEmptyText))

  def index = Action {
    Ok(views.html.index(barForm))
  }

  def addBar() = Action { implicit request =>
    barForm.bindFromRequest.fold(
      errors => BadRequest,
      {
        case (name) =>
          Bar.create(Bar(NotAssigned, name))
          Redirect(routes.Application.index())
      })
  }

  def listBars() = Action {
    val bars = Bar.findAll()

    val json = Json.generate(bars)

    Ok(json).as("application/json")
  }

  def getRecommendations() = Action {
    //val svc = url("http://api.hostip.info/country.php")
    //val country = Http(svc OK as.String)
    Ok("US")
  }
}


 
 