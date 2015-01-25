package com.markglh.blob.grid

import com.example.State.SearchState
import com.markglh.blob.grid.Grid.GridCells

/**
 * Represents the grid and it's properties.
 */
case class Grid(gridCells: GridCells, width: Int, height: Int) {

  /**
   * Returns a [[Set]] of unvisited Cells
   */
  def getUnvisitedCells(searchState: SearchState): Set[(Int, Int)] = {
    gridCells.keys.filterNot((searchState.zeroCells ++ searchState.blobCells).contains).toSet
  }
}

object Grid {
  type Cell = (Int, Int)
  type GridCells = Map[Cell, Boolean]
}
