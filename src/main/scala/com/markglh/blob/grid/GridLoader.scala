package com.markglh.blob.grid

/**
 * Implementations will create and provide the grid.
 */
trait GridLoader {
  def loadGrid(): Grid
}
