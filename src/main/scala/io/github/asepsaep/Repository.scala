package io.github.asepsaep

import reactivemongo.api.MongoDriver
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.{BSONDocument, Macros}

import scala.concurrent.{ExecutionContext, Future}

class Repository(implicit ec: ExecutionContext) extends Config {

  private implicit val userHandler = Macros.handler[User]

  private val driver = new MongoDriver
  private val connection = driver.connection(List(mongoHost))
  private val users: Future[BSONCollection] = connection.database(mongoDb).map(db => db.collection("users"))

  def insertUser(user: User): Future[User] = {
    users.flatMap(_.findAndUpdate(BSONDocument("username" -> user.username), user, upsert = true).map(_ => user))
  }

  def findUser(username: String): Future[Option[User]] =
    users.flatMap(_.find(BSONDocument("username" -> username)).cursor[User]().headOption)

  def close(): Unit = {
    connection.close()
    driver.system.terminate().onComplete(_ => {})
  }

}
