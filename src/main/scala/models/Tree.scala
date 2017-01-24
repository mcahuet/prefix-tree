package models

import scala.collection.immutable.TreeMap

/**
  * A radix-tree used to store keywords
  *
  * @param value : node word
  */
class Tree(value: Option[String]) {

  /**
    * Child node store in TreeMap(key, child)
    */
  var children = new TreeMap[Char, Tree]

  /**
    * Find the node corresponding to the search term
    *
    * @param term : search term
    * @return : node with his children
    */
  private def getLastNode(term: String): Option[Tree] = {
    if (term.isEmpty) {
      Some(this)
    } else {
      children.get(term.head).flatMap(_.getLastNode(term.tail))
    }
  }

  /**
    * Get word of the node and those of her children
    *
    * @return : list of word
    */
  private def getWords: List[String] = {
    this.value.toList ++ children.values.flatMap(_.getWords)
  }

  /**
    * Add word in tree, character by character
    *
    * @param keys  : keys used for search corresponding word
    * @param value : word
    * @return a tree create for that keys
    */
  def addWord(keys: String, value: String): Tree = {
    if (keys.isEmpty) {
      this
    } else {
      children.get(keys.head).fold {
        val word = if (keys.tail.isEmpty) Some(value) else None

        val newNode = new Tree(word)
        children = children.insert(keys.head, newNode)
        newNode.addWord(keys.tail, value)
      }(_.addWord(keys.tail, value))
    }
  }

  /**
    * Search words corresponding term
    *
    * @param term : string for search
    * @return words
    */
  def searchWord(term: String): List[String] = {
    getLastNode(term).toList.flatMap(_.getWords).sorted
  }
}
