package com.twitter.sample

import org.specs._

object SimpleParserSpec extends Specification {
  "SimpleParser" should {

//    doFirst { println("first thing...") }
//    doLast { println("last thing...") }
//
//    doBefore { println("before...") }
//    doAfter { println("after...") }

    val parser = new SimpleParser()

    "do simple math first" in {
      1 + 1 mustEqual 2
    }

    "do some advanced math now" in {
      2 * 3 mustEqual 6
    }

    "add" in {
      "two numbers" in {
        1 + 1 mustEqual 2
      }
      "three nubmers" in {
        1 + 1 + 1 mustEqual 3
      }
    }

    var x = 0
    "mutations are isolated" in {
      "x equals 1 if we set it" in {
        x = 1
        x mustEqual 1
      }
      "x is the default value if we don't change it" in {
        x mustEqual 0
      }
    }

    "work with basic tweet" in {
      val tweet = """{"id":1,"text":"foo"}"""
      parser.parse(tweet) match {
        case Some(parsed) => {
          parsed.text must be_==("foo")
          parsed.id must be_==(1)
        }
        case _ => fail("didn't parse tweet")
      }
    }

    "reject a non-JSON tweet" in {
      val tweet = """"id":1,"text":"foo""""
      parser.parse(tweet) match {
        case Some(parsed) => fail("didn't reject a non-JSON tweet")
        case e => e must be_==(None)
      }
    }

    "ignore nested content" in {
      val tweet = """{"id":1,"text":"foo","nested":{"id":2}}"""
      parser.parse(tweet) match {
        case Some(parsed) => {
          parsed.text must be_==("foo")
          parsed.id must be_==(1)
        }
        case _ => fail("didn't parse tweet")
      }
    }

    "fail on partial content" in {
      val tweet = """{"id":1}"""
      parser.parse(tweet) match {
        case Some(parsed) => fail("didn't reject a partial tweet")
        case e => e must be_==(None)
      }
    }

  }
}
