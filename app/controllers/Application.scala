package controllers

import jdk.nashorn.internal.ir.RuntimeNode.Request
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.json
import play.api.data._
import play.api.data.Forms._
import java.io.PrintWriter
import anorm._
import anorm.SqlParser._
import play.api.db.DB
import play.api.Play.current

object Application extends Controller {

  //データをJsonで返す
  def index = Action {
      //データをJsonで返す
      val member = Json.toJson(
         List(
           Map("name" -> Json.toJson("Gerrard"),   "number" -> Json.toJson(8),  "age" -> Json.toJson(34)),
           Map("name" -> Json.toJson("Henderson"), "number" -> Json.toJson(14), "age" -> Json.toJson(25)),
           Map("name" -> Json.toJson("Skrtel"),    "number" -> Json.toJson(37), "age" -> Json.toJson(31)),
           Map("name" -> Json.toJson("Sakho"),     "number" -> Json.toJson(17), "age" -> Json.toJson(24))
         )
      )

    Ok( member ).withHeaders("Access-Control-Allow-Origin" -> " *")
//    Ok(views.html.index("Yes!!"))
  }



  //Formのサンプル
  val form = Form("age" -> number)
  def formSample = Action { implicit request =>
      val form_age = form.bindFromRequest.get
      val age = Json.toJson( Map("age" -> form_age) )
      Ok( age ).withHeaders("Access-Control-Allow-Origin" -> " *")
  }



  //Listでデータを宣言しAngular側で取得する
  def initTasks = Action {

    //DB接続
    DB.withConnection { implicit conn =>
      //DB接続が出来ているかの確認
      // val result: Boolean = SQL("Select 1").execute()

      val task = SQL("SELECT * FROM task")
      val data = task().map(row =>
        Map( "id" -> Json.toJson(row[Int]("id")),
             "body" -> Json.toJson(row[String]("body")),
             "doen" -> Json.toJson(row[String]("doen")),
             "num" -> Json.toJson(row[Int]("num"))
        )
      ).toList


      //表示用初期データ
//      val tasks = Json.toJson(
//        List(
//          Map("body" -> Json.toJson("First Task"), "doen" -> Json.toJson(false), "num" -> Json.toJson(0)),
//          Map("body" -> Json.toJson("Second Task"), "doen" -> Json.toJson(false), "num" -> Json.toJson(1)),
//          Map("body" -> Json.toJson("Third Task"), "doen" -> Json.toJson(false), "num" -> Json.toJson(2))
//        )
//      )

      val tasks = Json.toJson( data )

      Ok(tasks).withHeaders("Access-Control-Allow-Origin" -> " *")
    }
  }



}