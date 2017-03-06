package io.github.asepsaep

import akka.actor.ActorSystem

import scala.concurrent.{ExecutionContext, Future}

class Service(repository: Repository)(implicit actorSystem: ActorSystem, ec: ExecutionContext) extends Config {

  def add(user: User): Future[User] = repository.insertUser(user)

  def find(username: String): Future[Option[User]] = repository.findUser(username)

  def close(): Unit = repository.close()

}
