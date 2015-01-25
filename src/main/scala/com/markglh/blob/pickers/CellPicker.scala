package com.markglh.blob.pickers

import com.example.State._
import com.markglh.blob.grid.Grid
import com.markglh.blob.grid.Grid.Cell

/**
 * Implementations of this Trait will find and return cells in the grid for us to validate.
 */
trait CellPicker {

  /**
   * Find the next possible cell in the grid.
   * @param grid The grid
   * @param globalState the current state
   * @return The next [[Cell]] or [[None]] if we've exhausted all viable blob cells.
   */
  def findNext(grid: Grid, globalState: GlobalState): Option[Cell]

}
