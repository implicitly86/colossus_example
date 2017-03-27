package com.implicitly

import akka.actor.ActorSystem
import colossus._
import colossus.core._
import colossus.protocols.http.HttpMethod._
import colossus.protocols.http.UrlParsing._
import colossus.protocols.http._
import colossus.service._

object Main extends App {

    implicit val actorSystem = ActorSystem()
    implicit val io = IOSystem()

    Server.basic("example-server", 9000) {
        new HttpService(_) {
            def handle = {
                case request@Get on Root / "hello" => Callback.successful(request.ok("Hello world!"))
                case request@Get on Root / "echo" / str => Callback.successful(request.ok(str))
            }
        }
    }

}
