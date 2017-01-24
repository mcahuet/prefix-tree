import scala.io.Source
import models.Tree

/**
  * Object that uses a custom Tree
  */
object CustomTree {

  def main(args: Array[String]): Unit = {

    if (args.length < 1) {
      println(s"ERR => Use: runMain Main keyword...\n")
    } else {

      val tree = new Tree(None)
      val source = Source.fromFile("src/main/resources/keyWords.txt").getLines()
      source.foreach(keyword => tree.addWord(keyword.toLowerCase, keyword))

      val res = tree.searchWord(args(0).toLowerCase).take(4)
      println(s"Find : ${res.mkString(",")}")
    }
  }
}
