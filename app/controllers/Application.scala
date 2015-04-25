package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.json

object Application extends Controller {

  def index = Action {

      //データをJsonで返す
      val member = Json.toJson(
         List(
           Map("name" -> Json.toJson("Gerrard"),   "number" -> Json.toJson(8),  "age" -> Json.toJson(34)),
           Map("name" -> Json.toJson("Henderson"), "number" -> Json.toJson(14), "age" -> Json.toJson(25)),
           Map("name" -> Json.toJson("Skrtel"),    "number" -> Json.toJson(37), "age" -> Json.toJson(31))
         )
      )

    Ok( member ).withHeaders("Access-Control-Allow-Origin" -> " *")
//    Ok(views.html.index("Yes!!"))
  }

}