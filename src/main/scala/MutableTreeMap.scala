import scala.collection.mutable
import scala.io.Source

/**
  * Object that uses a mutable TreeMap
  */

object MutableTreeMap {

  def main(args: Array[String]): Unit = {

    if (args.length < 1) {
      println(s"ERR => Use: runMain Main keyword...\n")
    } else {

      val term = args(0).toLowerCase

      val tree = new mutable.TreeMap[String, String]

      val source = Source.fromFile("src/main/resources/keyWords.txt").getLines()

      source.foreach(key => tree.+=((key.toLowerCase, key)))
      val res = tree.filterKeys(_.startsWith(term)).take(4)

      println(s"Find : ${res.values.mkString(",")}")

    }
  }

}
