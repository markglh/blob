package com.markglh.blob.pickers

import com.example.State.{GlobalState, SolutionState}
import com.markglh.blob.grid.Grid
import com.markglh.blob.grid.Grid.Cell

import scala.util.Random

/**
 * Simply picks any [[Cell]] outside of the current known blob boundaries at random.
 */
class RandomPicker extends CellPicker {

  private def findRandomCellOutsideBoundary(virginCells: Iterable[Cell], solutionState: SolutionState): Option[Cell] = {
    virginCells.find(cell =>
      cell._1 < solutionState.left || cell._1 > solutionState.right ||
        cell._2 < solutionState.top || cell._2 > solutionState.bottom)
  }

  /**
   * Find the next cell by blindly finding any cell in each direction then picking one to return.
   * If no cells are found then we're done!
   */
  def findNext(grid: Grid, globalState: GlobalState): Option[Cell] = globalState.solutionState match {
    case None => Random.shuffle(grid.getUnvisitedCells(globalState.searchState)).headOption
    case Some(solutionState) => findRandomCellOutsideBoundary(grid.getUnvisitedCells(globalState.searchState), solutionState)
  }
}
