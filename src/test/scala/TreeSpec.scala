import models.Tree
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

import scala.io.Source

class TreeSpec extends FlatSpec with Matchers with BeforeAndAfterAll {

  val tree = new Tree(None)

  override def beforeAll(): Unit = {
    val source = Source.fromFile("src/test/resources/test-keyWords.txt").getLines()
    source.foreach(keyword => tree.addWord(keyword.toLowerCase, keyword))
  }


  "Tree" should "have two leaf node" in {
    tree.children.size shouldBe 2
  }
  it should "return Pandora for pan search term" in {
    tree.searchWord("pan").size shouldBe 1
  }
  it should "return Nil for ttt search term" in {
    tree.searchWord("ttt") shouldBe Nil
  }
}
