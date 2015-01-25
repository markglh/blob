package com.markglh.blob.grid

/**
 * Implementations will create and provide the grid.
 */
trait GridLoader {

  /**
   * Loads and returns a [[Grid]]
   */
  def loadGrid(): Grid
}
